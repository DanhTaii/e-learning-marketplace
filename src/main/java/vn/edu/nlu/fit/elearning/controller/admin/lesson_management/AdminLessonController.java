package vn.edu.nlu.fit.elearning.controller.admin.lesson_management;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import vn.edu.nlu.fit.elearning.model.Course;
import vn.edu.nlu.fit.elearning.model.Lesson;
import vn.edu.nlu.fit.elearning.services.CourseService;
import vn.edu.nlu.fit.elearning.services.LessonService;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "AdminLessonController", value = "/admin/lessons")
public class AdminLessonController extends HttpServlet {

    private LessonService lessonService;
    private CourseService courseService;

    public AdminLessonController() {
        this.lessonService = new LessonService();
        this.courseService = new CourseService();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Lesson> listLessons = lessonService.getAllLessons();
        request.setAttribute("listLessons", listLessons);
        List<Course> listCourses = courseService.getAllCourses();
        request.setAttribute("listCourse", listCourses);
        request.getRequestDispatcher("/html-admin/lesson-management.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String nameLesson = request.getParameter("nameLesson");
        String urlVideo = request.getParameter("urlVideo");
        int idCourse = Integer.parseInt(request.getParameter("idCourse"));
        int duration_minutesLesson = Integer.parseInt(request.getParameter("duration_minutesLesson"));
        Lesson newLesson = new Lesson();
        newLesson.setCourseId(idCourse);
        newLesson.setTitle(nameLesson);
        newLesson.setVideoUrl(urlVideo);
        newLesson.setDurationMinutes(duration_minutesLesson);

        int checkCreate = lessonService.createLesson(newLesson);

        if (checkCreate == 1) {
            request.getSession().setAttribute("flashSuccess", "Tạo danh mục thành công");
            response.sendRedirect(request.getContextPath() + "/admin/lessons");
        } else {
            request.setAttribute("error", "Vui lòng nhập lại");
            request.getRequestDispatcher("/admin/lesson").forward(request, response);
        }

    }
}