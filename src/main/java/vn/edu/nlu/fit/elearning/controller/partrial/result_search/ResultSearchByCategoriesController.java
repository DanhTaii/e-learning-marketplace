package vn.edu.nlu.fit.elearning.controller.partrial.result_search;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import vn.edu.nlu.fit.elearning.dto.CourseCardDto;
import vn.edu.nlu.fit.elearning.model.Category;
import vn.edu.nlu.fit.elearning.model.User;
import vn.edu.nlu.fit.elearning.services.CategoryService;
import vn.edu.nlu.fit.elearning.services.CourseService;
import vn.edu.nlu.fit.elearning.services.TagService;
import vn.edu.nlu.fit.elearning.services.UserService;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "ResultSearchByCategoriesController", value = "/result-search/by-category")
public class ResultSearchByCategoriesController extends HttpServlet {

    private static final int PAGE_SIZE = 12;  // Số khóa học mỗi trang

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        HttpSession session = request.getSession();
        int userId = 0;

        if (session != null && session.getAttribute("userId") != null) {
            userId = (Integer) session.getAttribute("userId");
        }
        UserService userService = new UserService();
        User user = userService.getUserById(userId);
        request.setAttribute("user", user);

        // Lấy id category
        int idCategory;
        try {
            idCategory = Integer.parseInt(request.getParameter("id"));
        } catch (NumberFormatException | NullPointerException e) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid category ID");
            return;
        }

        CategoryService cs = new CategoryService();
        Category cate = cs.getCategoryById(idCategory);
        request.setAttribute("cate", cate);
        request.setAttribute("mode", "category");

        // Lấy page
        int page = 1;
        String pageParam = request.getParameter("page");
        if (pageParam != null && !pageParam.isEmpty()) {
            try {
                page = Integer.parseInt(pageParam);
                if (page < 1) page = 1;
            } catch (NumberFormatException e) {
                page = 1;
            }
        }

        // Lấy filter params
        String sortPrice = request.getParameter("sortPrice");
        String level = request.getParameter("level");
        String priceRange = request.getParameter("priceRange");
        String rating = request.getParameter("rating");
        String duration = request.getParameter("duration");
        String popular = request.getParameter("popular");

        CourseService courseService = new CourseService();

        // Lấy danh sách khóa học đã lọc + phân trang
        List<CourseCardDto> listCourse = courseService.filterCoursesByCategoryWithPagination(
                idCategory, sortPrice, level, priceRange, rating, duration, popular,
                page, PAGE_SIZE
        );

        // Đếm tổng số khóa học sau lọc
        int totalCourses = courseService.countFilteredCoursesByCategory(
                idCategory, sortPrice, level, priceRange, rating, duration, popular
        );

        int totalPages = (int) Math.ceil((double) totalCourses / PAGE_SIZE);

        // Set attributes
        request.setAttribute("listCourse", listCourse);
        request.setAttribute("currentPage", page);
        request.setAttribute("totalPages", totalPages);

        // Set base URL cho phân trang trong JSP
        StringBuilder paginationUrl = new StringBuilder(request.getContextPath());
        paginationUrl.append(request.getServletPath());

        paginationUrl.append("?id=").append(idCategory);

        if (sortPrice != null) paginationUrl.append("&sortPrice=").append(sortPrice);
        if (level != null) paginationUrl.append("&level=").append(level);
        if (priceRange != null) paginationUrl.append("&priceRange=").append(priceRange);
        if (rating != null) paginationUrl.append("&rating=").append(rating);
        if (duration != null) paginationUrl.append("&duration=").append(duration);
        if (popular != null) paginationUrl.append("&popular=").append(popular);

        request.setAttribute("paginationUrl", paginationUrl.toString());

        // này là làm để phần danh mục ở header hiện đc nội dung bên trong
        CategoryService categoryService = new CategoryService();
        List<Category> categories = categoryService.getAllCategories();
        request.setAttribute("categories", categories);
        TagService tagService = new TagService();
        request.setAttribute("tags", tagService.getAllTags());

        request.getRequestDispatcher("/html-partrial/result-search.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}