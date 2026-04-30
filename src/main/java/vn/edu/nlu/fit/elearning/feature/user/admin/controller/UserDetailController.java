package vn.edu.nlu.fit.elearning.feature.user.admin.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import vn.edu.nlu.fit.elearning.common.container.BeanContainer;
import vn.edu.nlu.fit.elearning.common.helper.enums.BaseStatus;
import vn.edu.nlu.fit.elearning.common.helper.enums.Role;
import vn.edu.nlu.fit.elearning.common.utils.security.PasswordUtils;
import vn.edu.nlu.fit.elearning.feature.user.admin.service.UserAdminService;
import vn.edu.nlu.fit.elearning.feature.user.common.model.User;
import vn.edu.nlu.fit.elearning.feature.user.student.service.UserService;

import java.io.IOException;

@WebServlet(name = "UserDetailController", value = "/admin/user/detail")
public class UserDetailController extends HttpServlet {
    private UserAdminService userAdminService;

    @Override
    public void init() throws ServletException {
        super.init();
        this.userAdminService = BeanContainer.getBean(UserAdminService.class);
    }
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String email = request.getParameter("email");
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        String role = request.getParameter("role");
        String status = request.getParameter("status");
        String phone = request.getParameter("phone");

        User user = new User();
        user.setEmail(email);
        user.setUsername(username);
        //Hashpassword
        String hashpassword = PasswordUtils.hashpassword(password);
        user.setPassword(hashpassword);
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setPhone(phone);
        Role userRole = Role.valueOf(role);
        user.setRole(userRole);
        //ÉP kiểu string về ENUM
        BaseStatus statusEnum = BaseStatus.valueOf(status);
        user.setStatus(statusEnum);

        if (email.isEmpty() || username.isEmpty() || password.isEmpty()) {
            request.setAttribute("error", "Vui lòng nhập đầy đủ thông tin !");
            request.getRequestDispatcher("/views/pages/auth/sign-up.jsp").forward(request, response);
        }

        try {
            int check = userAdminService.createUser(user);
            if (check > 0) {
                request.getSession().setAttribute("flashSuccess", "Tạo người dùng mới thành công !");
                response.sendRedirect(request.getContextPath() + "/admin/users");
            }
        } catch (IllegalArgumentException iae) {
            request.setAttribute("flashError", "Lỗi: " + iae.getMessage());
            request.getRequestDispatcher("/views/pages/admin/user-create.jsp").forward(request, response);
        }


    }

}