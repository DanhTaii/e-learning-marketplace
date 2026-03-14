package vn.edu.nlu.fit.elearning.feature.auth.controller;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import vn.edu.nlu.fit.elearning.feature.access_token.dao.AccessTokenDaoImpl;
import vn.edu.nlu.fit.elearning.feature.access_token.dao.AccessTokenDao;
import vn.edu.nlu.fit.elearning.feature.access_token.model.AccessToken;
import vn.edu.nlu.fit.elearning.feature.access_token.service.AccessTokenService;
import vn.edu.nlu.fit.elearning.feature.category.model.Category;
import vn.edu.nlu.fit.elearning.feature.category.service.ICategoryService;
import vn.edu.nlu.fit.elearning.feature.tag.service.TagService;
import vn.edu.nlu.fit.elearning.feature.user.model.User;
import vn.edu.nlu.fit.elearning.feature.access_token.service.AccessTokenServiceImpl;
import vn.edu.nlu.fit.elearning.feature.category.service.CategoryService;
import vn.edu.nlu.fit.elearning.feature.tag.service.TagServiceImpl;
import vn.edu.nlu.fit.elearning.feature.user.service.UserService;
import vn.edu.nlu.fit.elearning.feature.user.service.UserServiceImpl;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "SignUpController", value = "/sign-up")
public class SignUpController extends HttpServlet {
    private UserService userService;
    private AccessTokenService AccessTokenService = new AccessTokenServiceImpl();
    private AccessTokenDao tokenDao = new AccessTokenDaoImpl();

    @Override
    public void init() throws ServletException {
        super.init();
        this.userService = new UserServiceImpl();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // load categories/tags cho header
        ICategoryService ICategoryService = new CategoryService();
        List<Category> categories = ICategoryService.getAllCategories();
        request.setAttribute("categories", categories);
        TagService tagService = new TagServiceImpl();
        request.setAttribute("tags", tagService.getAllTags());

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

            if (email.isEmpty() || username.isEmpty() || password.isEmpty() || confirmPassword.isEmpty()) {
                throw new IllegalArgumentException("Vui lòng nhập đầy đủ thông tin!");
            }

            if (!password.equals(confirmPassword)) {
                throw new IllegalArgumentException("Mật khẩu và mật khẩu xác nhận không khớp!");
            }

            User user = null;
            if ((user = userService.getUserByEmail(email)) != null) {
                throw new IllegalArgumentException("Email đã tồn tại!");
            }

            if ((user = userService.getUserByUsername(username)) != null) {
                throw new IllegalArgumentException("Tên người dùng đã tồn tại !");
            }

            // Tạo token xác thực
            String token = AccessTokenService.generateToken();
            AccessToken accessToken = new AccessToken(
                    0, // chưa có userId vì chưa tạo user
                    token,
                    AccessTokenService.expireDateTime(),
                    false
            );

            if (!tokenDao.createToken(accessToken)) {
                throw new RuntimeException("Không thể tạo token xác thực!");
            }

            // Gửi email chứa mã
            if (!AccessTokenService.sendEmail(email, token, username)) {
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