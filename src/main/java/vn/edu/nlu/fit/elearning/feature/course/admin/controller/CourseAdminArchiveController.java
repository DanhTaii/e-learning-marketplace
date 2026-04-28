package vn.edu.nlu.fit.elearning.feature.course.admin.controller;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import vn.edu.nlu.fit.elearning.common.base.BaseController;
import vn.edu.nlu.fit.elearning.common.container.BeanContainer;
import vn.edu.nlu.fit.elearning.common.helper.pagination.filter.course.CourseArchivedFilter;
import vn.edu.nlu.fit.elearning.common.utils.servlet.RequestUtils;
import vn.edu.nlu.fit.elearning.feature.category.dto.CategoryOptionDto;
import vn.edu.nlu.fit.elearning.feature.category.service.CategoryService;
import vn.edu.nlu.fit.elearning.feature.course.admin.dto.CourseArchive;
import vn.edu.nlu.fit.elearning.feature.course.admin.service.CourseAdminService;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "CourseArchiveController", value = "/admin/courses/archive")
public class CourseAdminArchiveController extends BaseController {

    private transient CourseAdminService courseAdminService;
    private transient CategoryService categoryService;
    private static final Logger logger = LoggerFactory.getLogger(CourseAdminArchiveController.class);

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        this.courseAdminService = BeanContainer.getBean(CourseAdminService.class);
        this.categoryService = BeanContainer.getBean(CategoryService.class);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            CourseArchivedFilter filter = new CourseArchivedFilter();

            //Lấy điều kiện tìm kiếm
            filter.setTitle(RequestUtils.getParameterAsString(request, "searchName", ""));
            filter.setCategoryId(RequestUtils.getParameterAsInt(request, "categoryId", 0));
            filter.setDeletedFromDate(RequestUtils.getParameterAsFromDate(request, "deletedFromDate", null));
            filter.setDeletedToDate(RequestUtils.getParameterAsToDate(request, "deletedToDate", null));

            //Lấy thông tin phân trang
            filter.setPage(RequestUtils.getParameterAsInt(request, "page", 1));
            filter.setSize(RequestUtils.getParameterAsInt(request, "size", 16));

            //Lấy danh sách lesson theo điều kiện tìm kiếm và phân trang
            List<CourseArchive> listCourses = courseAdminService.getArchivedCourses(filter);

            //Tính toán tổng số trang hiện tại
            int totalRecords = courseAdminService.countArchivedCourses(filter);
            int totalPages = (int) Math.ceil((double) totalRecords / filter.getSize());

            List<CategoryOptionDto> listCategory = categoryService.getCategoriesIdAndName();
            request.setAttribute("listCategories", listCategory);

            request.setAttribute("archivedCourses", listCourses);
            request.setAttribute("totalArchived", courseAdminService.getTotalArchivedCourses());
            request.setAttribute("filter", filter);
            request.setAttribute("currentPageNumber", filter.getPage());
            request.setAttribute("currentPage", "courses");
            request.setAttribute("currentPageArchive", "courses");
            request.setAttribute("totalPages", totalPages);

            String type = request.getParameter("renderType");
            if ("partial".equals(type)) {
                // Chỉ render phần nội dung bảng
                this.forward(request, response, "/views/pages/admin/course/archive/course-archive-fragment.jsp");
                return;
            } else {
                // Render toàn bộ trang như cũ
                this.forward(request, response, "/views/pages/admin/course/archive/course-archive.jsp");
                return;
            }

        } catch (Exception e) {
            logger.error("Error processing: ", e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Integer> ids = RequestUtils.getParameterAsListInt(request, "item-checkbox");
        String action = RequestUtils.getParameterAsString(request, "action", null);
        int singleId = RequestUtils.getParameterAsInt(request, "id", 0);
        if (singleId > 0) {
            ids = List.of(singleId);
        }

        String query = request.getParameter("currentQuery");
        String newPath = "/admin/courses/archive?" + query;
        String contentResult = "";
        String subContent = " khóa học thành công !";

        try {
            int result = 0;
            switch (action) {
                case "delete":
                    result = courseAdminService.deleteCoursesByIds(ids);
                    contentResult = "Xóa " + result + subContent;
                    break;

                case "restore":
                    result = courseAdminService.restoreCoursesByIds(ids);
                    contentResult = "Khôi phục " + result + subContent;
                    break;

                default:
                    handleError(request, response, "Thao tác thực hiện thất bại ! ");
                    break;
            }
            if (result > 0) {
                request.getSession().setAttribute("flashSuccess", contentResult);
            } else {
                request.getSession().setAttribute("flashError", "Thao tác thất bại. Vui lòng thử lại!");
            }

            this.redirect(request, response, newPath);
        } catch (Exception e) {
            logger.error("Error processing: ", e);
            throw new RuntimeException(e);
        }
    }
}