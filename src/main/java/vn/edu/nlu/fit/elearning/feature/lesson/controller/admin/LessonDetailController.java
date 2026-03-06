    package vn.edu.nlu.fit.elearning.feature.lesson.controller.admin;

    import com.google.gson.Gson;
    import jakarta.servlet.ServletException;
    import jakarta.servlet.annotation.WebServlet;
    import jakarta.servlet.http.HttpServlet;
    import jakarta.servlet.http.HttpServletRequest;
    import jakarta.servlet.http.HttpServletResponse;
    import vn.edu.nlu.fit.elearning.feature.lesson.model.Lesson;
    import vn.edu.nlu.fit.elearning.feature.lesson.service.LessonService;

    import java.io.IOException;

    @WebServlet(name = "LessonDetailController", value = "/admin/lesson/detail")
    public class LessonDetailController extends HttpServlet {
        private LessonService lessonService;

        @Override
        public void init() throws ServletException {
            super.init();
            this.lessonService = new LessonService();
        }
    
        @Override
        protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
            // 1. Thiết lập Header để trình duyệt hiểu đây là dữ liệu JSON
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
    
            try {
                // 2. Lấy ID từ request
                String idStr = request.getParameter("id");
                if (idStr == null || idStr.isEmpty()) {
                    response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                    return;
                }
    
                int id = Integer.parseInt(idStr);
    
                // 3. Gọi Service để tìm Tag trong Database
                Lesson lesson = lessonService.getLessonById(id);
    
                if (lesson != null) {
                    // 4. Chuyển đối tượng Tag thành chuỗi JSON và gửi về Client
                    String json = new Gson().toJson(lesson);
                    response.getWriter().write(json);
                } else {
                    // Trả về 404 nếu không tìm thấy ID này
                    response.setStatus(HttpServletResponse.SC_NOT_FOUND);
                }
            } catch (Exception e) {
                e.printStackTrace();
                // Trả về 500 nếu có lỗi hệ thống (ép kiểu sai, lỗi SQL...)
                response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            }
        }
    
        @Override
        protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    
        }
    }