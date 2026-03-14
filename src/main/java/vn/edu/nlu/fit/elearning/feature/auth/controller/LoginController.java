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
import vn.edu.nlu.fit.elearning.feature.user.model.User;
import vn.edu.nlu.fit.elearning.feature.tag.service.TagServiceImpl;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "LoginController", value = "/sign-in")
public class LoginController extends HttpServlet {

    private AuthService AuthService;

    @Override
    public void init() throws ServletException {
        super.init();
        this.AuthService = new AuthServiceImpl();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // này là làm để phần danh mục ở header hiện đc nội dung bên trong
        CategoryService ICategoryService = BeanContainer.getBean(CategoryService.class);
        List<Category> categories = ICategoryService.getAllCategories();
        request.setAttribute("categories", categories);
        TagService tagService = new TagServiceImpl();
        request.setAttribute("tags", tagService.getAllTags());

        request.getRequestDispatcher("/views/pages/auth/sign-in.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String email = request.getParameter("email");
        String pass = request.getParameter("password");
        try {
            User canLogin = AuthService.login(email, pass);
            if (canLogin != null) {
                HttpSession session = request.getSession();
                canLogin.setPassword(null);
                session.setAttribute("userId", canLogin.getId());
                session.setAttribute("userSession", canLogin);

                if (canLogin.getRole().equalsIgnoreCase("admin")) {
                    response.sendRedirect("admin/dashboard");
                    return;
                } else {
                    canLogin.setPassword("");
                    response.sendRedirect("index");
                    return;
                }
            } else {
                request.setAttribute("error", "Bạn nhập sai email hoặc mật khẩu!");
                doGet(request, response);
                return;
            }
        } catch (IllegalArgumentException e) {
            // Bắt các lỗi nghiệp vụ (Trống thông tin, sai tài khoản...)
            request.setAttribute("error", e.getMessage());
            doGet(request, response);
            return;
        } catch (Exception e) {
            // Lỗi hệ thống (DB sập, NullPointer...)
            e.printStackTrace();
            request.setAttribute("error", "Có lỗi hệ thống xảy ra, vui lòng thử lại sau!");
            doGet(request, response);
            return;
        }

    }
}