package vn.edu.nlu.fit.elearning.feature.course.student.controller.all_courses;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import vn.edu.nlu.fit.elearning.common.container.BeanContainer;
import vn.edu.nlu.fit.elearning.common.helper.pagination.filter.course.AllCourseFilter;
import vn.edu.nlu.fit.elearning.feature.course.student.dto.CourseCardDto;
import vn.edu.nlu.fit.elearning.feature.course.student.service.CourseService;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "PaginationAllCoursesController", value = "/pagination-all-courses")
public class PaginationAllCoursesController extends HttpServlet {
    private CourseService courseServiceImpl;

    @Override
    public void init() {
        courseServiceImpl = BeanContainer.getBean(CourseService.class);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        HttpSession session = request.getSession();
        int userId = 0;
        if (session != null && session.getAttribute("userId") != null) {
            userId = (Integer) session.getAttribute("userId");
        }

        AllCourseFilter allCourseFilter = new AllCourseFilter();
        int page = 1;
        try {
            String pageStr = request.getParameter("page");
            if (pageStr != null) {
                page = Integer.parseInt(pageStr);
            }

            if (page < 1) {
                page = 1;
            }

        } catch (Exception ignored) {
        }

        Integer categoryId = null;

        try {

            String categoryStr = request.getParameter("category");
            if (categoryStr != null && !categoryStr.trim().isEmpty()) {
                categoryId = Integer.parseInt(categoryStr);
            }

        } catch (Exception ignored) {
        }

        allCourseFilter.setPage(page);
        allCourseFilter.setSize(16);
        allCourseFilter.setUserId(userId);
        allCourseFilter.setCategoryId(categoryId);
        allCourseFilter.setTagId(request.getParameter("tag") != null
                        ? Integer.parseInt(request.getParameter("tag"))
                        : null
        );

        allCourseFilter.setKeyword(request.getParameter("keyword"));

        allCourseFilter.setSortPrice(request.getParameter("sortPrice"));

        allCourseFilter.setPopular(Boolean.parseBoolean(request.getParameter("popular")));

        allCourseFilter.setNewest(Boolean.parseBoolean(request.getParameter("newest")));
        allCourseFilter.setLevel(request.getParameter("level"));
        allCourseFilter.setRating(request.getParameter("rating"));
        allCourseFilter.setDuration(request.getParameter("duration"));
        allCourseFilter.setPriceRange(request.getParameter("priceRange"));


        List<CourseCardDto> listCourse = courseServiceImpl.filterCourses(allCourseFilter);
        int totalCourses = courseServiceImpl.countFilterCourses(allCourseFilter);

        int totalPages = (int) Math.ceil((double) totalCourses / allCourseFilter.getSize());

        StringBuilder queryParams = new StringBuilder();

        if (categoryId != null) {
            queryParams.append("&category=").append(categoryId);
        }

        if (allCourseFilter.getSortPrice() != null) {
            queryParams.append("&sortPrice=").append(allCourseFilter.getSortPrice());
        }

        if (allCourseFilter.isPopular()) {
            queryParams.append("&popular=true");
        }

        if (allCourseFilter.isNewest()) {
            queryParams.append("&newest=true");
        }

        if (allCourseFilter.getLevel() != null) {
            queryParams.append("&level=").append(allCourseFilter.getLevel());
        }

        if (allCourseFilter.getRating() != null) {
            queryParams.append("&rating=").append(allCourseFilter.getRating());
        }

        if (allCourseFilter.getDuration() != null) {
            queryParams.append("&duration=").append(allCourseFilter.getDuration());
        }

        if (allCourseFilter.getPriceRange() != null) {
            queryParams.append("&priceRange=").append(allCourseFilter.getPriceRange());
        }

        if (allCourseFilter.getKeyword() != null) {
            queryParams.append("&keyword=").append(allCourseFilter.getKeyword());
        }

        request.setAttribute("listCourse", listCourse);
        request.setAttribute("currentPage", allCourseFilter.getPage());
        request.setAttribute("totalPages", totalPages);
        request.setAttribute("totalCourses", totalCourses);
        request.setAttribute("queryParams", queryParams.toString());
        request.setAttribute("filter", allCourseFilter);
        request.getRequestDispatcher("views/pages/partial/all-course.jsp").forward(request, response);
    }
}
