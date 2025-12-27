package vn.edu.nlu.fit.elearning.controller.admin.course_management;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import vn.edu.nlu.fit.elearning.model.Course;
import vn.edu.nlu.fit.elearning.services.CourseService;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "AdminCourseController", value = "/admin/courses")
public class AdminCourseController extends HttpServlet {
    private CourseService courseService;

    public AdminCourseController() {
        this.courseService = new CourseService();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Course> listCourses = courseService.getAllCourses();
        request.setAttribute("listCourses",listCourses);
        request.setAttribute("currentPage", "courses");
        request.getRequestDispatcher("/html-admin/courses-management.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}