package vn.edu.nlu.fit.elearning.feature.course.admin.controller;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import vn.edu.nlu.fit.elearning.common.container.BeanContainer;
import vn.edu.nlu.fit.elearning.common.utils.servlet.RequestUtils;
import vn.edu.nlu.fit.elearning.feature.course.admin.service.CourseAdminService;

import java.io.IOException;

@WebServlet(name = "CourseAdminActionController", value = "/admin/course/action")
public class CourseAdminActionController extends HttpServlet {
    private transient CourseAdminService courseAdminServiceImpl;
    private static final Logger logger = LoggerFactory.getLogger(CourseAdminActionController.class);

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        this.courseAdminServiceImpl = BeanContainer.getBean(CourseAdminService.class);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.sendError(HttpServletResponse.SC_METHOD_NOT_ALLOWED, "Phương thức GET không được hỗ trợ cho endpoint này");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int courseId = RequestUtils.getParameterAsInt(request, "id", 0);
        String actionType = RequestUtils.getParameterAsString(request, "actionType", null);
        String deleteReason = RequestUtils.getParameterAsString(request, "deleteReason", null);

        if (courseId <= 0 || actionType == null) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST);
            return;
        }

        String contentResult = "";
        String subContent = "khóa học thành công !";
        try {
            int result = 0;

            switch (actionType) {
                case "delete":
                    result = courseAdminServiceImpl.deleteCourseById(courseId);
                    contentResult = "Xóa " + result + subContent;
                    break;

                case "archive":
                    result = courseAdminServiceImpl.archiveCourseById(courseId, deleteReason);
                    contentResult = "Lưu trữ " + result + subContent;
                    break;

                default:
                    request.getSession().setAttribute("flashError", "Thao tác thất bại. Vui lòng thử lại!");
                    return;
            }

            if (result > 0) {
                request.getSession().setAttribute("flashSuccess", contentResult);
            } else {
                request.getSession().setAttribute("flashError", "Thao tác thất bại. Vui lòng thử lại!");
            }

            response.sendRedirect(request.getContextPath() + "/admin/courses");

        } catch (Exception e) {
            logger.error("Error processing action '{}' for courseId={}", actionType, courseId, e);
        }
    }
}