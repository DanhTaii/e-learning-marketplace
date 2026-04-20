package vn.edu.nlu.fit.elearning.feature.lesson.controller.admin;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import vn.edu.nlu.fit.elearning.common.base.BaseController;
import vn.edu.nlu.fit.elearning.common.container.BeanContainer;
import vn.edu.nlu.fit.elearning.common.helper.pagination.filter.lesson.LessonFilter;
import vn.edu.nlu.fit.elearning.common.utils.servlet.RequestUtils;
import vn.edu.nlu.fit.elearning.feature.course.model.Course;
import vn.edu.nlu.fit.elearning.feature.course.service.CourseService;
import vn.edu.nlu.fit.elearning.feature.lesson.model.Lesson;
import vn.edu.nlu.fit.elearning.feature.lesson.service.LessonService;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "LessonManagementController", value = "/admin/lessons")
public class LessonManagementController extends BaseController {

    private transient LessonService lessonService;
    private transient CourseService courseService;

    @Override
    public void init() throws ServletException {
        super.init();
        this.lessonService = BeanContainer.getBean(LessonService.class);
        this.courseService = BeanContainer.getBean(CourseService.class);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        LessonFilter filter = new LessonFilter();

        //Lấy điều kiện tìm kiếm
        filter.setTitle(RequestUtils.getParameterAsString(request, "searchName", ""));
        filter.setCourseId(RequestUtils.getParameterAsInt(request, "courseId", 0));
        filter.setFromDate(RequestUtils.getParameterAsFromDate(request, "fromDate", null));
        filter.setToDate(RequestUtils.getParameterAsToDate(request, "toDate", null));
        filter.setStatus(RequestUtils.getParameterAsStatus(request, "status"));
        filter.setMissingVideo(RequestUtils.getParameterAsBoolean(request, "missingVideo"));

        //Lấy thông tin phân trang
        filter.setPage(RequestUtils.getParameterAsInt(request, "page", 1));
        filter.setSize(RequestUtils.getParameterAsInt(request, "size", 16));

        //Lấy danh sách lesson theo điều kiện tìm kiếm và phân trang
        List<Lesson> listLessons = lessonService.getLessonsByFilter(filter);

        //Tính toán tổng số trang hiện tại
        int totalRecords = lessonService.getCountLessonsByFilter(filter);
        int totalPages = (int) Math.ceil((double) totalRecords / filter.getSize());

        List<Course> listCourses = courseService.getAllCourses();
        request.setAttribute("listCourse", listCourses);

        request.setAttribute("listLessons", listLessons);
        request.setAttribute("totalLessons", lessonService.getTotalLessons());
        request.setAttribute("filter", filter);
        request.setAttribute("currentPageNumber", filter.getPage());
        request.setAttribute("currentPage", "lessons");
        request.setAttribute("totalPages", totalPages);

        String type = request.getParameter("renderType");
        if ("partial".equals(type)) {
            // Chỉ render phần nội dung bảng
            this.forward(request, response, "/views/pages/admin/lesson/lesson-fragment.jsp");
        } else {
            // Render toàn bộ trang như cũ
            this.forward(request, response, "/views/pages/admin/lesson/lesson-management.jsp");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = RequestUtils.getParameterAsString(request, "action", null);
        List<Integer> ids = RequestUtils.getParameterAsListInt(request, "item-checkbox");

        if (action.equals("delete")) {
            int result = lessonService.deleteLessonByids(ids);
            if (result > 0) {
                handleSuccess(request, response, "Xóa " + result + " bài học thành công", "/admin/lessons");
                return;
            }
        }

        if (action.equals("duplicate")) {
            int result = lessonService.bulkDuplicateLessons(ids);
            if(result > 0){
                handleSuccess(request, response, "Nhân bản " + result + " bài học thành công", "/admin/lessons");
                return;
            }
        }

        this.redirect(request, response, "/admin/lessons");
    }
}