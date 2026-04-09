package vn.edu.nlu.fit.elearning.feature.lesson.controller.admin;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import vn.edu.nlu.fit.elearning.common.base.BaseController;
import vn.edu.nlu.fit.elearning.common.container.BeanContainer;
import vn.edu.nlu.fit.elearning.common.utils.servlet.RequestUtils;
import vn.edu.nlu.fit.elearning.feature.course.model.Course;
import vn.edu.nlu.fit.elearning.feature.course.service.CourseService;
import vn.edu.nlu.fit.elearning.feature.lesson.model.Lesson;
import vn.edu.nlu.fit.elearning.feature.lesson.service.LessonService;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "LessonDetailController", value = "/admin/lesson/detail")
public class LessonDetailController extends BaseController {
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
            try {
                List<Course> listCourses = courseService.getAllCourses();
                request.setAttribute("listCourse", listCourses);
                String idStr = request.getParameter("id");

                if(idStr != null && !idStr.trim().isEmpty()) {
                    int id = RequestUtils.getParameterAsInt(request, "id", -1);
                    Lesson lesson = lessonService.getLessonById(id);
                    if (lesson != null) {
                        request.setAttribute("lesson", lesson);
                    } else {
                        response.sendError(HttpServletResponse.SC_NOT_FOUND, "Không tìm thấy bài học");
                    }
                }

                this.forward(request, response, "/views/pages/admin/lesson/lesson-create.jsp");
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.sendError(HttpServletResponse.SC_METHOD_NOT_ALLOWED, "POST method is not supported");
    }
}