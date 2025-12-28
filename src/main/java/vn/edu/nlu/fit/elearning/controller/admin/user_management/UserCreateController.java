package vn.edu.nlu.fit.elearning.controller.admin.user_management;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import vn.edu.nlu.fit.elearning.enums.BasicStatus;
import vn.edu.nlu.fit.elearning.model.User;
import vn.edu.nlu.fit.elearning.services.UserService;
import vn.edu.nlu.fit.elearning.utils.PasswordUtils;

import java.io.IOException;

@WebServlet(name = "UserCreateController", value = "/admin/user/create")
public class UserCreateController extends HttpServlet {

    private UserService userService;

    public UserCreateController() {
        this.userService = new UserService();
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
        user.setRole(role);
        //ÉP kiểu string về ENUM
        BasicStatus statusEnum = BasicStatus.valueOf(status);
        user.setStatus(statusEnum);

        if (email.isEmpty() || username.isEmpty() || password.isEmpty()) {
            request.setAttribute("error", "Vui lòng nhập đầy đủ thông tin !");
            request.getRequestDispatcher("/html-authentication/sign-up.jsp").forward(request, response);
        }

        try {
            int check = userService.createUser(user);
            if (check > 0) {
                request.getSession().setAttribute("flashSuccess", "Tạo người dùng mới thành công !");
                response.sendRedirect(request.getContextPath() + "/admin/users");
            }
        } catch (IllegalArgumentException iae) {
            request.setAttribute("flashError", "Lỗi: " + iae.getMessage());
            request.getRequestDispatcher("/html-admin/user-create.jsp").forward(request, response);
        }


    }
}