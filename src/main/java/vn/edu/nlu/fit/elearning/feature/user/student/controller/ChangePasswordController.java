package vn.edu.nlu.fit.elearning.feature.user.student.controller;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import vn.edu.nlu.fit.elearning.common.container.BeanContainer;
import vn.edu.nlu.fit.elearning.feature.auth.service.AuthService;
import vn.edu.nlu.fit.elearning.feature.user.common.model.User;
import java.io.IOException;

@WebServlet(name = "ChangePasswordController", value = "/change-password")
public class ChangePasswordController extends HttpServlet {
    private AuthService AuthService;

    @Override
    public void init() throws ServletException {
        super.init();
        this.AuthService = BeanContainer.getBean(AuthService.class);
    }
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //Lấy ra người dùng hiện tại đnag trong session - được setAttribute bên Login
        HttpSession session = request.getSession(false);
        User userSession = (User) session.getAttribute("userSession");

        String userMail = request.getParameter("userMail");
        String oldPasswordUser = request.getParameter("oldPassword");
        String newPasswordUser = request.getParameter("newPassword");
        String retypeNewPasswordUser = request.getParameter("newPasswordRetype");
//        Sử dụng try-catch để lấy message từ thằng Service quăng qua
        try {
            boolean isSuccess = AuthService.resetUserPassword(oldPasswordUser, newPasswordUser, retypeNewPasswordUser, userSession.getEmail());
            if (isSuccess) {
                request.setAttribute("userSession", userSession);
                request.getSession().setAttribute("flashSuccess", "Đổi mật khẩu thành công !");
                response.sendRedirect(request.getContextPath() + "/personal/account-security");
            }
        } catch (IllegalArgumentException iae) {
            request.getSession().setAttribute("flashError",  iae.getMessage());
            request.setAttribute("userSession", userSession);
            response.sendRedirect(request.getContextPath() + "/personal/account-security");
        }

    }
}