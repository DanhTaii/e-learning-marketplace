package vn.edu.nlu.fit.elearning.controller.admin.course_management;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import vn.edu.nlu.fit.elearning.enums.Level;
import vn.edu.nlu.fit.elearning.model.Course;
import vn.edu.nlu.fit.elearning.services.CourseService;
import vn.edu.nlu.fit.elearning.services.CourseTagService;

import java.io.IOException;

@WebServlet(name = "CourseCreateController", value = "/admin/course/create")
public class CourseCreateController extends HttpServlet {

    private CourseService courseService;
    private CourseTagService courseTagService;

    @Override
    public void init() throws ServletException {
        super.init();
        this.courseService = new CourseService();
        this.courseTagService = new CourseTagService();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String courseId = request.getParameter("courseId");
        String title = request.getParameter("title");
        String subtitle = request.getParameter("subtitle");
        String levelStr = request.getParameter("level");
        String goals = request.getParameter("goals");
        String description = request.getParameter("description");
        String priceStr = request.getParameter("price");
        String discountStr = request.getParameter("discount_price");
        String statusStr = request.getParameter("status");
        String categoryIdStr = request.getParameter("category_id");
        String[] tagIdsStr = request.getParameterValues("tags");

        Level level = Level.valueOf(levelStr);

        Course course = new Course();
        course.setTitle(title);
        course.setSubtitle(subtitle);
        course.setLevel(level);
        course.setGoals(goals);
        course.setDescription(description);

        int price = Integer.parseInt(priceStr);
        course.setPrice(price);

        int discountPrice = Integer.parseInt(discountStr);
        course.setDiscountPrice(discountPrice); // wrapper Integer

        boolean status = Boolean.parseBoolean(statusStr);
        course.setIsPublic(status);

        int categoryId = Integer.parseInt(categoryIdStr);
        course.setCategoryId(categoryId);

        course.setThumbnailUrl(request.getParameter("thumbnail"));

        int checkCourseCreate = 0;
        boolean isUpdate = (courseId != null && !courseId.isEmpty());

        if (isUpdate) {
            int courseIdInt = Integer.parseInt(courseId);
            course.setId(courseIdInt);
//          Cập nhật tag mới
            courseTagService.deleteCourseTag(courseIdInt);
            courseTagService.createCourseTag(courseIdInt, tagIdsStr);
//          Cập nhật khóa học
            checkCourseCreate = courseService.updateCourse(course);

            if (checkCourseCreate > 0) {
                request.getSession().setAttribute("flashSuccess", "Cập nhật khóa học thành công !");
                response.sendRedirect(request.getContextPath() + "/admin/course/detail?id=" + courseIdInt);
            }

        } else if (!isUpdate) {
            checkCourseCreate = courseService.createCourse(course);
            if (checkCourseCreate > 0) {
                request.getSession().setAttribute("flashSuccess", "Tạo khóa học thành công !");
                response.sendRedirect(request.getContextPath() + "/admin/courses");
            }
        } else {

        }

    }
}