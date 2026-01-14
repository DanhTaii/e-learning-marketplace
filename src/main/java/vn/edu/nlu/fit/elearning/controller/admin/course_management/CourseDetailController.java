package vn.edu.nlu.fit.elearning.controller.admin.course_management;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import vn.edu.nlu.fit.elearning.dto.CategoryDto;
import vn.edu.nlu.fit.elearning.dto.ReviewDto;
import vn.edu.nlu.fit.elearning.dto.TagDto;
import vn.edu.nlu.fit.elearning.model.Category;
import vn.edu.nlu.fit.elearning.model.Course;
import vn.edu.nlu.fit.elearning.model.Lesson;
import vn.edu.nlu.fit.elearning.model.Tag;
import vn.edu.nlu.fit.elearning.services.*;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "CourseDetailController", value = "/admin/course/detail")
public class CourseDetailController extends HttpServlet {

    private CourseService cs;
    private TagService tagService;
    private CategoryService categoryService;
    private CourseTagService courseTagService;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        this.cs = new CourseService();
        this.categoryService = new CategoryService();
        this.tagService = new TagService();
        this.courseTagService = new CourseTagService();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        Course c = cs.getCourseById(id);
        List<Tag> tagList = tagService.getAllTags();
        List<Category> categoryList = categoryService.getAllCategories();
        List<Integer> tagIdList = courseTagService.getAllTagIdByCourseId(id);

        request.setAttribute("course", c);
        request.setAttribute("categories", categoryList);
        request.setAttribute("tags", tagList);
        request.setAttribute("courseTagIdList", tagIdList);
        request.getRequestDispatcher("/html-admin/course-create.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}