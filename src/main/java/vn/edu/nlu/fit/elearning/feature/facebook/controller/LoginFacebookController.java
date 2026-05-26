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
            if (code == null || code.isEmpty()) {
                response.sendRedirect(request.getContextPath() + "/sign-in?error=failed");
                return;
            }

            // redirect uri đúng môi trường
            String currentRedirectUri = FacebookConstants.getRedirectUri();

            // đổi code lấy token
            String accessToken = FacebookUtils.getToken(code, currentRedirectUri);

            // lấy user info
            FacebookUser facebookUser = FacebookUtils.getUserInfo(accessToken);

            // xử lý login/register
            User user = authService.processFacebookLogin(facebookUser);

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