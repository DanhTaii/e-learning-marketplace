package vn.edu.nlu.fit.elearning.feature.course.admin.controller;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import vn.edu.nlu.fit.elearning.common.container.BeanContainer;
import vn.edu.nlu.fit.elearning.common.utils.servlet.RequestUtils;
import vn.edu.nlu.fit.elearning.feature.course.admin.service.CourseAdminService;

import java.io.IOException;

@WebServlet(name = "CourseDeleteController", value = "/admin/course/action")
public class CourseAdminActionController extends HttpServlet {
    private transient CourseAdminService courseAdminServiceImpl;

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

        String mainContent = " khóa học thành công !";
        int result = 0;

        if (deleteReason != null) {
            switch (actionType) {
                case "delete": {
                    result = courseAdminServiceImpl.deleteCourse(courseId);
                    if (result > 0) {
                        request.getSession().setAttribute("flashSuccess", "Xóa khóa học thành công!");
                    }
                    response.sendRedirect(request.getContextPath() + "/admin/courses");
                    break;
                }
                case "archive": {
//                    result = courseServiceImpl.ar
                    break;
                }
                default: {
                    request.getSession().setAttribute("flashError", "Xóa khóa học thất bại. Vui lòng thử lại!");
                }
            }

        }
    }
}