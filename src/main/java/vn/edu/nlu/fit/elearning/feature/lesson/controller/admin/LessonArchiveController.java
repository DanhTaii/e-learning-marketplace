package vn.edu.nlu.fit.elearning.feature.lesson.controller.admin;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import vn.edu.nlu.fit.elearning.common.base.BaseController;
import vn.edu.nlu.fit.elearning.common.container.BeanContainer;
import vn.edu.nlu.fit.elearning.common.helper.pagination.filter.lesson.LessonArchiveFilter;
import vn.edu.nlu.fit.elearning.common.utils.servlet.RequestUtils;
import vn.edu.nlu.fit.elearning.feature.course.common.model.Course;
import vn.edu.nlu.fit.elearning.feature.course.admin.service.CourseAdminService;
import vn.edu.nlu.fit.elearning.feature.lesson.dto.LessonArchive;
import vn.edu.nlu.fit.elearning.feature.lesson.service.LessonService;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "LessonAchievementController", value = "/admin/lessons/archive")
public class LessonArchiveController extends BaseController {

    private transient LessonService lessonService;
    private transient CourseAdminService courseAdminService;

    @Override
    public void init() throws ServletException {
        super.init();
        this.courseAdminService = BeanContainer.getBean(CourseAdminService.class);
        this.lessonService = BeanContainer.getBean(LessonService.class);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            LessonArchiveFilter filter = new LessonArchiveFilter();

            //Lấy điều kiện tìm kiếm
            filter.setTitle(RequestUtils.getParameterAsString(request, "searchName", ""));
            filter.setCourseId(RequestUtils.getParameterAsInt(request, "courseId", 0));
            filter.setDeletedFromDate(RequestUtils.getParameterAsFromDate(request, "deletedFromDate", null));
            filter.setDeletedToDate(RequestUtils.getParameterAsToDate(request, "deletedToDate", null));

            //Lấy thông tin phân trang
            filter.setPage(RequestUtils.getParameterAsInt(request, "page", 1));
            filter.setSize(RequestUtils.getParameterAsInt(request, "size", 16));

            //Lấy danh sách lesson theo điều kiện tìm kiếm và phân trang
            List<LessonArchive> listLessons = lessonService.getArchivedLessonsByFilter(filter);

            //Tính toán tổng số trang hiện tại
            int totalRecords = lessonService.getCountLessonsArchiveByFilter(filter);
            int totalPages = (int) Math.ceil((double) totalRecords / filter.getSize());

            List<Course> listCourses = courseAdminService.getAllCourses();
            request.setAttribute("listCourse", listCourses);

            request.setAttribute("archivedLessons", listLessons);
            request.setAttribute("totalArchived", lessonService.getTotalLessonsArchive());
            request.setAttribute("filter", filter);
            request.setAttribute("currentPageNumber", filter.getPage());
            request.setAttribute("currentPage", "lessons");
            request.setAttribute("currentPageArchive", "lessons");
            request.setAttribute("totalPages", totalPages);

            String type = request.getParameter("renderType");
            if ("partial".equals(type)) {
                // Chỉ render phần nội dung bảng
                this.forward(request, response, "/views/pages/admin/lesson/archive/lesson-archive-fragment.jsp");
                return;
            } else {
                // Render toàn bộ trang như cũ
                this.forward(request, response, "/views/pages/admin/lesson/archive/lesson-archive.jsp");
                return;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Integer> ids = RequestUtils.getParameterAsListInt(request, "item-checkbox");
        String action = RequestUtils.getParameterAsString(request, "action", null);
        int singleId = RequestUtils.getParameterAsInt(request, "id", 0);
        if(singleId > 0){
            ids = List.of(singleId);
        }

        String query = request.getParameter("currentQuery");
        String newPath = "/admin/lessons/archive?" + query;

        int result = 0;

        System.out.println(action);
        switch (action) {
            case "delete":
                result = lessonService.deleteLessonByIds(ids);
                System.out.println(result);
                if (result > 0) {
                    handleSuccess(request, response, "Xóa " + result + " bài học thành công", newPath);
                    System.out.println(result + "của delete");
                    return;
                }
                break;

            case "restore":
                result = lessonService.restoreLessonsByIds(ids);
                System.out.println(result);
                if (result > 0) {
                    handleSuccess(request, response, "Khôi phục " + result + " bài học thành công", newPath);
                    System.out.println(result + "của restore");
                    return;
                }
                break;

            default:
                handleError(request, response, "Thao tác thực hiện thất bại ! ");
                break;
        }

        this.redirect(request, response, newPath);
    }
}