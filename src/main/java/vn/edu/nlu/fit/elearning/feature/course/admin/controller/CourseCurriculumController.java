package vn.edu.nlu.fit.elearning.feature.course.admin.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import vn.edu.nlu.fit.elearning.common.base.BaseController;
import vn.edu.nlu.fit.elearning.common.container.BeanContainer;
import vn.edu.nlu.fit.elearning.common.external.cloudinary.CloudinaryService;
import vn.edu.nlu.fit.elearning.common.helper.enums.BaseStatus;
import vn.edu.nlu.fit.elearning.common.helper.validator.lesson.LessonValidator;
import vn.edu.nlu.fit.elearning.common.utils.servlet.RequestUtils;
import vn.edu.nlu.fit.elearning.feature.course.common.model.Course;
import vn.edu.nlu.fit.elearning.feature.course.admin.service.CourseAdminService;
import vn.edu.nlu.fit.elearning.feature.lesson.model.Lesson;
import vn.edu.nlu.fit.elearning.feature.lesson.service.LessonService;

import java.io.IOException;
import java.util.List;
import java.util.Map;


@WebServlet(name = "CourseCurriculumController", value = "/admin/course/curriculum")
@MultipartConfig(
        fileSizeThreshold = 1024 * 1024, // 1MB
        maxFileSize = 150 * 1024 * 1024, // 150MB
        maxRequestSize = 100 * 1024 * 1024 // 100MB
)
public class CourseCurriculumController extends BaseController {
    private transient LessonService lessonService;
    private static final Logger logger = LoggerFactory.getLogger(CourseCurriculumController.class);

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
        //Lấy dữ liệu từ bên Client truyền qua
        Lesson lesson = new Lesson();
//        int id = RequestUtils.getParameterAsInt(request, "id", -1);
        int id = RequestUtils.getParameterAsInt(request, "lessonId", -1);
        lesson.setId(id);
        lesson.setTitle(request.getParameter("nameLesson"));
        lesson.setOrderIndex(RequestUtils.getParameterAsInt(request, "orderIndex", 0));

        int courseId = RequestUtils.getParameterAsInt(request, "courseId", 0);
        lesson.setCourseId(courseId);
//        lesson.setCourseId(RequestUtils.getParameterAsInt(request, "idCourse", 0));
//        lesson.setCourseId(courseId);

        lesson.setDurationMinutes(RequestUtils.getParameterAsInt(request, "duration_minutesLesson", 0));
        BaseStatus status = RequestUtils.getParameterAsStatus(request, "status");
        lesson.setStatus(status);

        try {
            Part videoPart = request.getPart("videoFile");
            if (videoPart != null && videoPart.getSize() > 0) {
                String uploadedVideoUrl = CloudinaryService.uploadFile(videoPart, "elearning/lessons");
                lesson.setVideoUrl(uploadedVideoUrl);
            } else {
                lesson.setVideoUrl(request.getParameter("urlVideo"));
            }

            //Truyền model vô validator để kiểm tra dữ liệu
            Map<String, String> errors = LessonValidator.validate(lesson);

            //Nếu như có lỗi thì gửi sang bên client để client hiển thị cùng với cái các giá trị đã nhập của lesson
            if (!errors.isEmpty()) {
                request.setAttribute("errors", errors);
                request.setAttribute("lesson", lesson);
//                this.doGet(request, response);
                request.getRequestDispatcher("/admin/course/editor?id=" + courseId + "&lessonId=" + id).forward(request, response);
                return;
            }

            // Nếu id lớn hơn 0 thì là cập nhật, ngược lại là tạo mới
            if (lesson.getId() > 0) {
                // LOGIC CẬP NHẬT
                int oldOrderIndex = RequestUtils.getParameterAsInt(request, "oldOrderIndex", -1);
                int oldCourseId = RequestUtils.getParameterAsInt(request, "oldCourseId", -1);
                int orderIndex = RequestUtils.getParameterAsInt(request, "orderIndex", -1);

                lesson.setId(id);
                lesson.setOrderIndex(orderIndex);

//                System.out.println("Lesson Title: " + lesson.getTitle());
//                System.out.println("Course ID: " + lesson.getCourseId());
//                System.out.println("Status: " + lesson.getStatus());
                boolean success = lessonService.updateLessonWithOrdering(lesson, oldOrderIndex, oldCourseId);
                if (success) {
                    request.getSession().setAttribute("flashSuccess", "Cập nhật bài học thành công!");
                }
                response.sendRedirect(request.getContextPath() + "/admin/course/editor?id=" + courseId + "&lessonId=" + lesson.getId());
            } else {
                // LOGIC TẠO MỚI
                // Kiểm tra trùng tên trong cùng 1 khóa học
                if (lessonService.checkLessonName(lesson.getTitle(), lesson.getCourseId())) {
//                    handleError(request, response, "Tên bài học đã tồn tại trong khóa học này!");
                    request.setAttribute("flashError", "Tên bài học đã tồn tại trong khóa học này!");
                    request.getRequestDispatcher("/admin/course/editor?id=" + courseId + "&lessonId=0").forward(request, response);
                    return;
                }

                //Result này đang trả về id của bài học vừa được tạo ra
//                System.out.println("Lesson Title: " + lesson.getTitle());
//                System.out.println("Course ID: " + lesson.getCourseId());
//                System.out.println("Status: " + lesson.getStatus());
                int result = lessonService.createLesson(lesson);
                if (result > 0) {
                    request.getSession().setAttribute("flashSuccess", "Tạo bài học thành công!");
                    response.sendRedirect(request.getContextPath() + "/admin/course/editor?id=" + courseId + "&lessonId=" + result);
                } else {
                    handleError(request, response, "Lỗi hệ thống khi tạo bài học");
                }
            }
        } catch (Exception e) {
            logger.error("Error processing lesson curriculum for courseId={}", courseId, e);
            request.getSession().setAttribute("flashError", "Lỗi hệ thống: " + e.getMessage());
            response.sendRedirect(request.getContextPath() + "/admin/course/editor?id=" + courseId);
        }
    }
}