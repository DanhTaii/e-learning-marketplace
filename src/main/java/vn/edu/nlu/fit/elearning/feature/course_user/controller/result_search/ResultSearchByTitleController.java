package vn.edu.nlu.fit.elearning.feature.course_user.controller.result_search;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import vn.edu.nlu.fit.elearning.common.container.BeanContainer;
import vn.edu.nlu.fit.elearning.feature.course_user.dto.CourseCardDto;
import vn.edu.nlu.fit.elearning.feature.course_user.service.CourseSearchService;
import vn.edu.nlu.fit.elearning.feature.course.service.CourseService;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet(name = "ResultSearchByTitleController", value = "/result-search/by-title")
public class ResultSearchByTitleController extends HttpServlet {

    private static final int PAGE_SIZE = 12;
    private CourseSearchService courseSearchService;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        this.courseSearchService = BeanContainer.getBean(CourseSearchService.class);
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

        String ajax = request.getParameter("ajax");

        if ("true".equals(ajax)) {

            String keyword = request.getParameter("keyword");
            if (keyword == null) keyword = "";
            keyword = keyword.trim();

            CourseService courseSearchService = BeanContainer.getBean(CourseService.class);
            List<CourseCardDto> list = courseSearchService.getCourseSuggestByTitle(keyword);

            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");

            PrintWriter out = response.getWriter();

            out.print("[");
            for (int i = 0; i < list.size(); i++) {

                CourseCardDto c = list.get(i);

                // Trong ResultSearchByTitleController, phần ajax=true
                out.print("{");
                out.print("\"id\":" + c.getId() + ",");
                out.print("\"title\":\"" + c.getTitle().replace("\"","\\\"") + "\",");
                out.print("\"thumbnailUrl\":\"" + c.getThumbnailUrl() + "\",");
                out.print("\"price\":" + c.getPrice() + ",");
                out.print("\"discountPrice\":" + c.getDiscountPrice() );
                out.print("}");

                if (i < list.size() - 1) {
                    out.print(",");
                }
            }
            out.print("]");
            out.flush();
            return;
        }

        // Lấy từ khóa search
        String search = request.getParameter("title");
        if (search == null) search = "";
        search = search.trim();

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
        List<CourseCardDto> listCourse = courseSearchService.filterCoursesByTitleWithPagination(
                search, sortPrice, level, priceRange, rating, duration, popular,
                page, PAGE_SIZE, userId
        );

        // Đếm tổng
        int totalCourses = courseSearchService.countFilteredCoursesByTitle(
                search, sortPrice, level, priceRange, rating, duration, popular
        );

        int totalPages = (int) Math.ceil((double) totalCourses / PAGE_SIZE);

        // Set attributes
        request.setAttribute("listCourse", listCourse);
        request.setAttribute("search", search);
        request.setAttribute("mode", "title");
        request.setAttribute("currentPage", page);
        request.setAttribute("totalPages", totalPages);

        // Set base URL cho phân trang (encode title nếu có dấu tiếng Việt)
        StringBuilder paginationUrl = new StringBuilder(request.getContextPath());
        paginationUrl.append(request.getServletPath());  // /result-search/by-title

        if (!search.isEmpty()) {
            paginationUrl.append("?title=").append(java.net.URLEncoder.encode(search, "UTF-8"));
        }

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