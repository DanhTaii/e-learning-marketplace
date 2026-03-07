package vn.edu.nlu.fit.elearning.feature.course.controller.admin;

import com.google.gson.Gson;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import vn.edu.nlu.fit.elearning.feature.course.model.Course;
import vn.edu.nlu.fit.elearning.feature.course.service.CourseService;
import vn.edu.nlu.fit.elearning.helper.pagination.PageResponse;
import vn.edu.nlu.fit.elearning.utils.objects.CourseFilter;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "CourseSearchController", value = "/api/admin/courses")
public class CourseSearchController extends HttpServlet {
    private CourseService courseService;

    @Override
    public void init() throws ServletException {
        super.init();
        this.courseService = new CourseService();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //Định dạng kiểu trả về là JSON
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        // Xứ lý bộ lọc tìm kiếm
        CourseFilter courseFilter = new CourseFilter();
        courseFilter.setTitle(request.getParameter("courseTitle"));
        courseFilter.setCreatedAt(request.getParameter("dateFrom"));
        String isPublicParam = request.getParameter("isPublic");
        if ("public".equals(isPublicParam)) {
            courseFilter.setPublic(true);
        } else if ("private".equals(isPublicParam)) {
            courseFilter.setPublic(false);
        } else {
            courseFilter.setPublic(null); // Chọn "Tất cả"
        }
        courseFilter.setLevel(request.getParameter("level"));

        //Xử lý chia trang
        //Lấy tổng số trang
        int page = request.getParameter("page") != null ? Integer.parseInt(request.getParameter("page")) : 1;
        //1 trang tối đa 16 phần tử
        int pageSize = 16;
        //
        int offset = (page - 1) * pageSize;
        List<Course> listCourses = courseService.getAllCourses(courseFilter, pageSize, offset);

        // 4. Tính toán phân trang
        int totalCourses = courseService.countAllCourseAdmin(courseFilter);
        int totalPages = (int) Math.ceil((double) totalCourses / pageSize);

        PageResponse<Course> result = new PageResponse<>(listCourses, page, totalPages, totalCourses);

        //Đóng gói PageResponse theo dạng JSON
        String json = new Gson().toJson(result);
        response.getWriter().write(json);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}