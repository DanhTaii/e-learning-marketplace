package vn.edu.nlu.fit.elearning.controller.partrial;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import vn.edu.nlu.fit.elearning.model.Course;
import vn.edu.nlu.fit.elearning.services.CourseService;

import java.io.IOException;
import java.util.ArrayList;

@WebServlet(name = "AllCourses", value = "/all-courses")
public class AllCoursesController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        CourseService cs = new CourseService();
        ArrayList<Course> allCourses = (ArrayList<Course>) cs.getAllCourses();
        request.setAttribute("list", allCourses);
        request.getRequestDispatcher("/html-partrial/all-course.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}