package vn.edu.nlu.fit.elearning.controller.partrial.allCourses;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import vn.edu.nlu.fit.elearning.dto.CourseCardDto;
import vn.edu.nlu.fit.elearning.model.Course;
import vn.edu.nlu.fit.elearning.services.CourseService;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "AllCourses", value = "/all-courses")
public class AllCoursesController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        CourseService cs = new CourseService();
        List<CourseCardDto> allCourses = cs.getCourseCards();
        request.setAttribute("listCourse", allCourses);
        request.getRequestDispatcher("/html-partrial/all-course.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}