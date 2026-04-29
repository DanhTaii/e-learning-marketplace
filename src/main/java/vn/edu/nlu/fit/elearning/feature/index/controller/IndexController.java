package vn.edu.nlu.fit.elearning.feature.index.controller;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import vn.edu.nlu.fit.elearning.common.container.BeanContainer;
import vn.edu.nlu.fit.elearning.feature.course.student.dto.CourseCardDto;
import vn.edu.nlu.fit.elearning.feature.index.service.IndexService;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "IndexController", value = {"/index"})
public class IndexController extends HttpServlet {
    private IndexService indexService;

    @Override
    public void init() throws ServletException {
        super.init();
        // do làm session
        // Khởi tạo 1 lần duy nhất
        this.indexService = BeanContainer.getBean(IndexService.class);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        int userId = 0;

        if (session != null && session.getAttribute("userId") != null) {
            userId = (Integer) session.getAttribute("userId");
        }

        // 4 Các danh sách khóa học
        CourseCardDto courseMostPopular = indexService.getCoursesMostPopular(userId);
        List<CourseCardDto> coursesLiked = indexService.getThreeCoursesWereLiked(userId);
        List<CourseCardDto> coursesLastest = indexService.getSixCoursesLast(userId);
        List<CourseCardDto> coursesFeature = indexService.getSixCoursesMostPopular(userId);

        request.setAttribute("courseMostPopular", courseMostPopular);
        request.setAttribute("coursesLiked", coursesLiked);
        request.setAttribute("coursesLastest", coursesLastest);
        request.setAttribute("coursesFeature", coursesFeature);

        request.getRequestDispatcher("/index.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}