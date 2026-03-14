package vn.edu.nlu.fit.elearning.feature.auth.controller;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import vn.edu.nlu.fit.elearning.common.container.BeanContainer;
import vn.edu.nlu.fit.elearning.feature.auth.service.AuthServiceImpl;
import vn.edu.nlu.fit.elearning.feature.auth.service.AuthService;
import vn.edu.nlu.fit.elearning.feature.category.model.Category;
import vn.edu.nlu.fit.elearning.feature.category.service.CategoryService;
import vn.edu.nlu.fit.elearning.feature.tag.service.TagService;
import vn.edu.nlu.fit.elearning.feature.tag.service.TagServiceImpl;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "ResetPasswordController", value = "/reset-password")
public class ResetPasswordController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        // này là làm để phần danh mục ở header hiện đc nội dung bên trong
        CategoryService ICategoryService = BeanContainer.getBean(CategoryService.class);
        List<Category> categories = ICategoryService.getAllCategories();
        request.setAttribute("categories", categories);
        TagService tagService = new TagServiceImpl();
        request.setAttribute("tags", tagService.getAllTags());

        request.getRequestDispatcher("/views/pages/auth/reset-password.jsp").forward(request, response);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        AuthService AuthService = new AuthServiceImpl();
        HttpSession session = request.getSession();

        String password = request.getParameter("password");
        String retypePassword = request.getParameter("retypePassword");
        String userMail = (String) session.getAttribute("userMail");

//        System.out.println("===> DO POST RESET PASSWORD");
//        System.out.println("password = " + password);
//        System.out.println("retype = " + retypePassword);
//        System.out.println("userMail = " + userMail);

        try {
            boolean isSuccess = AuthService.changePassword(password, retypePassword, userMail);
            if (isSuccess) {
                response.setStatus(200);
                response.sendRedirect(request.getContextPath() + "/sign-in");
            }
        } catch (IllegalArgumentException iae) {
            request.setAttribute("error", "Lỗi: " + iae.getMessage());
            request.getRequestDispatcher("/views/pages/auth/reset-password.jsp").forward(request, response);
        }

    }
}