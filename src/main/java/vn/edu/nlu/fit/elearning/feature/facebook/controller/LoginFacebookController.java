package vn.edu.nlu.fit.elearning.feature.facebook.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import org.slf4j.Logger;

import vn.edu.nlu.fit.elearning.common.container.BeanContainer;
import vn.edu.nlu.fit.elearning.feature.auth.service.AuthService;
import vn.edu.nlu.fit.elearning.feature.facebook.model.FacebookUser;
import vn.edu.nlu.fit.elearning.feature.facebook.service.FacebookConstants;
import vn.edu.nlu.fit.elearning.feature.facebook.service.FacebookUtils;
import vn.edu.nlu.fit.elearning.feature.user.common.model.User;
import vn.edu.nlu.fit.elearning.feature.user.student.dto.response.UserShortResponse;

import java.io.IOException;

@WebServlet(name = "LoginFacebookController", value = "/sign-in/facebook")
public class LoginFacebookController extends HttpServlet {

    private AuthService authService;
    private static final Logger logger = org.slf4j.LoggerFactory.getLogger(LoginFacebookController.class);

    @Override
    public void init() throws ServletException {
        super.init();
        this.authService = BeanContainer.getBean(AuthService.class);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            String code = request.getParameter("code");
            System.out.println("👉 Bước 1 - Mã Code nhận từ FB: " + code);

            if (code == null || code.isEmpty()) {
                response.sendRedirect(request.getContextPath() + "/sign-in?error=failed");
                return;
            }

            String currentRedirectUri = FacebookConstants.getRedirectUri();

            // 1. Đổi code lấy token
            String accessToken = FacebookUtils.getToken(code, currentRedirectUri);

            // 2. Lấy user info
            FacebookUser facebookUser = FacebookUtils.getUserInfo(accessToken);

            // === IN RA ĐỂ KIỂM TRA OBJECT TRƯỚC KHI LƯU DB ===
            System.out.println("=========================================");
            if (facebookUser != null) {
                System.out.println("Bước 4 - Dữ liệu Object FacebookUser:");
                System.out.println(" - ID: " + facebookUser.getId());
                System.out.println(" - First Name: " + facebookUser.getFirstName());
                System.out.println(" - Last Name: " + facebookUser.getLastName());
                System.out.println(" - Email: " + facebookUser.getEmail());
            } else {
                System.out.println(" Object facebookUser bị NULL hoàn toàn!");
            }
            System.out.println("=========================================");

            // xử lý login/register
            UserShortResponse user = authService.processFacebookLogin(facebookUser);

            HttpSession session = request.getSession();

            session.setAttribute("userSession", user);
            session.setAttribute("userId", user.getId());

            response.sendRedirect(request.getContextPath() + "/index");

        } catch (Exception e) {

            e.printStackTrace();
            logger.error("Lỗi khi xử lý đăng nhập Facebook: ", e);
            response.sendRedirect(request.getContextPath() + "/sign-in?error=failed");
        }
    }
}