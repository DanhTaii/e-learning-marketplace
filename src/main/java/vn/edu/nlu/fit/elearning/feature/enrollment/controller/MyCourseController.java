package vn.edu.nlu.fit.elearning.feature.enrollment.controller;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import vn.edu.nlu.fit.elearning.common.container.BeanContainer;
import vn.edu.nlu.fit.elearning.feature.enrollment.dto.EnrollmentCardDTO;
import vn.edu.nlu.fit.elearning.feature.enrollment.service.EnrollmentService;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "MyCourseController", value = "/personal/my-courses")
public class MyCourseController extends HttpServlet {

    private EnrollmentService enrollmentService;

    @Override
    public void init() throws ServletException {
        super.init();
        this.enrollmentService = BeanContainer.getBean(EnrollmentService.class);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        HttpSession session = request.getSession();
        int userId = 0;

        if (session != null && session.getAttribute("userId") != null) {
            userId = (Integer) session.getAttribute("userId");
        }
        List<EnrollmentCardDTO> enrollmentList = enrollmentService.getAllEnrollments(userId);

        request.setAttribute("listEnrollments", enrollmentList);
        request.getRequestDispatcher("/views/pages/personal/course/enrollment/my-course.jsp").forward(request, response);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}