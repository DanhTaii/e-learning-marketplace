package vn.edu.nlu.fit.elearning.controller.partrial.resultSearch;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import vn.edu.nlu.fit.elearning.dto.CourseCardDto;
import vn.edu.nlu.fit.elearning.model.Category;
import vn.edu.nlu.fit.elearning.model.Tag;
import vn.edu.nlu.fit.elearning.services.CategoryService;
import vn.edu.nlu.fit.elearning.services.CourseService;
import vn.edu.nlu.fit.elearning.services.TagService;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "ResultSearchByTagsController", value = "/result-search/by-tag")
public class ResultSearchByTagsController extends HttpServlet {

    private static final int PAGE_SIZE = 12;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Lấy id tag
        int idTag;
        try {
            idTag = Integer.parseInt(request.getParameter("id"));
        } catch (NumberFormatException | NullPointerException e) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid tag ID");
            return;
        }

        TagService ts = new TagService();
        Tag tag = ts.getTagById(idTag);
        request.setAttribute("tag", tag);
        request.setAttribute("mode", "tag");

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

        // Lấy list + phân trang
        List<CourseCardDto> listCourse = courseService.filterCoursesByTagWithPagination(
                idTag, sortPrice, level, priceRange, rating, duration, popular,
                page, PAGE_SIZE
        );

        // Đếm tổng
        int totalCourses = courseService.countFilteredCoursesByTag(
                idTag, sortPrice, level, priceRange, rating, duration, popular
        );

        int totalPages = (int) Math.ceil((double) totalCourses / PAGE_SIZE);

        // Set attributes
        request.setAttribute("listCourse", listCourse);
        request.setAttribute("currentPage", page);
        request.setAttribute("totalPages", totalPages);

        // Set base URL cho phân trang
        StringBuilder paginationUrl = new StringBuilder(request.getContextPath());
        paginationUrl.append(request.getServletPath());  // /result-search/by-tag
        paginationUrl.append("?id=").append(idTag);      // bắt đầu bằng ?id=...

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