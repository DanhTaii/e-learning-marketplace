package vn.edu.nlu.fit.elearning.feature.cart.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import vn.edu.nlu.fit.elearning.common.container.BeanContainer;
import vn.edu.nlu.fit.elearning.feature.category.service.CategoryService;
import vn.edu.nlu.fit.elearning.feature.course.dto.CourseCardDto;
import vn.edu.nlu.fit.elearning.feature.category.model.Category;
import vn.edu.nlu.fit.elearning.feature.tag.service.TagService;
import vn.edu.nlu.fit.elearning.feature.user.model.User;
import vn.edu.nlu.fit.elearning.feature.course.service.CourseServiceImpl;
import vn.edu.nlu.fit.elearning.feature.tag.service.TagServiceImpl;
import vn.edu.nlu.fit.elearning.feature.user.service.UserService;
import vn.edu.nlu.fit.elearning.feature.user.service.UserServiceImpl;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "ShowCartController", value = "/personal/cart")

public class ShowCartController extends HttpServlet {
    private CourseServiceImpl courseServiceImpl;

    @Override
    public void init() {
        courseServiceImpl = new CourseServiceImpl();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        int userId = 0;

        if (session != null && session.getAttribute("userId") != null) {
            userId = (Integer) session.getAttribute("userId");
        }
        UserService userService = new UserServiceImpl();
        User user = userService.getUserById(userId);
        request.setAttribute("user", user);

        List<CourseCardDto> coursesLastest = courseServiceImpl.getSixCoursesLast(userId);
        request.setAttribute("coursesLastest", coursesLastest);

        // này là làm để phần danh mục ở header hiện đc nội dung bên trong
        CategoryService ICategoryService = BeanContainer.getBean(CategoryService.class);
        List<Category> categories = ICategoryService.getAllCategories();
        request.setAttribute("categories", categories);
        TagService tagService = new TagServiceImpl();
        request.setAttribute("tags", tagService.getAllTags());

        request.getRequestDispatcher("/views/pages/cart/cart.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
