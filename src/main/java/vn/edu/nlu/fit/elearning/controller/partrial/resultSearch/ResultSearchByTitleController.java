package vn.edu.nlu.fit.elearning.controller.partrial.resultSearch;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import vn.edu.nlu.fit.elearning.dto.CourseCardDto;
import vn.edu.nlu.fit.elearning.model.Category;
import vn.edu.nlu.fit.elearning.model.Course;
import vn.edu.nlu.fit.elearning.services.CategoryService;
import vn.edu.nlu.fit.elearning.services.CourseService;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "ResultSearchByTitleController", value = "/result-search/by-title")
public class ResultSearchByTitleController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // lấy từ khóa search từ thanh header
        String search = request.getParameter("title");

        CourseService courseService = new CourseService();
        List<CourseCardDto> listCourse = null;
        if (search != null && !search.trim().isEmpty()) {
            listCourse = courseService.getCoursesByTitle(search.trim());
        }
        request.setAttribute("list", listCourse);
        request.setAttribute("search", search);

        // dòng này xem là đang là search theo cái gì title hay category
        request.setAttribute("mode", "title");

        // lấy tham số lọc
        String sortPrice = request.getParameter("sortPrice");
        String level = request.getParameter("level");
        String priceRange = request.getParameter("priceRange");
        String rating = request.getParameter("rating");
        String duration = request.getParameter("duration");
        String popular = request.getParameter("popular");

        if (sortPrice == null && level == null && priceRange == null &&
                rating == null && duration == null && popular == null) {
            listCourse = courseService.getCoursesByTitle(search);
        } else {
            listCourse = courseService.filterCoursesByTitle(
                    search, sortPrice, level, priceRange, rating, duration, popular
            );
        }
        request.setAttribute("list", listCourse);


        request.getRequestDispatcher("/html-partrial/result-search.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}