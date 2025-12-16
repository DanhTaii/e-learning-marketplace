package vn.edu.nlu.fit.elearning.controller.partrial.resultSearch;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import vn.edu.nlu.fit.elearning.model.Category;
import vn.edu.nlu.fit.elearning.model.Course;
import vn.edu.nlu.fit.elearning.services.CategoryService;
import vn.edu.nlu.fit.elearning.services.CourseService;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "ResultSearchController", value = "/result-search/by-category")
public class ResultSearchByCategoriesController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // làm cho hiện đúng category được chọn ở trang result-search
        int idCategory = Integer.parseInt(request.getParameter("id"));
        CategoryService cs = new CategoryService();
        Category cate = cs.getCategoryById(idCategory);
        request.setAttribute("cate", cate);

        // làm để các khóa học hiển thị ở result-search đúng với category
        CourseService courseService = new CourseService();
        List<Course> listCourse = courseService.getCoursesByIdCategory(idCategory);
        request.setAttribute("list", listCourse);

        // dòng này xem là đang là search theo cái gì title hay category
        request.setAttribute("mode", "category");

        String sortPrice = request.getParameter("sortPrice");
        String level = request.getParameter("level");
        String priceRange = request.getParameter("priceRange");
        String rating = request.getParameter("rating");
        String duration = request.getParameter("duration");
        String popular = request.getParameter("popular");

        if (sortPrice == null && level == null && priceRange == null &&
                rating == null && duration == null && popular == null) {
            listCourse = courseService.getCoursesByIdCategory(idCategory);
        } else {
            listCourse = courseService.filterCoursesByCategory(
                    idCategory, sortPrice, level, priceRange, rating, duration, popular
            );
        }
        request.setAttribute("list", listCourse);


        request.getRequestDispatcher("/html-partrial/result-search.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}