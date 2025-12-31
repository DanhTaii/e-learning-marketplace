package vn.edu.nlu.fit.elearning.controller.personal;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import vn.edu.nlu.fit.elearning.dto.EnrollmentCardDTO;
import vn.edu.nlu.fit.elearning.model.User;
import vn.edu.nlu.fit.elearning.services.EnrollmentService;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "MyCourseController", value = "/my-courses")
public class MyCourseController extends HttpServlet {

    private EnrollmentService enrollmentService;

    @Override
    public void init() throws ServletException {
        super.init();
        this.enrollmentService = new EnrollmentService();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User user = (User) request.getSession().getAttribute("userSession");

        int userId = user.getId();

        List<EnrollmentCardDTO> enrollmentList = enrollmentService.getAllEnrollments(userId);

        request.setAttribute("listEnrollments", enrollmentList);
        request.getRequestDispatcher("html-personal/my-course.jsp").forward(request, response);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}