package vn.edu.nlu.fit.elearning.controller.auth;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import vn.edu.nlu.fit.elearning.model.User;
import vn.edu.nlu.fit.elearning.services.UserService;

import java.io.IOException;

@WebServlet(name = "SignUpController", value = "/sign-up")
public class SignUpController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String email = request.getParameter("email");
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String confirmPassword = request.getParameter("confirmPassword");


        if (email == null || email.isEmpty() || username == null || username.isEmpty() || password == null || password.isEmpty() || confirmPassword == null || confirmPassword.isEmpty()) {
            request.setAttribute("error", "Vui lòng nhập đầy đủ email, tên đăng nhập, mật khẩu và xác nhận mật khẩu!");
            request.getRequestDispatcher("/html-authentication/sign-up.jsp").forward(request, response);
            return;
        }
        if (!password.equals(confirmPassword)) {
            request.setAttribute("error", "Mật khẩu và xác nhận mật khẩu không khớp!");
            request.getRequestDispatcher("/html-authentication/sign-up.jsp").forward(request, response);
            return;
        }

        UserService userService = new UserService();
        User user = new User();
        user.setEmail(email);
        user.setUsername(username);
        user.setPassword(password);

        boolean success = userService.createUser(user);
        if (success) {
            response.sendRedirect(request.getContextPath() + "/html-authentication/sign-in.jsp");
        } else {
            request.setAttribute("error", "Email đã tồn tại!");
            request.getRequestDispatcher("/html-authentication/sign-up.jsp").forward(request, response);
        }

    }
}