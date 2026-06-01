package vn.edu.nlu.fit.elearning.feature.course.admin.controller;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
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

    @Override
    public void init() throws ServletException {
        super.init();
        this.courseAdminService = BeanContainer.getBean(CourseAdminService.class);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.sendError(HttpServletResponse.SC_METHOD_NOT_ALLOWED, "GET method is not supported for this endpoint.");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Part filePart = request.getPart("excelFile");

        List<String> errorMessages = new ArrayList<>();

        try (InputStream fileContent = filePart.getInputStream()) {

            List<Course> courses = courseAdminService.importCoursesFromExcel(fileContent, errorMessages);

            if (courses != null && !courses.isEmpty()) {

                courseAdminService.createListCourses(courses);

                request.getSession().setAttribute("successMessage", "Imported " + courses.size() + " courses successfully!");
            } else {
                request.getSession().setAttribute("errorMessage", "No valid courses found in the file or file is empty.");
            }

        } catch (Exception e) {
            e.printStackTrace();
            request.getSession().setAttribute("errorMessage", "An error occurred during file processing: " + e.getMessage());
        }

        response.sendRedirect(request.getContextPath() + "/admin/course");
    }
}