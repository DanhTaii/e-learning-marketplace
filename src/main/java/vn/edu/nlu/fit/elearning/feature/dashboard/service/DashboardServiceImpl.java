package vn.edu.nlu.fit.elearning.feature.dashboard.service;

import vn.edu.nlu.fit.elearning.feature.dashboard.dao.DashboardDao;
import vn.edu.nlu.fit.elearning.feature.dashboard.dao.DashboardDaoImpl;
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
}
