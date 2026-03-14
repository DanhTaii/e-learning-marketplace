package vn.edu.nlu.fit.elearning.feature.course.controller.admin;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import vn.edu.nlu.fit.elearning.feature.course.service.CourseServiceImpl;

import java.io.IOException;

@WebServlet(name = "CourseDeleteController", value = "/admin/course/delete")
public class CourseDeleteController extends HttpServlet {
    private CourseServiceImpl courseServiceImpl;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        this.courseServiceImpl = new CourseServiceImpl();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String courseId = request.getParameter("id");
        if(courseId != null){
            int idCourse= Integer.parseInt(courseId);
            int success = courseServiceImpl.deleteCourse(idCourse);
            if (success >0) {
                request.getSession().setAttribute("flashSuccess", "Xóa khóa học thành công!");
            } else {
                request.getSession().setAttribute("flashError", "Xóa khóa học thất bại. Vui lòng thử lại!");
            }
            response.sendRedirect(request.getContextPath() + "/admin/courses");

        }
    }
}