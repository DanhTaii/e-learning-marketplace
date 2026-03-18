package vn.edu.nlu.fit.elearning.feature.course_user.controller.result_search;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import vn.edu.nlu.fit.elearning.common.container.BeanContainer;
import vn.edu.nlu.fit.elearning.feature.course_user.dto.CourseCardDto;
import vn.edu.nlu.fit.elearning.feature.course_user.service.CourseSearchService;
import vn.edu.nlu.fit.elearning.feature.tag.model.Tag;
import vn.edu.nlu.fit.elearning.feature.tag.service.TagService;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "ResultSearchByTagsController", value = "/result-search/by-tag")
public class ResultSearchByTagsController extends HttpServlet {

    private static final int PAGE_SIZE = 12;
    private CourseSearchService courseSearchService;
    private TagService tagService;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        this.courseSearchService = BeanContainer.getBean(CourseSearchService.class);
        this.tagService = BeanContainer.getBean(TagService.class);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        HttpSession session = request.getSession();
        int userId = 0;

        if (session != null && session.getAttribute("userId") != null) {
            userId = (Integer) session.getAttribute("userId");
        }

        // Lấy id tag
        int idTag;
        try {
            idTag = Integer.parseInt(request.getParameter("id"));
        } catch (NumberFormatException | NullPointerException e) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid tag ID");
            return;
        }

        Tag tag = tagService.getTagById(idTag);
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

        // Lấy list + phân trang
        List<CourseCardDto> listCourse = courseSearchService.filterCoursesByTagWithPagination(
                idTag, sortPrice, level, priceRange, rating, duration, popular,
                page, PAGE_SIZE, userId
        );

        // Đếm tổng
        int totalCourses = courseSearchService.countFilteredCoursesByTag(
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

        request.getRequestDispatcher("/views/pages/partial/result-search.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }
}