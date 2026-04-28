package vn.edu.nlu.fit.elearning.feature.course.controller.admin;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import vn.edu.nlu.fit.elearning.common.base.BaseController;

import java.io.IOException;

@WebServlet(name = "CourseArchiveController", value = "/admin/courses/archive")
public class CourseArchiveController extends BaseController {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("currentPage", "courses");
        request.setAttribute("currentPageArchive", "courses");
        this.forward(request,response, "/views/pages/admin/course/archive/course-archive.jsp");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}