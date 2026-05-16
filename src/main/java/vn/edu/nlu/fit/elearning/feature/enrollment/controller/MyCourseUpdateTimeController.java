package vn.edu.nlu.fit.elearning.feature.enrollment.controller;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import vn.edu.nlu.fit.elearning.common.container.BeanContainer;
import vn.edu.nlu.fit.elearning.common.utils.servlet.RequestUtils;
import vn.edu.nlu.fit.elearning.common.utils.servlet.SessionUtils;
import vn.edu.nlu.fit.elearning.feature.lesson_progress.service.UserLessonProgressService;

import java.io.IOException;

@WebServlet(name = "MyCourseUpdateTimeController", value = "/personal/my-course/update-time")
public class MyCourseUpdateTimeController extends HttpServlet {
    private transient UserLessonProgressService ulp;
    private static final Logger logger = LoggerFactory.getLogger(MyCourseUpdateTimeController.class);

    @Override
    public void init() throws ServletException {
        super.init();
        this.ulp = BeanContainer.getBean(UserLessonProgressService.class);
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
            int userId = SessionUtils.getCurrentUserId(request);
            int lessonId = RequestUtils.getParameterAsInt(request, "lessonId", 0);
            int lastTimeWatched = RequestUtils.getParameterAsInt(request, "lastWatchedTime", 0);

            int result = ulp.updateUserLessonProgressLastWatchedTime(userId, lessonId, lastTimeWatched);
            if (result > 0) {
                response.getWriter().write("{\"status\":\"success\"}");
            } else {
                response.getWriter().write("{\"status\":\"error\"}");
            }
        } catch (Exception e) {
            response.getWriter().write("{\"status\":\"error\"}");
            logger.error("Lỗi cập nhật thời gian coi cuối: " + e);
        }
    }
}