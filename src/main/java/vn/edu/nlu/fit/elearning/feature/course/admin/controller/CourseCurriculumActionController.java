package vn.edu.nlu.fit.elearning.feature.course.admin.controller;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import vn.edu.nlu.fit.elearning.common.base.BaseController;
import vn.edu.nlu.fit.elearning.common.container.BeanContainer;
import vn.edu.nlu.fit.elearning.common.utils.servlet.RequestUtils;
import vn.edu.nlu.fit.elearning.feature.lesson.controller.admin.LessonActionController;
import vn.edu.nlu.fit.elearning.feature.lesson.model.Lesson;
import vn.edu.nlu.fit.elearning.feature.lesson.service.LessonService;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "CourseCurriculumActionController", value = "/admin/course/curriculum/action")
public class CourseCurriculumActionController extends BaseController {
    private transient LessonService lessonService;
    private static final Logger logger = LoggerFactory.getLogger(CourseCurriculumActionController.class);

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
        try{
            int lessonId = RequestUtils.getParameterAsInt(request, "id", 0);
            String deleteType = RequestUtils.getParameterAsString(request, "actionType", null);
            String deleteReason = RequestUtils.getParameterAsString(request, "deleteReason", null);
            List<Integer> ids = List.of(lessonId);
            Lesson lesson = lessonService.getLessonById(lessonId);
            List<Lesson> lessonsByCourse = lessonService.getLessonsByCourseId(lesson.getCourseId());
            Lesson firstLesson = lessonsByCourse.getFirst();
//            System.out.println("Lesson current" + lesson.toString());
//            System.out.println("Lesson list" + lessonsByCourse.toString());
//            System.out.println("Lesson first in course" + firstLesson.toString());

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
                            response.sendRedirect(request.getContextPath() + "/admin/course/editor?id=" + lesson.getCourseId() + "&lessonId=" + firstLesson.getId());
                            return;
                        }
                        break;

                    case "restore":
                        result = lessonService.restoreLessonsByIds(ids);
                        if (result > 0) {
                            request.getSession().setAttribute("flashSuccess", "Khôi phục bài học thành công!");
                            response.sendRedirect(request.getContextPath() + "/admin/courses");
                            return;
                        }
                        break;

                    default:
                        handleError(request, response, "Thao tác thực hiện thất bại ! ");
                        break;
                }
            }

            this.redirect(request, response, "/admin/course/editor?id=" + RequestUtils.getParameterAsInt(request, "id", 0));
        } catch (Exception e) {
            logger.error("Lỗi hệ thống khi thực hiện thao tác trên bài học: {}", e.getMessage(), e);
            handleError(request, response, "Lỗi hệ thống khi thực hiện thao tác trên bài học: " + e.getMessage());
        }
    }
}