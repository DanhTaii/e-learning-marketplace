package vn.edu.nlu.fit.elearning.feature.dashboard.controller;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import vn.edu.nlu.fit.elearning.feature.course.dto.CourseRankingDto;
import vn.edu.nlu.fit.elearning.feature.course.service.CourseService;
import vn.edu.nlu.fit.elearning.feature.dashboard.service.DashboardService;
import vn.edu.nlu.fit.elearning.feature.order.service.OrderService;
import vn.edu.nlu.fit.elearning.feature.dashboard.dto.RevenueDto;
import vn.edu.nlu.fit.elearning.feature.course.model.Course;
import vn.edu.nlu.fit.elearning.feature.order.model.Order;
import vn.edu.nlu.fit.elearning.feature.user.model.User;
import vn.edu.nlu.fit.elearning.feature.user.service.UserService;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "DashboardController", value = "/admin/dashboard")
public class DashboardController extends HttpServlet {
    private UserService userService;
    private OrderService orderService;
    private CourseService courseService;
    private DashboardService dashboardService;

    @Override
    public void init() throws ServletException {
        super.init();
        this.userService = new UserService();
        this.orderService = new OrderService();
        this.courseService = new CourseService();
        this.dashboardService = new DashboardService();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Order> orderTotal = orderService.getAllOrders();
        List<User> userTotal = userService.getAllUsers();
        List<Course> courseTotal = courseService.getAllCourses();
        double revenueSum = orderService.getRevenueTotal();
        List<CourseRankingDto> popularCourses = dashboardService.getTopSixCourses();
        List<RevenueDto> revenues = dashboardService.getRevenueChartData();

        request.setAttribute("userCount", userTotal.size());
        request.setAttribute("orderCount", orderTotal.size());
        request.setAttribute("courseCount", courseTotal.size());
        request.setAttribute("revenueTotal", revenueSum);
        request.setAttribute("popularCourses", popularCourses);
        request.setAttribute("chartData", revenues);

        request.setAttribute("currentPage", "dashboard");
        request.getRequestDispatcher("/views/pages/admin/dashboard.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}