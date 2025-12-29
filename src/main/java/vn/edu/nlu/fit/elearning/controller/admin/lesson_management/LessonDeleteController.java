package vn.edu.nlu.fit.elearning.controller.admin.lesson_management;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import vn.edu.nlu.fit.elearning.services.CourseService;
import vn.edu.nlu.fit.elearning.services.LessonService;
import vn.edu.nlu.fit.elearning.services.TagService;

import java.io.IOException;

@WebServlet(name = "LessonDeleteController", value = "/admin/lesson/delete")
public class LessonDeleteController extends HttpServlet {
    private LessonService lessonService;

    @Override
    public void init() throws ServletException {
        super.init();
        this.lessonService = new LessonService();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String lessonId = request.getParameter("id");
        if(lessonId != null){
            int idLesson= Integer.parseInt(lessonId);
            int success = lessonService.deleteLesson(idLesson);
            if (success >0) {
                request.getSession().setAttribute("flashSuccess", "Xóa bài học thành công!");
            } else {
                request.getSession().setAttribute("flashError", "Xóa bài học thất bại. Vui lòng thử lại!");
            }
            response.sendRedirect(request.getContextPath() + "/admin/lessons");

        }
    }
}