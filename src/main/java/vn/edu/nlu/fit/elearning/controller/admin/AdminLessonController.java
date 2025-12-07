package vn.edu.nlu.fit.elearning.controller.admin;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import vn.edu.nlu.fit.elearning.model.Lesson;
import vn.edu.nlu.fit.elearning.services.LessonService;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "AdminLessonController", value = "/admin/lesson")
public class AdminLessonController extends HttpServlet {

    private LessonService lessonService;

    public AdminLessonController() {
        this.lessonService = new LessonService();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Lesson> listLessons = lessonService.getAllLessons();
        request.setAttribute("listLessons",listLessons);
        request.getRequestDispatcher("/html-admin/lesson-management.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}