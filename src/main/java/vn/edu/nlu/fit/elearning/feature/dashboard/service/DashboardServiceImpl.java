package vn.edu.nlu.fit.elearning.feature.dashboard.service;

import vn.edu.nlu.fit.elearning.feature.dashboard.dao.DashboardDao;
import vn.edu.nlu.fit.elearning.feature.dashboard.dao.DashboardDaoImpl;
import vn.edu.nlu.fit.elearning.feature.dashboard.dto.CourseRankingDto;
import vn.edu.nlu.fit.elearning.feature.dashboard.dto.RevenueDto;

import java.util.List;

public class DashboardServiceImpl implements DashboardService {
    private DashboardDao dashboardDao;

    public DashboardServiceImpl() {
        this.dashboardDao = new DashboardDaoImpl();
    }

    @Override
    public List<RevenueDto> getRevenueChartData() {
        List<RevenueDto> list = dashboardDao.findSevenDaysRevenue();

        if (list.isEmpty()) return list;

        // 1. Tìm doanh thu lớn nhất trong 7 ngày
        double maxRevenue = list.stream()
                .mapToDouble(RevenueDto::getDailyRevenue)
                .max()
                .orElse(1.0);

        // Tính % chiều cao cho từng ngày
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
