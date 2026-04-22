package vn.edu.nlu.fit.elearning.feature.course.controller.admin;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import vn.edu.nlu.fit.elearning.common.base.BaseController;
import vn.edu.nlu.fit.elearning.common.container.BeanContainer;
import vn.edu.nlu.fit.elearning.common.helper.pagination.base.PageResponse;
import vn.edu.nlu.fit.elearning.common.helper.pagination.filter.course.CourseFilter;
import vn.edu.nlu.fit.elearning.common.utils.servlet.RequestUtils;
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
        courseFilter.setFromDate(RequestUtils.getParameterAsFromDate(request, "fromDate", null));
        courseFilter.setToDate(RequestUtils.getParameterAsToDate(request, "toDate", null));
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
        courseFilter.setPage(RequestUtils.getParameterAsInt(request, "page", 1));
        courseFilter.setSize(RequestUtils.getParameterAsInt(request, "size", 16));

        List<Course> listCourses = courseServiceImpl.getAllCourses(courseFilter);
        //Tính toán tổng số trang hiện tại
        int totalRecords = courseServiceImpl.countAllCourseAdmin(courseFilter);
        int totalPages = (int) Math.ceil((double) totalRecords / courseFilter.getSize());

        int totalAllCourses = courseServiceImpl.getTotalCourses();

        request.setAttribute("totalAllCourses", totalAllCourses);
        request.setAttribute("currentPage", "courses");
        request.setAttribute("listCourses", listCourses);
        request.setAttribute("filter", courseFilter);
        request.setAttribute("currentPageNumber", courseFilter.getPage());
        request.setAttribute("totalPages", totalPages);

        String type = request.getParameter("renderType");
        if ("partial".equals(type)) {
            this.forward(request, response, "/views/pages/admin/course/course-fragment.jsp");
        } else {
            this.forward(request, response, "/views/pages/admin/course/courses-management.jsp");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = RequestUtils.getParameterAsString(request, "action", null);
        List<Integer> ids = RequestUtils.getParameterAsListInt(request, "item-checkbox");

        String query = RequestUtils.getParameterAsString(request, "currentQuery", null);
        String newUrl = "/admin/courses?" + query;

        int result = 0;

        switch (action) {
            case "delete":
                result = courseServiceImpl.deleteCoursesByIds(ids);
                if (result > 0) {
                    handleSuccess(request, response, "Xóa " + result + " khóa học", newUrl);
                    return;
                }
                break;

            case "duplicate":
                result = courseServiceImpl.bulkDuplicateCourses(ids);
                if (result > 0) {
                    handleSuccess(request, response, "Nhân bản " + result + " khóa học", newUrl);
                    return;
                }
                break;

            case "status":
                result = courseServiceImpl.changeCoursesStatusByIds(ids);
                if (result > 0) {
                    handleSuccess(request, response, "Cập nhật " + result + " khóa học", newUrl);
                    return;
                }
                break;
        }
    }
}