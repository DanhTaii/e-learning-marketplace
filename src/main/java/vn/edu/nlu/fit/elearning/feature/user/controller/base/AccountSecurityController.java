package vn.edu.nlu.fit.elearning.feature.user.controller.base;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import vn.edu.nlu.fit.elearning.common.container.BeanContainer;
import vn.edu.nlu.fit.elearning.feature.category.model.Category;
import vn.edu.nlu.fit.elearning.feature.category.service.CategoryService;
import vn.edu.nlu.fit.elearning.feature.tag.service.TagService;
import vn.edu.nlu.fit.elearning.feature.tag.service.TagServiceImpl;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "AccountSecurityController", value = "/personal/account-security")
public class AccountSecurityController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

//        HttpSession session = request.getSession();
//        int userId = 0;
//
//        if (session != null && session.getAttribute("userId") != null) {
//            userId = (Integer) session.getAttribute("userId");
//        }

        // này là làm để phần danh mục ở header hiện đc nội dung bên trong
        CategoryService ICategoryService = BeanContainer.getBean(CategoryService.class);
        List<Category> categories = ICategoryService.getAllCategories();
        request.setAttribute("categories", categories);
        TagService tagService = new TagServiceImpl();
        request.setAttribute("tags", tagService.getAllTags());
//        UserService userService = new UserService();
//        User user = userService.getUserById(userId);
//        request.setAttribute("user", user);

        request.setAttribute("currentPage", "security");
        request.getRequestDispatcher("/views/pages/personal/profile/account-security.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}