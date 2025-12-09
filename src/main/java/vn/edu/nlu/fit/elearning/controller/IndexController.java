package vn.edu.nlu.fit.elearning.controller;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import vn.edu.nlu.fit.elearning.model.Category;
import vn.edu.nlu.fit.elearning.model.Course;
import vn.edu.nlu.fit.elearning.services.CategoryService;
import vn.edu.nlu.fit.elearning.services.CourseService;
import vn.edu.nlu.fit.elearning.services.UserService;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "IndexController", value = "/index")
public class IndexController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // này làm cho phần catrgory
        CategoryService cs = new CategoryService();
        ArrayList<Category> categories = (ArrayList<Category>) cs.getAllCategories();
        request.setAttribute("categories", categories);

        // làm cho phần banner
        // tổng số học viên
        UserService us = new UserService();
        int total = (int) us.totalUsers();
        request.setAttribute("totalUsers", total);
        // tổng số khóa học
        CourseService courseService = new CourseService();
        int totalCourses = (int) courseService.totalCourses();
        request.setAttribute("totalCourses", totalCourses);
        // trung bình đánh giá
        double avgRating = (double) courseService.avgRating();
        request.setAttribute("avgRating", avgRating);

        // 3 khóa học yêu thích nhất
        ArrayList<Course> coursesLiked = (ArrayList<Course>) courseService.getThreeCoursesWereLiked();
        request.setAttribute("coursesLiked", coursesLiked);

        // 6 khóa học mới nhất
        ArrayList<Course> coursesLastest = (ArrayList<Course>) courseService.getSixCoursesLast();
        request.setAttribute("coursesLastest", coursesLastest);

        // 6 khóa học phổ biến
        ArrayList<Course> coursesFeature = (ArrayList<Course>) courseService.getSixCoursesLast();
        request.setAttribute("coursesFeature", coursesFeature);

        // dòng này chỉ gọi 1 lần (nó sẽ tính lần đầu tiên mà nó đc gọi nếu có > 1 dòng)
        request.getRequestDispatcher("/index.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}