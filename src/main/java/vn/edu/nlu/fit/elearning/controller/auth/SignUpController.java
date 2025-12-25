package vn.edu.nlu.fit.elearning.controller.auth;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import vn.edu.nlu.fit.elearning.model.User;
import vn.edu.nlu.fit.elearning.services.UserService;

import java.io.IOException;

@WebServlet(name = "SignUpController", value = "/sign-up")
public class SignUpController extends HttpServlet {
    private UserService userService;

    public SignUpController() {
        this.userService = new UserService();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String email = request.getParameter("email");
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String confirmPassword = request.getParameter("confirmPassword");

        if (email.isEmpty() || username.isEmpty() || password.isEmpty() || confirmPassword.isEmpty()) {
            request.setAttribute("error", "Vui lòng nhập đầy đủ thông tin !");
            request.getRequestDispatcher("/html-authentication/sign-up.jsp").forward(request, response);
        }

        if (!password.equals(confirmPassword)) {
            request.setAttribute("error", "Mật khẩu và mật khẩu xác nhận không khớp!");
            request.getRequestDispatcher("/html-authentication/sign-up.jsp").forward(request, response);
        }

        try {
            boolean isSuccess = userService.register(email, username, password);
            if (isSuccess) {
                response.sendRedirect(request.getContextPath() + "/html-authentication/sign-in.jsp");
            }
        } catch (IllegalArgumentException iae) {
            request.setAttribute("error", "Lỗi: " + iae.getMessage());
            request.getRequestDispatcher("/html-authentication/sign-up.jsp").forward(request, response);
        }
    }
}