package vn.edu.nlu.fit.elearning.feature.auth.controller;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import vn.edu.nlu.fit.elearning.common.container.BeanContainer;
import vn.edu.nlu.fit.elearning.feature.access_token.service.AccessTokenService;
import vn.edu.nlu.fit.elearning.feature.auth.service.AuthServiceImpl;
import vn.edu.nlu.fit.elearning.feature.auth.service.AuthService;
import vn.edu.nlu.fit.elearning.feature.user.dto.UserShortDto;
import vn.edu.nlu.fit.elearning.feature.user.model.User;
import vn.edu.nlu.fit.elearning.feature.user.service.UserService;
import java.io.IOException;

@WebServlet(name = "CheckMailController", value = "/check-email")
public class CheckMailController extends HttpServlet {

    private AuthService AuthService;
    private AccessTokenService AccessTokenService;
    private UserService userService;

    @Override
    public void init() throws ServletException {
        this.AuthService = BeanContainer.getBean(AuthService.class);
        this.AccessTokenService =BeanContainer.getBean(AccessTokenService.class);
        this.userService =BeanContainer.getBean(UserService.class);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        HttpSession session = request.getSession(false);
        if (session == null ||
                (session.getAttribute("resetEmail") == null && session.getAttribute("signupEmail") == null)) {
            response.sendRedirect(request.getContextPath() + "/sign-up");
            return;
        }

        request.getRequestDispatcher("/views/pages/auth/check-email.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        if (session == null ||
                (session.getAttribute("resetEmail") == null && session.getAttribute("signupEmail") == null)) {
            response.sendRedirect(request.getContextPath() + "/sign-up");
            return;
        }

        String otp = request.getParameter("otp");
        if (otp == null || otp.trim().length() != 5) {
            request.setAttribute("error", "Vui lòng nhập đúng mã 5 chữ số!");
            request.getRequestDispatcher("/views/pages/auth/check-email.jsp").forward(request, response);
            return;
        }

        // Trường hợp quên mật khẩu
        if (session.getAttribute("resetEmail") != null) {
            String email = (String) session.getAttribute("resetEmail");
            UserShortDto user = userService.getUserByEmail(email);
            if (user == null) {
                request.setAttribute("error", "Không tìm thấy tài khoản!");
                request.getRequestDispatcher("/views/pages/auth/check-email.jsp").forward(request, response);
                return;
            }

            boolean isValid = AccessTokenService.validateResetToken(user.getId(), otp);
            if (isValid) {
                AccessTokenService.markAsUsed(otp);
                session.setAttribute("resetUserId", user.getId());
                session.setAttribute("userMail", user.getEmail()); // thêm dòng này để ResetPasswordController dùng
                response.sendRedirect(request.getContextPath() + "/reset-password");
            } else {
                request.setAttribute("error", "Mã xác thực không đúng hoặc đã hết hạn!");
                request.getRequestDispatcher("/views/pages/auth/check-email.jsp").forward(request, response);
            }
        }

        // Trường hợp đăng ký
        else if (session.getAttribute("signupEmail") != null) {
            String email = (String) session.getAttribute("signupEmail");
            String username = (String) session.getAttribute("signupUsername");
            String password = (String) session.getAttribute("signupPassword");

            boolean isValid = AccessTokenService.validateSignupToken(otp);
            if (isValid) {
                AccessTokenService.markAsUsed(otp);

                boolean created = AuthService.register(email.trim(), username.trim(), password.trim());
                if (created) {
                    session.removeAttribute("signupEmail");
                    session.removeAttribute("signupUsername");
                    session.removeAttribute("signupPassword");
                    request.setAttribute("success", "Xác nhận thành công! Bạn có thể đăng nhập.");
                    response.sendRedirect(request.getContextPath() + "/sign-in");
                } else {
                    request.setAttribute("error", "Không thể tạo tài khoản!");
                    request.getRequestDispatcher("/views/pages/auth/check-email.jsp").forward(request, response);
                }
            } else {
                request.setAttribute("error", "Mã xác thực không đúng hoặc đã hết hạn!");
                request.getRequestDispatcher("/views/pages/auth/check-email.jsp").forward(request, response);
            }
        }
    }
}

