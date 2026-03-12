package vn.edu.nlu.fit.elearning.feature.course.controller.admin;

import com.google.gson.Gson;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import vn.edu.nlu.fit.elearning.feature.course.model.Course;
import vn.edu.nlu.fit.elearning.feature.course.service.CourseService;
import vn.edu.nlu.fit.elearning.helper.pagination.PageResponse;
import vn.edu.nlu.fit.elearning.utils.objects.CourseFilter;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "AdminCourseController", value = "/admin/courses")
public class AdminCourseController extends HttpServlet {
//    private CourseService courseService;
//
//    @Override
//    public void init() throws ServletException {
//        super.init();
//        this.courseService = new CourseService();
//    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("currentPage", "courses");
        request.getRequestDispatcher("/views/pages/admin/course/courses-management.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}