package vn.edu.nlu.fit.elearning.feature.course.student.controller.result_search;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import vn.edu.nlu.fit.elearning.common.container.BeanContainer;
import vn.edu.nlu.fit.elearning.feature.category.service.CategoryService;
import vn.edu.nlu.fit.elearning.feature.course.student.dto.CourseCardDto;
import vn.edu.nlu.fit.elearning.feature.category.model.Category;
import vn.edu.nlu.fit.elearning.feature.course.student.service.CourseService;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "ResultSearchByCategoriesController", value = "/result-search/by-category")
public class ResultSearchByCategoriesController extends HttpServlet {

    private static final int PAGE_SIZE = 12;  // Số khóa học mỗi trang
    private CourseService courseService;
    private CategoryService categoryService;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        this.courseService = BeanContainer.getBean(CourseService.class);
        this.categoryService = BeanContainer.getBean(CategoryService.class);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        HttpSession session = request.getSession();
        int userId = 0;

        if (session != null && session.getAttribute("userId") != null) {
            userId = (Integer) session.getAttribute("userId");
        }

        int idCategory;
        try {
            idCategory = Integer.parseInt(
                    request.getParameter("id")
            );
        } catch (Exception e) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid category id");
            return;
        }

        Category cate = categoryService.getCategoryById(idCategory);

        request.setAttribute("cate", cate);
        request.setAttribute("mode", "category");

        int page = 1;

        try {
            page = Integer.parseInt(
                    request.getParameter("page")
            );

            if (page < 1) {
                page = 1;
            }

        } catch (Exception ignored) {
        }


        String sortPrice = request.getParameter("sortPrice");
        String level = request.getParameter("level");
        String priceRange = request.getParameter("priceRange");
        String rating = request.getParameter("rating");
        String duration = request.getParameter("duration");
        String popular = request.getParameter("popular");


        List<CourseCardDto> listCourse = courseService.filterCoursesByCategoryWithPagination(idCategory, sortPrice, level, priceRange, rating, duration, popular, page, PAGE_SIZE, userId);

        int totalCourses = courseService.countFilteredCoursesByCategory(idCategory, sortPrice, level, priceRange, rating, duration, popular);

        int totalPages = Math.max(1, (int) Math.ceil((double) totalCourses / PAGE_SIZE));

        request.setAttribute("listCourse", listCourse);
        request.setAttribute("currentPage", page);
        request.setAttribute("totalPages", totalPages);
        request.setAttribute("sortPrice", sortPrice);
        request.setAttribute("level", level);
        request.setAttribute("priceRange", priceRange);
        request.setAttribute("rating", rating);
        request.setAttribute("duration", duration);
        request.setAttribute("popular", popular);

        StringBuilder paginationUrl = new StringBuilder(request.getContextPath() + request.getServletPath());

        paginationUrl.append("?id=").append(idCategory);

        if (sortPrice != null && !sortPrice.isBlank()) {
            paginationUrl.append("&sortPrice=").append(sortPrice);
        }

        if (level != null && !level.isBlank()) {
            paginationUrl.append("&level=").append(level);
        }

        if (priceRange != null && !priceRange.isBlank()) {
            paginationUrl.append("&priceRange=").append(priceRange);
        }

        if (rating != null && !rating.isBlank()) {
            paginationUrl.append("&rating=").append(rating);
        }

        if (duration != null && !duration.isBlank()) {
            paginationUrl.append("&duration=").append(duration);
        }

        if (popular != null && !popular.isBlank()) {
            paginationUrl.append("&popular=").append(popular);
        }

        request.setAttribute("paginationUrl", paginationUrl.toString());
        request.getRequestDispatcher("/views/pages/partial/result-search.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}