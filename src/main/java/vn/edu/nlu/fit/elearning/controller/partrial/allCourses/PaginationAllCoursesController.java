package vn.edu.nlu.fit.elearning.controller.partrial.allCourses;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import vn.edu.nlu.fit.elearning.dto.CourseCardDto;
import vn.edu.nlu.fit.elearning.services.CourseService;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "PaginationAllCoursesController", value = "/pagination-all-courses")
public class PaginationAllCoursesController extends HttpServlet {
    private static final int PAGE_SIZE = 6; // số khóa học mỗi trang

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int page = 1;
        String pageParam = request.getParameter("page");
        if (pageParam != null) {
            try {
                page = Integer.parseInt(pageParam);
            } catch (NumberFormatException e) {
                page = 1;
            }
        }

        CourseService cs = new CourseService();
        int totalCourses = cs.totalCourses();
        int totalPages = (int) Math.ceil((double) totalCourses / PAGE_SIZE);

        List<CourseCardDto> listCourse = cs.getCourseCardsByPage(page, PAGE_SIZE);

        request.setAttribute("listCourse", listCourse);
        request.setAttribute("currentPage", page);
        request.setAttribute("totalPages", totalPages);

        request.getRequestDispatcher("/html-partrial/all-course.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

}
