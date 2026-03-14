package vn.edu.nlu.fit.elearning.feature.course_user.controller.all_courses;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import vn.edu.nlu.fit.elearning.common.container.BeanContainer;
import vn.edu.nlu.fit.elearning.common.utils.search.AllCourseFilter;
import vn.edu.nlu.fit.elearning.common.utils.search.CourseFilter;
import vn.edu.nlu.fit.elearning.feature.course_user.dto.CourseCardDto;
import vn.edu.nlu.fit.elearning.feature.course_user.service.CourseSearchService;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "PaginationAllCoursesController", value = "/pagination-all-courses")
public class PaginationAllCoursesController extends HttpServlet {
    private CourseSearchService courseSearchServiceImpl;

    @Override
    public void init() {
        courseSearchServiceImpl = BeanContainer.getBean(CourseSearchService.class);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //Filter cho AllCourse
        AllCourseFilter allCourseFilter = new AllCourseFilter();

        HttpSession session = request.getSession();
        int userId = 0;
        if (session != null && session.getAttribute("userId") != null) {
            userId = (Integer) session.getAttribute("userId");
        }

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
        allCourseFilter.setCategoryId(categoryId);
        allCourseFilter.setSortPrice(sortPrice);
        allCourseFilter.setPopular(popular);
        allCourseFilter.setNewest(newest);
        allCourseFilter.setUserId(userId);
        allCourseFilter.setSize(16);
        allCourseFilter.setPage(page);

        // Dùng filter thống nhất cho mọi trường hợp
        listCourse = courseSearchServiceImpl.filterCoursesForAllCourses(allCourseFilter);

        totalCourses = courseSearchServiceImpl.countFilteredCourses(
                categoryId, null, null, sortPrice, null, null, null, null, popularStr
        );

        int totalPages = (int) Math.ceil((double) totalCourses / allCourseFilter.getSize());

        // Set attributes
        request.setAttribute("listCourse", listCourse);
        request.setAttribute("currentPage", allCourseFilter.getPage());
        request.setAttribute("totalPages", totalPages);
        request.setAttribute("totalCourses", totalCourses);

        // Giữ trạng thái filter cho JSP
        request.setAttribute("category", categoryStr);
        request.setAttribute("sortPrice", sortPrice);
        request.setAttribute("popular", popular);

        request.getRequestDispatcher("views/pages/partial/all-course.jsp").forward(request, response);
    }
}
