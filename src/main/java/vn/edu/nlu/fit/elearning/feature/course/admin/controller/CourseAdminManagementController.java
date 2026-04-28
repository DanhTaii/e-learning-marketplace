package vn.edu.nlu.fit.elearning.feature.course.admin.controller;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import vn.edu.nlu.fit.elearning.common.base.BaseController;
import vn.edu.nlu.fit.elearning.common.container.BeanContainer;
import vn.edu.nlu.fit.elearning.common.helper.pagination.filter.course.CourseFilter;
import vn.edu.nlu.fit.elearning.common.utils.servlet.RequestUtils;
import vn.edu.nlu.fit.elearning.feature.category.dto.CategoryOptionDto;
import vn.edu.nlu.fit.elearning.feature.category.service.CategoryService;
import vn.edu.nlu.fit.elearning.feature.course.admin.dto.CourseAdminDto;
import vn.edu.nlu.fit.elearning.feature.course.common.model.Course;
import vn.edu.nlu.fit.elearning.feature.course.admin.service.CourseAdminService;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "CourseAdminManagementController", value = "/admin/courses")
public class CourseAdminManagementController extends BaseController {
    private transient CourseAdminService courseAdminServiceImpl;
    private transient CategoryService categoryService;
    private static final Logger logger = LoggerFactory.getLogger(CourseAdminManagementController.class);

    @Override
    public void init() throws ServletException {
        super.init();
        this.courseAdminServiceImpl = BeanContainer.getBean(CourseAdminService.class);
        this.categoryService = BeanContainer.getBean(CategoryService.class);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Xứ lý bộ lọc tìm kiếm
        CourseFilter courseFilter = new CourseFilter();
        courseFilter.setTitle(request.getParameter("courseTitle"));
        courseFilter.setFromDate(RequestUtils.getParameterAsFromDate(request, "fromDate", null));
        courseFilter.setToDate(RequestUtils.getParameterAsToDate(request, "toDate", null));
        courseFilter.setCategoryId(RequestUtils.getParameterAsInt(request, "categoryId", 0));
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

        try {
            List<CourseAdminDto> listCourses = courseAdminServiceImpl.getCourses(courseFilter);
            //Tính toán tổng số trang hiện tại
            int totalRecords = courseAdminServiceImpl.countCourses(courseFilter);
            int totalPages = (int) Math.ceil((double) totalRecords / courseFilter.getSize());

            int totalAllCourses = courseAdminServiceImpl.getTotalCourses();

            List<CategoryOptionDto> listCategories = categoryService.getCategoriesIdAndName();
            request.setAttribute("listCategories", listCategories);
            System.out.println(listCourses);

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
        } catch (Exception e) {
            logger.error("Error: " + e);
        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = RequestUtils.getParameterAsString(request, "action", null);
        List<Integer> ids = RequestUtils.getParameterAsListInt(request, "item-checkbox");
        String deleteReason = RequestUtils.getParameterAsString(request, "deleteReason", null);

        String query = RequestUtils.getParameterAsString(request, "currentQuery", null);
        String newUrl = "/admin/courses?" + query;
        String mainContent = " khóa học";

        int result = 0;
        try {
            switch (action) {
                case "archive":
                    result = courseAdminServiceImpl.archiveCoursesByIds(ids, deleteReason);
                    if (result > 0) {
                        handleSuccess(request, response, "Lưu trữ " + result + mainContent, newUrl);
                        return;
                    }
                    break;

                case "duplicate":
                    result = courseAdminServiceImpl.duplicateCoursesByIds(ids);
                    if (result > 0) {
                        handleSuccess(request, response, "Nhân bản " + result + mainContent, newUrl);
                        return;
                    }
                    break;

                case "update_status":
                    result = courseAdminServiceImpl.updateCoursesStatusByIds(ids);
                    if (result > 0) {
                        handleSuccess(request, response, "Cập nhật " + result + mainContent, newUrl);
                        return;
                    }
                    break;

                default: {
                    request.getSession().setAttribute("flashError", "Thao tác với khóa học thất bại. Vui lòng thử lại!");
                }
            }
        } catch (Exception e) {
            logger.error("Error processing action '{}' for courseId={}", action, ids, e);
        }
    }
}