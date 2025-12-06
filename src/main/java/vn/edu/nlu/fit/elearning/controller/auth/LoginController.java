package vn.edu.nlu.fit.elearning.controller.auth;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import vn.edu.nlu.fit.elearning.dao.UserDao;
import vn.edu.nlu.fit.elearning.services.UserService;

import java.io.IOException;

@WebServlet(name = "LoginController", value = "/sign-in")
public class LoginController extends HttpServlet {

    private UserService userService;

    public LoginController() {
        this.userService = new UserService();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String email = request.getParameter("email");
        String pass = request.getParameter("password");
        boolean canLogin = userService.login(email,pass);
        if(canLogin){
            response.sendRedirect(request.getContextPath() + "/html-partrial/home.jsp" );
        }
        else {
            request.setAttribute("error","Bạn nhập sai email hoặc mật khẩu!");
            request.getRequestDispatcher("/html-authentication/sign-in.jsp").forward(request, response);
        }
    }
}