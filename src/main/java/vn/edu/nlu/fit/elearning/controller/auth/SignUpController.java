package vn.edu.nlu.fit.elearning.controller.auth;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import vn.edu.nlu.fit.elearning.dao.AccessTokenDao;
import vn.edu.nlu.fit.elearning.model.AccessToken;
import vn.edu.nlu.fit.elearning.model.Category;
import vn.edu.nlu.fit.elearning.model.User;
import vn.edu.nlu.fit.elearning.services.AccessTokenService;
import vn.edu.nlu.fit.elearning.services.CategoryService;
import vn.edu.nlu.fit.elearning.services.TagService;
import vn.edu.nlu.fit.elearning.services.UserService;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "SignUpController", value = "/sign-up")
public class SignUpController extends HttpServlet {
    private UserService userService;
    private AccessTokenService accessTokenService = new AccessTokenService();
    private AccessTokenDao tokenDao = new AccessTokenDao();

    @Override
    public void init() throws ServletException {
        super.init();
        this.userService = new UserService();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // load categories/tags cho header
        CategoryService categoryService = new CategoryService();
        List<Category> categories = categoryService.getAllCategories();
        request.setAttribute("categories", categories);
        TagService tagService = new TagService();
        request.setAttribute("tags", tagService.getAllTags());

        request.getRequestDispatcher("/html-authentication/sign-up.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        try {
            String email = request.getParameter("email");
            String username = request.getParameter("username");
            String password = request.getParameter("password");
            String confirmPassword = request.getParameter("confirmPassword");

            if (email.isEmpty() || username.isEmpty() || password.isEmpty() || confirmPassword.isEmpty()) {
                throw new IllegalArgumentException("Vui lòng nhập đầy đủ thông tin!");
            }

            if (!password.equals(confirmPassword)) {
                throw new IllegalArgumentException("Mật khẩu và mật khẩu xác nhận không khớp!");
            }

            User user = userService.getUserByEmail(email);
            if (user != null) {
                throw new IllegalArgumentException("Email đã tồn tại!");
            }

            // Tạo token xác thực
            String token = accessTokenService.generateToken();
            AccessToken accessToken = new AccessToken(
                    0, // chưa có userId vì chưa tạo user
                    token,
                    accessTokenService.expireDateTime(),
                    false
            );

            if (!tokenDao.createToken(accessToken)) {
                throw new RuntimeException("Không thể tạo token xác thực!");
            }

            // Gửi email chứa mã
            if (!accessTokenService.sendEmail(email, token, username)) {
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
            request.getRequestDispatcher("/html-authentication/sign-up.jsp").forward(request, response);

        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("error", "Lỗi hệ thống, vui lòng thử lại sau!");
            request.getRequestDispatcher("/html-authentication/sign-up.jsp").forward(request, response);
        }
    }
}