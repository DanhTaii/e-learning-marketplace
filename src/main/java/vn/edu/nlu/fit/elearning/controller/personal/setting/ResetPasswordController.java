package vn.edu.nlu.fit.elearning.controller.personal.setting;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import vn.edu.nlu.fit.elearning.model.User;
import vn.edu.nlu.fit.elearning.services.UserService;

import java.io.IOException;

@WebServlet(name = "ResetPasswordController", value = "/reset-password")
public class ResetPasswordController extends HttpServlet {
    private UserService userService;

    @Override
    public void init() throws ServletException {
        super.init();
        this.userService = new UserService();
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
            boolean isSuccess = userService.resetUserPassword(oldPasswordUser, newPasswordUser, retypeNewPasswordUser, userSession.getEmail());
            if (isSuccess) {
                request.setAttribute("userSession", userSession);
                request.getSession().setAttribute("success", "Đổi lại mật khẩu thành công !");
                response.sendRedirect(request.getContextPath() + "/personal/account-security");
            }
        } catch (IllegalArgumentException iae) {
            request.setAttribute("error", "Lỗi: " + iae.getMessage());
            request.setAttribute("userSession", userSession);
            request.getRequestDispatcher("/html-personal/account-security.jsp").forward(request, response);
        }

    }
}