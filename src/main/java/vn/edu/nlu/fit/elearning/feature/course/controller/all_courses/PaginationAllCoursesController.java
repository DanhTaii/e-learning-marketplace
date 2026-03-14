package vn.edu.nlu.fit.elearning.feature.course.controller.all_courses;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import vn.edu.nlu.fit.elearning.common.container.BeanContainer;
import vn.edu.nlu.fit.elearning.feature.category.service.CategoryService;
import vn.edu.nlu.fit.elearning.feature.course.dto.CourseCardDto;
import vn.edu.nlu.fit.elearning.feature.category.model.Category;
import vn.edu.nlu.fit.elearning.feature.course.service.CourseServiceImpl;
import vn.edu.nlu.fit.elearning.feature.tag.service.TagService;
import vn.edu.nlu.fit.elearning.feature.tag.service.TagServiceImpl;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "PaginationAllCoursesController", value = "/pagination-all-courses")
public class PaginationAllCoursesController extends HttpServlet {

    private static final int PAGE_SIZE = 16;
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
//        UserService userService = new UserService();
//        User user = userService.getUserById(userId);
//        request.setAttribute("user", user);

        // Lấy tất cả các tham số filter
        String pageStr = request.getParameter("page");
        String categoryStr = request.getParameter("category");
        String sortPrice = request.getParameter("sortPrice");
        String popularStr = request.getParameter("popular");
        boolean popular = (popularStr != null) ? Boolean.parseBoolean(popularStr) : false;
        String newestStr = request.getParameter("newest");
        boolean newest = (newestStr != null) ? Boolean.parseBoolean(newestStr) : false;

        int page = 1;
        try {
            if (pageStr != null) page = Integer.parseInt(pageStr);
            if (page < 1) page = 1;
        } catch (Exception e) {
            page = 1;
        }

        // Chuyển đổi category
        Integer categoryId = null;
        if (categoryStr != null && !categoryStr.trim().isEmpty()) {
            try {
                categoryId = Integer.parseInt(categoryStr);
            } catch (NumberFormatException ignored) {
            }
        }

        List<CourseCardDto> listCourse;
        int totalCourses;

        // Dùng filter thống nhất cho mọi trường hợp
        listCourse = courseServiceImpl.filterCoursesForAllCourses(
                categoryId,     // null nếu không lọc cate
                sortPrice,      // asc/desc hoặc null// duration
                popular,        // "true" nếu phổ biến
                newest,
                PAGE_SIZE,
                (page - 1) * PAGE_SIZE, userId
        );

        totalCourses = courseServiceImpl.countFilteredCourses(
                categoryId, null, null, sortPrice, null, null, null, null, popularStr
        );

        int totalPages = (int) Math.ceil((double) totalCourses / PAGE_SIZE);

        // Set attributes
        request.setAttribute("listCourse", listCourse);
        request.setAttribute("currentPage", page);
        request.setAttribute("totalPages", totalPages);
        request.setAttribute("totalCourses", totalCourses);

        // Giữ trạng thái filter cho JSP
        request.setAttribute("category", categoryStr);
        request.setAttribute("sortPrice", sortPrice);
        request.setAttribute("popular", popular);

        // này là làm để phần danh mục ở header hiện đc nội dung bên trong
        CategoryService ICategoryService = BeanContainer.getBean(CategoryService.class);
        List<Category> categories = ICategoryService.getAllCategories();
        request.setAttribute("categories", categories);
        TagService tagService = new TagServiceImpl();
        request.setAttribute("tags", tagService.getAllTags());

        request.setAttribute("categories", ICategoryService.getAllCategories());

        request.getRequestDispatcher("views/pages/partial/all-course.jsp").forward(request, response);
    }
}
