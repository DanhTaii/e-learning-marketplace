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
        // Lấy tham số bộ lọc thời gian
        String timeRange = request.getParameter("timeRange");
        if (timeRange == null || timeRange.trim().isEmpty()) {
            timeRange = "7days";
        }

        // 1. Logic Thẻ Doanh Thu (Kỳ này, Kỳ trước, Tăng trưởng)
        double currentRevenue = dashboardService.getCurrentRevenueTotalByTimeRange(timeRange);
        double previousRevenue = dashboardService.getPreviousRevenueTotalByTimeRange(timeRange);
        double revenueGrowth = dashboardService.calculateGrowth(currentRevenue, previousRevenue);

        // 2. Logic Thẻ Đơn Hàng (Kỳ này, Kỳ trước, Tăng trưởng)
        long currentOrderCount = dashboardService.getCurrentOrderCountByTimeRange(timeRange);
        long previousOrderCount = dashboardService.getPreviousOrderCountByTimeRange(timeRange);
        double orderGrowth = dashboardService.calculateGrowth(currentOrderCount, previousOrderCount);

        // 3. Logic Thẻ Người Dùng Mới (Kỳ này, Kỳ trước, Tăng trưởng)
        long currentUserCount = dashboardService.getCurrentUserCountByTimeRange(timeRange);
        long previousUserCount = dashboardService.getPreviousUserCountByTimeRange(timeRange);
        double userGrowth = dashboardService.calculateGrowth(currentUserCount, previousUserCount);

        // 4. Logic Thẻ Khóa Học (Tổng số lượng All-time không lọc thời gian)
        long totalCourses = dashboardService.getTotalCoursesCount();

        // 5. Lấy dữ liệu Biểu đồ và Bảng xếp hạng khóa học
        List<RevenueDto> revenues = dashboardService.getRevenueChartData(timeRange);
        List<CourseRankingDto> popularCourses = dashboardService.getTopSixCourses();

        // --- Đẩy toàn bộ dữ liệu đồng bộ ra file JSP ---
        request.setAttribute("revenueTotal", currentRevenue);
        request.setAttribute("revenueGrowth", revenueGrowth);

        request.setAttribute("orderCount", currentOrderCount);
        request.setAttribute("orderGrowth", orderGrowth);

        request.setAttribute("userCount", currentUserCount);
        request.setAttribute("userGrowth", userGrowth);

        request.setAttribute("courseCount", totalCourses);

        request.setAttribute("popularCourses", popularCourses);
        request.setAttribute("chartData", revenues);
        request.setAttribute("currentPage", "dashboard");

        request.getRequestDispatcher("/views/pages/admin/dashboard.jsp").forward(request, response);
    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
    private double calculateGrowth(double current, double previous) {
        if (previous == 0) {
            return current > 0 ? 100.0 : 0.0; // Nếu kỳ trước bằng 0 và kỳ này có doanh thu -> tăng 100%
        }
        return ((current - previous) / previous) * 100.0;
    }
}