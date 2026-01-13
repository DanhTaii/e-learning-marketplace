package vn.edu.nlu.fit.elearning.controller.auth;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import vn.edu.nlu.fit.elearning.dao.AccessTokenDao;
import vn.edu.nlu.fit.elearning.model.AccessToken;
import vn.edu.nlu.fit.elearning.model.User;
import vn.edu.nlu.fit.elearning.services.AccessTokenService;
import vn.edu.nlu.fit.elearning.services.UserService;

import java.io.IOException;

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

        // nếu không có token thì hiển thị form đăng ký
        request.getRequestDispatcher("/html-authentication/sign-up.jsp").forward(request, response);
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
            return;
        }

        if (!password.equals(confirmPassword)) {
            request.setAttribute("error", "Mật khẩu và mật khẩu xác nhận không khớp!");
            request.getRequestDispatcher("/html-authentication/sign-up.jsp").forward(request, response);
            return;
        }

        User user = userService.getUserByEmail(email);
        if (user != null) {
            request.setAttribute("error", "Email đã tồn tại");
            request.getRequestDispatcher("/html-authentication/sign-up.jsp").forward(request, response);
            return;
        }

        // Đăng ký user mới
        boolean isSuccess = userService.register(email, username, password);
        if (!isSuccess) {
            request.setAttribute("error", "Đăng ký thất bại!");
            request.getRequestDispatcher("/html-authentication/sign-up.jsp").forward(request, response);
            return;
        }

        // Lấy lại user vừa tạo để có id
        user = userService.getUserByEmail(email);

        // Tạo token xác thực
        String token = accessTokenService.generateTokenForVerify();
        AccessToken accessToken = new AccessToken(user.getId(), token, accessTokenService.expireDateTime(), false);

        boolean isCreate = tokenDao.createToken(accessToken);
        if (!isCreate) {
            request.setAttribute("error", "Lỗi server");
            request.getRequestDispatcher("/html-authentication/sign-up.jsp").forward(request, response);
            return;
        }

        // Gửi email xác nhận
        boolean isSend = accessTokenService.sendEmail(email, token, username, true);
        if (!isSend) {
            request.setAttribute("error", "Gửi không thành công!");
            request.getRequestDispatcher("/html-authentication/sign-up.jsp").forward(request, response);
            return;
        }

        // Không redirect sang sign-in, chỉ báo kiểm tra email
        request.setAttribute("success", "Đăng ký thành công! Vui lòng kiểm tra email để xác nhận.");
        request.getRequestDispatcher("/html-authentication/sign-up.jsp").forward(request, response);
    }
}
