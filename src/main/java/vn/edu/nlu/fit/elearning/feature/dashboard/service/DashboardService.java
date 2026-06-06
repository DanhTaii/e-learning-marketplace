package vn.edu.nlu.fit.elearning.feature.dashboard.service;

import vn.edu.nlu.fit.elearning.feature.dashboard.dto.CourseRankingDto;
import vn.edu.nlu.fit.elearning.feature.dashboard.dto.RevenueDto;

import java.util.List;

public interface DashboardService {
    List<RevenueDto> getRevenueChartData(String timeRange);

    List<CourseRankingDto> getTopSixCourses();

    double getCurrentRevenueTotalByTimeRange(String timeRange);

    double getPreviousRevenueTotalByTimeRange(String timeRange);

    long getCurrentOrderCountByTimeRange(String timeRange);

    long getPreviousOrderCountByTimeRange(String timeRange);

    long getCurrentUserCountByTimeRange(String timeRange);

    long getPreviousUserCountByTimeRange(String timeRange);

    long getTotalCoursesCount();

    // =========================================================================
    // LOGIC NGHIỆP VỤ: TÍNH PHẦN TRĂM TĂNG TRƯỞNG TỔNG HỢP
    // =========================================================================
    double calculateGrowth(double current, double previous);
}
