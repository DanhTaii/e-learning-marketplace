package vn.edu.nlu.fit.elearning.feature.auth.controller;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import vn.edu.nlu.fit.elearning.common.container.BeanContainer;
import vn.edu.nlu.fit.elearning.feature.access_token.dao.AccessTokenDaoImpl;
import vn.edu.nlu.fit.elearning.feature.access_token.dao.AccessTokenDao;
import vn.edu.nlu.fit.elearning.feature.access_token.model.AccessToken;
import vn.edu.nlu.fit.elearning.feature.access_token.service.AccessTokenService;
import vn.edu.nlu.fit.elearning.feature.category.model.Category;
import vn.edu.nlu.fit.elearning.feature.category.service.CategoryService;
import vn.edu.nlu.fit.elearning.feature.tag.service.TagService;
import vn.edu.nlu.fit.elearning.feature.user.model.User;
import vn.edu.nlu.fit.elearning.feature.access_token.service.AccessTokenServiceImpl;
import vn.edu.nlu.fit.elearning.feature.tag.service.TagServiceImpl;
import vn.edu.nlu.fit.elearning.feature.user.service.UserService;
import vn.edu.nlu.fit.elearning.feature.user.service.UserServiceImpl;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "ForgetPasswordController", value = "/forgot-password")
public class ForgetPasswordController extends HttpServlet {
    UserService userService;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        this.userService =BeanContainer.getBean(UserService.class);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        // này là làm để phần danh mục ở header hiện đc nội dung bên trong
        CategoryService ICategoryService = BeanContainer.getBean(CategoryService.class);
        List<Category> categories = ICategoryService.getAllCategories();
        request.setAttribute("categories", categories);
        TagService tagService = BeanContainer.getBean(TagService.class);
        request.setAttribute("tags", tagService.getAllTags());

        request.getRequestDispatcher("/views/pages/auth/forgot-password.jsp").forward(request, response);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String email = request.getParameter("email");
//        System.out.println("Email nhận được từ form: " + email);

        User user = userService.getUserByEmail(email);
//        System.out.println("User tìm được: " + (user == null ? "NULL" : user.getUsername()));

        if (user == null) {
            request.setAttribute("error", "Email không tồn tại!");
            request.getRequestDispatcher("/views/pages/auth/forgot-password.jsp").forward(request, response);
        }

        AccessTokenService AccessTokenService = BeanContainer.getBean(AccessTokenService.class);
        String token = AccessTokenService.generateToken();
//        System.out.println("Token được tạo: " + token);


//        String tokenReset = "http://localhost:8080/e_learning_war_exploded/check-mail" + token;
//        System.out.println("Link reset password: " + token);

        AccessToken accessToken = null;
        if(user != null){
            accessToken = new AccessToken(user.getId(), token, AccessTokenService.expireDateTime(), false);

        }

        AccessTokenDao tokenDao = new AccessTokenDaoImpl();
        boolean isCreate = false;
        if ( accessToken != null){
            isCreate = tokenDao.createToken(accessToken);
        }
        if (!isCreate) {
            request.setAttribute("error", "Lỗi server");
            request.getRequestDispatcher("/views/pages/auth/forgot-password.jsp").forward(request, response);
            return;
        }

        boolean isSend = AccessTokenService.sendEmail(email, token, user.getUsername());
        if(!isSend){
            request.setAttribute("error", "Gửi không thành công!");
            request.getRequestDispatcher("/views/pages/auth/forgot-password.jsp").forward(request, response);
            return;
        }

        // Lưu email vào session để dùng ở check-email
        HttpSession session = request.getSession();
        session.setAttribute("resetEmail", email);
        session.setMaxInactiveInterval(10 * 60); // 10 phút

        request.setAttribute("success", "Gửi thành công!");
        response.sendRedirect(request.getContextPath() + "/check-email");
    }
}