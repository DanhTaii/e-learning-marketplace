package vn.edu.nlu.fit.elearning.feature.auth.controller;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import vn.edu.nlu.fit.elearning.feature.auth.service.AuthService;
import vn.edu.nlu.fit.elearning.feature.category.model.Category;
import vn.edu.nlu.fit.elearning.feature.user.model.User;
import vn.edu.nlu.fit.elearning.feature.access_token.service.AccessTokenService;
import vn.edu.nlu.fit.elearning.feature.category.service.CategoryService;
import vn.edu.nlu.fit.elearning.feature.tag.service.TagService;
import vn.edu.nlu.fit.elearning.feature.user.service.UserService;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "CheckMailController", value = "/check-email")
public class CheckMailController extends HttpServlet {

    private AuthService authService;
    private AccessTokenService accessTokenService;
    private  UserService userService;

    @Override
    public void init() throws ServletException {
        this.authService = new AuthService();
        this.accessTokenService = new AccessTokenService();
        this.userService = new UserService();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        CategoryService categoryService = new CategoryService();
        List<Category> categories = categoryService.getAllCategories();
        request.setAttribute("categories", categories);

        TagService tagService = new TagService();
        request.setAttribute("tags", tagService.getAllTags());

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
            User user = userService.getUserByEmail(email);
            if (user == null) {
                request.setAttribute("error", "Không tìm thấy tài khoản!");
                request.getRequestDispatcher("/views/pages/auth/check-email.jsp").forward(request, response);
                return;
            }

            boolean isValid = accessTokenService.validateResetToken(user.getId(), otp);
            if (isValid) {
                accessTokenService.markAsUsed(otp);
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

            boolean isValid = accessTokenService.validateSignupToken(otp);
            if (isValid) {
                accessTokenService.markAsUsed(otp);

                boolean created = authService.register(email.trim(), username.trim(), password.trim());
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

