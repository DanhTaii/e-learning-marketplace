package vn.edu.nlu.fit.elearning.feature.course.admin.controller;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import vn.edu.nlu.fit.elearning.common.base.BaseController;
import vn.edu.nlu.fit.elearning.common.container.BeanContainer;
import vn.edu.nlu.fit.elearning.feature.course.admin.service.CourseAdminService;

import java.io.IOException;

@WebServlet(name = "CourseArchiveController", value = "/admin/courses/archive")
public class CourseAdminArchiveController extends BaseController {

    private transient CourseAdminService courseAdminService;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        this.courseAdminService = BeanContainer.getBean(CourseAdminService.class);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("currentPage", "courses");
        request.setAttribute("currentPageArchive", "courses");
        this.forward(request, response, "/views/pages/admin/course/archive/course-archive.jsp");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}