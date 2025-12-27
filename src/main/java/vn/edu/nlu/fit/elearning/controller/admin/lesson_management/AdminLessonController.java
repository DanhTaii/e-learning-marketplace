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
        String idCourseStr = request.getParameter("idCourse");
        String durationStr = request.getParameter("duration_minutesLesson");

        if (nameLesson.isEmpty() || urlVideo.isEmpty() || idCourseStr.isEmpty() || durationStr.isEmpty()) {

            request.getSession().setAttribute("flashError", "Vui lòng nhập đầy đủ thông tin!");
            request.setAttribute("listLessons", lessonService.getAllLessons());
            request.setAttribute("listCourse", courseService.getAllCourses());
            request.getRequestDispatcher("/html-admin/lesson-management.jsp").forward(request, response);
            return;
        }
        int idCourse = Integer.parseInt(idCourseStr);
        int duration_minutesLesson = Integer.parseInt(durationStr);
        if (idCourse <= 0) {
            request.getSession().setAttribute("flashError", "Vui lòng chọn một khóa học cụ thể!");
            request.setAttribute("listLessons", lessonService.getAllLessons());
            request.setAttribute("listCourse", courseService.getAllCourses());
            request.getRequestDispatcher("/html-admin/lesson-management.jsp").forward(request, response);
            return;
        }

        boolean duplicate = lessonService.checkLessonName(nameLesson,idCourse);
        if(duplicate){
            request.getSession().setAttribute("flashError", "Bài học bị trùng trong hệ thống");
            request.setAttribute("listLessons", lessonService.getAllLessons());
            request.setAttribute("listCourse", courseService.getAllCourses());
            request.getRequestDispatcher("/html-admin/lesson-management.jsp").forward(request, response);
            return;
        }

        Lesson newLesson = new Lesson();
        newLesson.setCourseId(idCourse);
        newLesson.setTitle(nameLesson);
        newLesson.setVideoUrl(urlVideo);
        newLesson.setDurationMinutes(duration_minutesLesson);

        int checkCreate = lessonService.createLesson(newLesson);

        if (checkCreate == 1) {
            request.getSession().setAttribute("flashSuccess", "Tạo danh mục thành công");
            response.sendRedirect(request.getContextPath() + "/admin/lessons");
        }

    }
}