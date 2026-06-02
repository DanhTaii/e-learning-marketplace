package vn.edu.nlu.fit.elearning.feature.course.admin.controller;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import vn.edu.nlu.fit.elearning.common.container.BeanContainer;
import vn.edu.nlu.fit.elearning.feature.course.admin.service.CourseAdminService;
import vn.edu.nlu.fit.elearning.feature.course.common.model.Course;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "CourseImportController", value = "/admin/course/import/excel")
@MultipartConfig(
        fileSizeThreshold = 1024 * 1024 * 2,  // 2MB
        maxFileSize = 1024 * 1024 * 10, // 10MB
        maxRequestSize = 1024 * 1024 * 50 // 50MB
)
public class CourseImportController extends HttpServlet {

    private transient CourseAdminService courseAdminService;
    private static final Logger logger = LoggerFactory.getLogger(CourseImportController.class);

    @Override
    public void init() throws ServletException {
        super.init();
        this.courseAdminService = BeanContainer.getBean(CourseAdminService.class);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.sendError(HttpServletResponse.SC_METHOD_NOT_ALLOWED, "Phương thức GET không hỗ trợ cho endpoint này !");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Part filePart = request.getPart("excelFile");

        List<String> errorMessages = new ArrayList<>();

        try (InputStream fileContent = filePart.getInputStream()) {

            List<Course> courses = courseAdminService.importCoursesFromExcel(fileContent, errorMessages);

            if (courses != null && !courses.isEmpty()) {

                courseAdminService.createListCourses(courses);

                request.getSession().setAttribute("flashSuccess", "Tải lên " + courses.size() + " khóa học thành công !");
            } else {
                request.getSession().setAttribute("flashError", "File excel không được để trống hoặc không đúng định dạng");
            }

            if (!errorMessages.isEmpty()) {
                request.getSession().setAttribute("importErrors", errorMessages);
            }

        } catch (Exception e) {
            e.printStackTrace();
            request.getSession().setAttribute("flashError", "Có lỗi xảy khi thực hiện tải file");
            logger.error("An error occurred during file processing: ", e);
        }

        response.sendRedirect(request.getContextPath() + "/admin/courses");
    }
}