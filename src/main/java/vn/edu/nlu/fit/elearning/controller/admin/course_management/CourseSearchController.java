package vn.edu.nlu.fit.elearning.controller.admin.course_management;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import vn.edu.nlu.fit.elearning.model.Course;
import vn.edu.nlu.fit.elearning.services.CourseService;
import vn.edu.nlu.fit.elearning.services.UserService;
import vn.edu.nlu.fit.elearning.utils.CourseFilter;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "CourseSearchController", value = "/admin/course/search")
public class CourseSearchController extends HttpServlet {
    private CourseService courseService;


    public CourseSearchController() {
        this.courseService = new CourseService();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        CourseFilter courseFilter = new CourseFilter();
        courseFilter.setTitle(request.getParameter("courseTitle"));
        courseFilter.setCreatedAt(request.getParameter("dateFrom"));

        String isPublicParam = request.getParameter("isPublic");
        if ("public".equals(isPublicParam)) {
            courseFilter.setPublic(true);
        } else if ("private".equals(isPublicParam)) {
            courseFilter.setPublic(false);
        } else {
            courseFilter.setPublic(null); // Chọn "Tất cả"
        }
        courseFilter.setLevel(request.getParameter("level"));

        List<Course> listCourses = courseService.getAllCourses(courseFilter);

        System.out.println(listCourses);
        request.setAttribute("listCourses", listCourses);
        request.setAttribute("currentPage", "courses");
        request.getRequestDispatcher("/html-admin/courses-management.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}