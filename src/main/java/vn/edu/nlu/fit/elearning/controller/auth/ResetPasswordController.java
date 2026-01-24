package vn.edu.nlu.fit.elearning.controller.auth;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import vn.edu.nlu.fit.elearning.model.Category;
import vn.edu.nlu.fit.elearning.services.CategoryService;
import vn.edu.nlu.fit.elearning.services.TagService;
import vn.edu.nlu.fit.elearning.services.UserService;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "ResetPasswordController", value = "/reset-password")
public class ResetPasswordController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        // này là làm để phần danh mục ở header hiện đc nội dung bên trong
        CategoryService categoryService = new CategoryService();
        List<Category> categories = categoryService.getAllCategories();
        request.setAttribute("categories", categories);
        TagService tagService = new TagService();
        request.setAttribute("tags", tagService.getAllTags());

        request.getRequestDispatcher("/html-authentication/reset-password.jsp").forward(request, response);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        UserService userService = new UserService();
        HttpSession session = request.getSession();

        String password = request.getParameter("password");
        String retypePassword = request.getParameter("retypePassword");
        String userMail = (String) session.getAttribute("userMail");

//        System.out.println("===> DO POST RESET PASSWORD");
//        System.out.println("password = " + password);
//        System.out.println("retype = " + retypePassword);
//        System.out.println("userMail = " + userMail);

        try {
            boolean isSuccess = userService.changePassword(password, retypePassword, userMail);
            if (isSuccess) {
                response.setStatus(200);
                response.sendRedirect(request.getContextPath() + "/sign-in");
            }
        } catch (IllegalArgumentException iae) {
            request.setAttribute("error", "Lỗi: " + iae.getMessage());
            request.getRequestDispatcher("/html-authentication/reset-password.jsp").forward(request, response);
        }

    }
}