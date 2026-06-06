package vn.edu.nlu.fit.elearning.feature.dashboard.service;

import vn.edu.nlu.fit.elearning.feature.dashboard.dao.DashboardDao;
import vn.edu.nlu.fit.elearning.feature.dashboard.dto.CourseRankingDto;
import vn.edu.nlu.fit.elearning.feature.dashboard.dto.RevenueDto;

import java.util.List;

public class DashboardServiceImpl implements DashboardService {
    private DashboardDao dashboardDao;

    public DashboardServiceImpl(DashboardDao dashboardDao) {
        this.dashboardDao = dashboardDao;
    }

    @Override
    public List<RevenueDto> getRevenueChartData(String timeRange) {
        List<RevenueDto> list = dashboardDao.findRevenueByTimeRange(timeRange);

        if (list.isEmpty()) return list;

        double maxRevenue = list.stream()
                .mapToDouble(RevenueDto::getDailyRevenue)
                .max()
                .orElse(1.0);

        // Tính % chiều cao cho từng phần tử
        for (RevenueDto item : list) {
            double percent = (item.getDailyRevenue() / maxRevenue) * 100;
            item.setHeightPercent(percent);
        }

        return list;
    }

    @Override
    public List<CourseRankingDto> getTopSixCourses(){
        return this.dashboardDao.findTopSixCourses();
    }
    @Override
    public double getCurrentRevenueTotalByTimeRange(String timeRange) {
        return this.dashboardDao.getCurrentRevenueTotalByTimeRange(timeRange);
    }

    @Override
    public double getPreviousRevenueTotalByTimeRange(String timeRange) {
        return this.dashboardDao.getPreviousRevenueTotalByTimeRange(timeRange);
    }

    @Override
    public long getCurrentOrderCountByTimeRange(String timeRange) {
        return this.dashboardDao.getCurrentOrderCountByTimeRange(timeRange);
    }

    @Override
    public long getPreviousOrderCountByTimeRange(String timeRange) {
        return this.dashboardDao.getPreviousOrderCountByTimeRange(timeRange);
    }

    // =========================================================================
    // KHỐI THẺ KPI: NGƯỜI DÙNG & KHÓA HỌC
    // =========================================================================

    @Override
    public long getCurrentUserCountByTimeRange(String timeRange) {
        return this.dashboardDao.getCurrentUserCountByTimeRange(timeRange);
    }

    @Override
    public long getPreviousUserCountByTimeRange(String timeRange) {
        return this.dashboardDao.getPreviousUserCountByTimeRange(timeRange);
    }

    @Override
    public long getTotalCoursesCount() {
        return this.dashboardDao.getTotalCoursesCount();
    }

    // =========================================================================
    // LOGIC NGHIỆP VỤ: TÍNH PHẦN TRĂM TĂNG TRƯỞNG TỔNG HỢP
    // =========================================================================
    @Override
    public double calculateGrowth(double current, double previous) {
        if (previous == 0) {
            // Nếu kỳ trước bằng 0 và kỳ này phát sinh số dương -> tăng trưởng đạt tối đa 100%
            return current > 0 ? 100.0 : 0.0;
        }
        return ((current - previous) / previous) * 100.0;
    }
}