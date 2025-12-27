package vn.edu.nlu.fit.elearning.controller.admin.lesson_management;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import vn.edu.nlu.fit.elearning.model.Course;
import vn.edu.nlu.fit.elearning.model.Lesson;
import vn.edu.nlu.fit.elearning.services.CourseService;
import vn.edu.nlu.fit.elearning.services.LessonService;
import vn.edu.nlu.fit.elearning.services.TagService;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "LessonSearchController", value = "/admin/lesson/search")
public class LessonSearchController extends HttpServlet {
    private LessonService lessonService;
    private CourseService courseService;

    public LessonSearchController() {
        this.lessonService = new LessonService();
        this.courseService = new CourseService();
    }




    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String nameSearch = request.getParameter("searchName");
        String courseIdStr = request.getParameter("courseId");
        List<Lesson> listLesson = lessonService.getSearchLessons(nameSearch, courseIdStr);
        request.setAttribute("listLessons", listLesson);
        List<Course> listCourses = courseService.getAllCourses();
        request.setAttribute("listCourse", listCourses);
        request.getRequestDispatcher("/html-admin/lesson-management.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}