package vn.edu.nlu.fit.elearning.feature.lesson.controller.admin;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import vn.edu.nlu.fit.elearning.common.base.BaseController;
import vn.edu.nlu.fit.elearning.common.container.BeanContainer;
import vn.edu.nlu.fit.elearning.common.utils.servlet.RequestUtils;
import vn.edu.nlu.fit.elearning.feature.lesson.service.LessonService;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "LessonDeleteController", value = "/admin/lesson/action")
public class LessonActionController extends BaseController {
    private transient LessonService lessonService;

    @Override
    public void init() throws ServletException {
        super.init();
        this.lessonService = BeanContainer.getBean(LessonService.class);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.sendError(HttpServletResponse.SC_METHOD_NOT_ALLOWED, "Phương thức GET không được hỗ trợ cho endpoint này");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int lessonId = RequestUtils.getParameterAsInt(request, "id", 0);
        String deleteType = RequestUtils.getParameterAsString(request, "deleteType", null);
        String deleteReason = RequestUtils.getParameterAsString(request, "deleteReason", null);
        List<Integer> ids = List.of(lessonId);

        int result = 0;

        if (deleteType != null) {
            switch (deleteType) {
                case "delete":
                    result = lessonService.deleteLessonByIds(ids);
                    if (result > 0) {
                        request.getSession().setAttribute("flashSuccess", "Xóa vĩnh viễn bài học thành công!");
                        response.sendRedirect(request.getContextPath() + "/admin/lessons/archive");
                        return;
                    }
                    break;

                case "archive":
                    result = lessonService.archiveLessonsByIds(ids, deleteReason);

                    if (result > 0) {
                        request.getSession().setAttribute("flashSuccess", "Xóa bài học thành công!");
                        response.sendRedirect(request.getContextPath() + "/admin/lessons");
                        return;
                    }
                    break;

                case "restore":
                    result = lessonService.restoreLessonsByIds(ids);
                    if (result > 0) {
                        request.getSession().setAttribute("flashSuccess", "Khôi phục bài học thành công!");
                        response.sendRedirect(request.getContextPath() + "/admin/lessons");
                        return;
                    }
                    break;

                default:
                    handleError(request, response, "Thao tác thực hiện thất bại ! ");
                    break;
            }
        }

        this.redirect(request, response, "/admin/lessons");
    }
}