package vn.edu.nlu.fit.elearning.feature.auth.controller;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import vn.edu.nlu.fit.elearning.common.container.BeanContainer;
import vn.edu.nlu.fit.elearning.feature.auth.service.AuthService;
import java.io.IOException;

@WebServlet(name = "ResetPasswordController", value = "/reset-password")
public class ResetPasswordController extends HttpServlet {
    private AuthService authService;

    @Override
    public void init() throws ServletException {
        super.init();
        this.authService = BeanContainer.getBean(AuthService.class);
    }


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        request.getRequestDispatcher("/views/pages/auth/reset-password.jsp").forward(request, response);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();

        String password = request.getParameter("password");
        String retypePassword = request.getParameter("retypePassword");
        String userMail = (String) session.getAttribute("userMail");

//        System.out.println("===> DO POST RESET PASSWORD");
//        System.out.println("password = " + password);
//        System.out.println("retype = " + retypePassword);
//        System.out.println("userMail = " + userMail);

        try {
            boolean isSuccess = authService.changePassword(password, retypePassword, userMail);
            if (isSuccess) {
                response.setStatus(200);
                response.sendRedirect(request.getContextPath() + "/sign-in");
            }
        } catch (IllegalArgumentException iae) {
            request.setAttribute("error", "Lỗi: " + iae.getMessage());
            request.getRequestDispatcher("/views/pages/auth/reset-password.jsp").forward(request, response);
        }

    }
}