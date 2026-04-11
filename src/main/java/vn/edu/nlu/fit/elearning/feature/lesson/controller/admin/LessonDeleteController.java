package vn.edu.nlu.fit.elearning.feature.lesson.controller.admin;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import vn.edu.nlu.fit.elearning.common.container.BeanContainer;
import vn.edu.nlu.fit.elearning.feature.lesson.service.LessonService;

import java.io.IOException;

@WebServlet(name = "LessonDeleteController", value = "/admin/lesson/delete")
public class LessonDeleteController extends HttpServlet {
    private transient LessonService lessonService;

    @Override
    public void init() throws ServletException {
        super.init();
        this.lessonService = BeanContainer.getBean(LessonService.class);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.sendError(HttpServletResponse.SC_METHOD_NOT_ALLOWED, "Phương thức GET không được hỗ trợ cho endpoint này");
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