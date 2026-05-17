package vn.edu.nlu.fit.elearning.feature.enrollment.controller;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import vn.edu.nlu.fit.elearning.common.container.BeanContainer;
import vn.edu.nlu.fit.elearning.common.utils.servlet.RequestUtils;
import vn.edu.nlu.fit.elearning.feature.course_note.service.CourseNoteService;

import java.io.IOException;

@WebServlet(name = "MyCourseNoteActionController", value = "/personal/my-course/note/action")
public class MyCourseNoteActionController extends HttpServlet {
    private transient CourseNoteService courseNoteService;
    private static final Logger logger = LoggerFactory.getLogger(MyCourseNoteActionController.class);

    @Override
    public void init() throws ServletException {
        super.init();
        this.courseNoteService = BeanContainer.getBean(CourseNoteService.class);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.sendError(HttpServletResponse.SC_FORBIDDEN, "PHƯƠNG THỨC GET KHÔNG ĐƯỢC HỖ TRỢ");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        try {
            int noteId = RequestUtils.getParameterAsInt(request, "noteId", 0);
            String action = RequestUtils.getParameterAsString(request, "action", "");

            int result = 0;

            switch (action) {
                case "delete":
                    result = courseNoteService.deleteCourseNotes(noteId);
                    if (result > 0) {
                        response.getWriter().write("{\"status\":\"success\"}");
                    } else {
                        response.getWriter().write("{\"status\":\"error\", \"message\":\"Không tìm thấy ghi chú để xóa\"}");
                    }
                    break;
                case "update":
                    String content = RequestUtils.getParameterAsString(request, "content", null);
                    result = courseNoteService.editNoteContentById(noteId, content);
                    if (result > 0) {
                        response.getWriter().write("{\"status\":\"success\"}");
                    } else {
                        response.getWriter().write("{\"status\":\"error\", \"message\":\"Không tìm thấy ghi chú để xóa\"}");
                    }
                    break;
                default:
                    response.getWriter().write("{\"status\":\"error\"}");
                    break;
            }
        } catch (Exception e) {
            logger.error("Lỗi thao tác với note: " + e);
            response.getWriter().write("{\"status\":\"error\"}");
        }
    }

}