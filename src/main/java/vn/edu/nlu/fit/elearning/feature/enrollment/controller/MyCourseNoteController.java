package vn.edu.nlu.fit.elearning.feature.enrollment.controller;

import com.google.gson.Gson;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import vn.edu.nlu.fit.elearning.common.base.BaseController;
import vn.edu.nlu.fit.elearning.common.container.BeanContainer;
import vn.edu.nlu.fit.elearning.common.utils.servlet.RequestUtils;
import vn.edu.nlu.fit.elearning.common.utils.servlet.SessionUtils;
import vn.edu.nlu.fit.elearning.feature.course_note.model.CourseNote;
import vn.edu.nlu.fit.elearning.feature.course_note.service.CourseNoteService;
import vn.edu.nlu.fit.elearning.feature.enrollment.service.EnrollmentService;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "MyCourseNoteController", value = "/personal/my-course/note")
public class MyCourseNoteController extends BaseController {

    private transient CourseNoteService courseNoteService;
    private static final Logger logger = LoggerFactory.getLogger(MyCourseNoteController.class);

    @Override
    public void init() throws ServletException {
        super.init();
        this.courseNoteService = BeanContainer.getBean(CourseNoteService.class);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            int userId = SessionUtils.getCurrentUserId(request);

            int lessonId = RequestUtils.getParameterAsInt(request, "lessonId", 0);

            List<CourseNote> notes = courseNoteService.getNotesByUserIdAndLessonId(userId, lessonId);
            String json = new Gson().toJson(notes);

            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            response.getWriter().write(json);
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            int userId = SessionUtils.getCurrentUserId(request);

            int lessonId = RequestUtils.getParameterAsInt(request, "lessonId", 0);

            String content = RequestUtils.getParameterAsString(request, "content", null);

            int noteTime = RequestUtils.getParameterAsInt(request, "noteTime", 0);

            CourseNote note = new CourseNote();
            note.setUserId(userId);
            note.setLessonId(lessonId);
            note.setContent(content);
            note.setNoteTime(noteTime);

            int result = courseNoteService.createCourseNotes(note);

            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            if (result > 0) {
                response.getWriter().write("{\"status\":\"success\"}");
            } else {
                response.getWriter().write("{\"status\":\"error\"}");
            }

        } catch (Exception e) {
            logger.error(e.getMessage());
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            response.getWriter().write("{\"status\":\"error\"}");
        }
    }
}