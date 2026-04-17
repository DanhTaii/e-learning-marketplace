package vn.edu.nlu.fit.elearning.feature.auth.controller;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import vn.edu.nlu.fit.elearning.common.container.BeanContainer;
import vn.edu.nlu.fit.elearning.common.external.mail.MailService;
import vn.edu.nlu.fit.elearning.common.helper.validator.sign_up.SignUpValidator;
import vn.edu.nlu.fit.elearning.feature.access_token.model.AccessToken;
import vn.edu.nlu.fit.elearning.feature.access_token.service.AccessTokenService;
import vn.edu.nlu.fit.elearning.feature.auth.service.AuthService;
import vn.edu.nlu.fit.elearning.feature.user.dto.response.UserShortResponse;
import vn.edu.nlu.fit.elearning.feature.user.service.UserService;
import java.io.IOException;
import java.util.Map;

@WebServlet(name = "SignUpController", value = "/sign-up")
public class SignUpController extends HttpServlet {
    private transient UserService userService;
    private transient AccessTokenService accessTokenService;
    private transient AuthService authService;

    @Override
    public void init() throws ServletException {
        super.init();
        this.userService = BeanContainer.getBean(UserService.class);
        this.accessTokenService = BeanContainer.getBean(AccessTokenService.class);
        this.authService = BeanContainer.getBean(AuthService.class);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        request.getRequestDispatcher("/views/pages/auth/sign-up.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        try {
            String email = request.getParameter("email");
            String username = request.getParameter("username");
            String password = request.getParameter("password");
            String confirmPassword = request.getParameter("confirmPassword");

            Map<String, String> errors = SignUpValidator.validate(
                    email, username, password, confirmPassword, userService
            );

            if (!errors.isEmpty()) {
                request.setAttribute("errors", errors);
                request.getRequestDispatcher("/views/pages/auth/sign-up.jsp").forward(request, response);
                return;
            }

            // Tạo token xác thực
            String token = MailService.generateToken();
            AccessToken accessToken = new AccessToken(
                    0, // chưa có userId vì chưa tạo user
                    token,
                    accessTokenService.expireDateTime(),
                    false
            );

            if (accessTokenService.createToken(accessToken) == 0) {
                throw new RuntimeException("Không thể tạo token xác thực!");
            }

            // Gửi email chứa mã
            if (!MailService.sendEmail(email, token, username)) {
                throw new RuntimeException("Gửi email xác nhận thất bại!");
            }

            // Lưu thông tin đăng ký vào session để dùng ở CheckEmailController
            HttpSession session = request.getSession();
            session.setAttribute("signupEmail", email);
            session.setAttribute("signupUsername", username);
            session.setAttribute("signupPassword", password);
            session.setMaxInactiveInterval(10 * 60); // 10 phút

            response.sendRedirect(request.getContextPath() + "/check-email");

        } catch (IllegalArgumentException e) {
            request.setAttribute("error", e.getMessage());
            request.getRequestDispatcher("/views/pages/auth/sign-up.jsp").forward(request, response);

        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("error", "Lỗi hệ thống, vui lòng thử lại sau!");
            request.getRequestDispatcher("/views/pages/auth/sign-up.jsp").forward(request, response);
        }
    }
}