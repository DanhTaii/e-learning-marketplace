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
        // xử lý khi người dùng click link xác nhận trong email
        String token = request.getParameter("token");
        if (token != null) {
            AccessToken accessToken = tokenDao.findByToken(token);
            if (accessToken != null
                    && !accessToken.isUsed()
                    && !accessTokenService.isExpireTime(accessToken.getExpiriTime())) {

                // đánh dấu token đã dùng
                tokenDao.markAsUsed(token);

                // thông báo thành công và cho phép đăng nhập
                request.setAttribute("success", "Xác nhận email thành công! Bạn có thể đăng nhập.");
                request.getRequestDispatcher("/html-authentication/sign-in.jsp").forward(request, response);
                return;
            } else {
                request.setAttribute("error", "Token không hợp lệ hoặc đã hết hạn.");
                request.getRequestDispatcher("/html-authentication/sign-up.jsp").forward(request, response);
                return;
            }
        }

        // này là làm để phần danh mục ở header hiện đc nội dung bên trong
        CategoryService categoryService = new CategoryService();
        List<Category> categories = categoryService.getAllCategories();
        request.setAttribute("categories", categories);
        TagService tagService = new TagService();
        request.setAttribute("tags", tagService.getAllTags());

        // nếu không có token thì hiển thị form đăng ký
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

            // Đăng ký user
            boolean isSuccess = userService.register(email, username, password);
            if (!isSuccess) {
                throw new RuntimeException("Đăng ký thất bại!");
            }

            // Lấy user vừa tạo
            user = userService.getUserByEmail(email);

            // Tạo token xác thực
            String token = accessTokenService.generateTokenForVerify();
            AccessToken accessToken = new AccessToken(
                    user.getId(),
                    token,
                    accessTokenService.expireDateTime(),
                    false
            );

            if (!tokenDao.createToken(accessToken)) {
                throw new RuntimeException("Không thể tạo token xác thực!");
            }

            // Gửi email
            if (!accessTokenService.sendEmail(email, token, username, true)) {
                throw new RuntimeException("Gửi email xác nhận thất bại!");
            }

            request.setAttribute("success", "Đăng ký thành công! Vui lòng kiểm tra email để xác nhận.");
            request.getRequestDispatcher("/html-authentication/sign-up.jsp").forward(request, response);

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