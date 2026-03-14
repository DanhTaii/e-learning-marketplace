package vn.edu.nlu.fit.elearning.feature.lesson.controller.admin;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import vn.edu.nlu.fit.elearning.common.container.BeanContainer;
import vn.edu.nlu.fit.elearning.feature.lesson.model.Lesson;
import vn.edu.nlu.fit.elearning.feature.lesson.service.LessonService;
import vn.edu.nlu.fit.elearning.feature.lesson.service.LessonServiceImpl;

import java.io.IOException;

@WebServlet(name = "LessonUpdateController", value = "/admin/lesson/update")
public class LessonUpdateController extends HttpServlet {

    private LessonService lessonService;

    @Override
    public void init() throws ServletException {
        super.init();
        this.lessonService = BeanContainer.getBean(LessonService.class);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            int id = Integer.parseInt(request.getParameter("id"));
            int courseId = Integer.parseInt(request.getParameter("courseId"));
            String nameLesson = request.getParameter("nameLesson");
            String videoURL = request.getParameter("videoURL");
            int durationMinutes = Integer.parseInt(request.getParameter("durationMinutes"));

            int orderIndex = Integer.parseInt(request.getParameter("orderIndex"));
            int oldOrderIndex = Integer.parseInt(request.getParameter("oldOrderIndex"));
            int oldCourseId = Integer.parseInt(request.getParameter("oldCourseId"));

            // 2. Kiểm tra dữ liệu trống
            if (nameLesson == null || nameLesson.trim().isEmpty() || videoURL.isEmpty()) {
                request.getSession().setAttribute("flashError", "Vui lòng nhập đầy đủ thông tin!");
                response.sendRedirect(request.getContextPath() + "/admin/lessons"); // Quay lại trang quản lý
                return;
            }

            // 3. Tạo Object Lesson
            Lesson lesson = new Lesson();
            lesson.setId(id);
            lesson.setCourseId(courseId);
            lesson.setTitle(nameLesson);
            lesson.setVideoUrl(videoURL);
            lesson.setDurationMinutes(durationMinutes);
            lesson.setOrderIndex(orderIndex);

            // 4. Gọi Service xử lý cập nhật
            // Ở đây chúng ta truyền thêm oldOrderIndex để Service biết có cần sắp xếp lại bài học khác không
            boolean success = lessonService.updateLessonWithOrdering(lesson, oldOrderIndex,oldCourseId);

            if (success) {
                request.getSession().setAttribute("flashSuccess", "Cập nhật bài học thành công!");
            } else {
                request.getSession().setAttribute("flashError", "Cập nhật thất bại. Vui lòng thử lại!");
            }

        } catch (NumberFormatException e) {
            request.getSession().setAttribute("flashError", "Dữ liệu số (Thời lượng/Thứ tự) không hợp lệ!");
        } catch (Exception e) {
            e.printStackTrace();
            request.getSession().setAttribute("flashError", "Có lỗi hệ thống xảy ra!");
        }
        String searchName = request.getParameter("currentSearchName");
        String courseIdFilter = request.getParameter("currentCourseId");
        if ((searchName != null && !searchName.isEmpty()) || (courseIdFilter != null && !courseIdFilter.isEmpty())) {
            // Nếu trước đó có dùng bộ lọc, quay về trang search kèm tham số
            response.sendRedirect(request.getContextPath() + "/admin/lesson/search?searchName="
                    + searchName + "&courseId=" + courseIdFilter);
        } else {
            // Nếu không có bộ lọc, quay về trang danh sách mặc định
            response.sendRedirect(request.getContextPath() + "/admin/lessons");
        }
    }
    }




