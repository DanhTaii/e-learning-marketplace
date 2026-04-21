package vn.edu.nlu.fit.elearning.feature.course.controller.admin;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import vn.edu.nlu.fit.elearning.common.base.BaseController;
import vn.edu.nlu.fit.elearning.common.container.BeanContainer;
import vn.edu.nlu.fit.elearning.common.helper.pagination.base.PageResponse;
import vn.edu.nlu.fit.elearning.common.helper.pagination.filter.course.CourseFilter;
import vn.edu.nlu.fit.elearning.feature.course.model.Course;
import vn.edu.nlu.fit.elearning.feature.course.service.CourseService;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "AdminCourseController", value = "/admin/courses")
public class CourseManagementController extends BaseController {
    private transient CourseService courseServiceImpl;

    @Override
    public void init() throws ServletException {
        super.init();
        this.courseServiceImpl = BeanContainer.getBean(CourseService.class);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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
        List<Course> listCourses = courseServiceImpl.getAllCourses(courseFilter, pageSize, offset);

        // Tính toán phân trang
        int totalCourses = courseServiceImpl.countAllCourseAdmin(courseFilter);
        int totalPages = (int) Math.ceil((double) totalCourses / pageSize);

        PageResponse<Course> result = new PageResponse<>(listCourses, page, totalPages, totalCourses, pageSize);

        request.setAttribute("result", result);
        request.setAttribute("currentPage", "courses");
        this.forward(request, response, "/views/pages/admin/course/courses-management.jsp");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}