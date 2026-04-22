package vn.edu.nlu.fit.elearning.feature.lesson.controller.admin;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import vn.edu.nlu.fit.elearning.common.base.BaseController;
import vn.edu.nlu.fit.elearning.common.container.BeanContainer;
import vn.edu.nlu.fit.elearning.feature.lesson.service.LessonService;

import java.io.IOException;

@WebServlet(name = "LessonAchievementController", value = "/admin/lessons/archive")
public class LessonAchievementController extends BaseController {

    private transient LessonService lessonService;

    @Override
    public void init() throws ServletException {
        super.init();
        this.lessonService = BeanContainer.getBean(LessonService.class);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.forward(request,response, "/views/pages/admin/lesson/archive/lesson-archive.jsp");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}