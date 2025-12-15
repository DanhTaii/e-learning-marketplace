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

@WebServlet(name = "ResultSearchController", value = "/result-search-by-categories")
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

        request.getRequestDispatcher("/html-partrial/result-search.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}