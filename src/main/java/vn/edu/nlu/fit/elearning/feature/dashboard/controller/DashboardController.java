package vn.edu.nlu.fit.elearning.feature.dashboard.controller;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import vn.edu.nlu.fit.elearning.common.container.BeanContainer;
import vn.edu.nlu.fit.elearning.feature.course.admin.service.CourseAdminService;
import vn.edu.nlu.fit.elearning.feature.dashboard.dto.CourseRankingDto;
import vn.edu.nlu.fit.elearning.feature.dashboard.service.DashboardService;
import vn.edu.nlu.fit.elearning.feature.order.service.OrderService;
import vn.edu.nlu.fit.elearning.feature.dashboard.dto.RevenueDto;
import vn.edu.nlu.fit.elearning.feature.user.student.service.UserService;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "DashboardController", value = "/admin/dashboard")
public class DashboardController extends HttpServlet {
    private UserService userService;
    private OrderService orderService;
    private CourseAdminService courseAdminServiceImpl;
    private DashboardService dashboardService;

    @Override
    public void init() throws ServletException {
        super.init();
        this.userService =BeanContainer.getBean(UserService.class);
        this.orderService = BeanContainer.getBean(OrderService.class);
        this.courseAdminServiceImpl = BeanContainer.getBean(CourseAdminService.class);
        this.dashboardService = BeanContainer.getBean(DashboardService.class);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<CourseRankingDto> popularCourses = dashboardService.getTopSixCourses();

        String timeRange = request.getParameter("timeRange");
        if (timeRange == null || timeRange.trim().isEmpty()) {
            timeRange = "7days";
        }
        List<RevenueDto> revenues = dashboardService.getRevenueChartData(timeRange);

        request.setAttribute("userCount", userService.countUsersByTimeRange("all"));
        request.setAttribute("orderCount", orderService.countOrdersByTimeRange(timeRange));
        request.setAttribute("courseCount", courseAdminServiceImpl.countCoursesByTimeRange("all"));
        request.setAttribute("revenueTotal", orderService.getRevenueTotalByTimeRange(timeRange));
        request.setAttribute("popularCourses", popularCourses);
        request.setAttribute("chartData", revenues);

        request.setAttribute("currentPage", "dashboard");
        request.getRequestDispatcher("/views/pages/admin/dashboard.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}