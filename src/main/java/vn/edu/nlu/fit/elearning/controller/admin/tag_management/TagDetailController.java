    package vn.edu.nlu.fit.elearning.controller.admin.tag_management;
    
    import com.google.gson.Gson;
    import jakarta.servlet.ServletException;
    import jakarta.servlet.annotation.WebServlet;
    import jakarta.servlet.http.HttpServlet;
    import jakarta.servlet.http.HttpServletRequest;
    import jakarta.servlet.http.HttpServletResponse;
    import vn.edu.nlu.fit.elearning.model.Tag;
    import vn.edu.nlu.fit.elearning.services.TagService;
    
    import java.io.IOException;
    
    @WebServlet(name = "TagDetailController", value = "/admin/tag/detail")
    public class TagDetailController extends HttpServlet {
        private TagService tagService;
    
        public TagDetailController() {
            // Khởi tạo TagService
            this.tagService = new TagService();
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
                Tag tag = tagService.getTagById(id);
    
                if (tag != null) {
                    // 4. Chuyển đối tượng Tag thành chuỗi JSON và gửi về Client
                    String json = new Gson().toJson(tag);
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