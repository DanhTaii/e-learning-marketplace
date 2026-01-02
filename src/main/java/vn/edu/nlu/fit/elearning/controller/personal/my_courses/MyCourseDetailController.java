package vn.edu.nlu.fit.elearning.controller.personal.my_courses;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import vn.edu.nlu.fit.elearning.dto.LessonProgressDTO;
import vn.edu.nlu.fit.elearning.model.User;
import vn.edu.nlu.fit.elearning.services.EnrollmentService;
import vn.edu.nlu.fit.elearning.services.UserLessonProgressService;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "MyCourseDetailController", value = "/my-course/detail")
public class MyCourseDetailController extends HttpServlet {
    private EnrollmentService enrollmentService;
    private UserLessonProgressService ulp;

    @Override
    public void init() throws ServletException {
        super.init();
        this.enrollmentService = new EnrollmentService();
        this.ulp = new UserLessonProgressService();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User user = (User) request.getSession().getAttribute("userSession");
        int userId = user.getId();
        int courseId = Integer.parseInt(request.getParameter("courseId"));
//        System.out.println(userId);
//        System.out.println(courseId);
        List<LessonProgressDTO> listLessons = ulp.getAllUserLessonProgresss(userId, courseId);
//        System.out.println(listLessons);
        request.setAttribute("listLessons", listLessons);
        request.getRequestDispatcher("/html-personal/course-content.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}