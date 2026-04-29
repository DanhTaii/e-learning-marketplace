package vn.edu.nlu.fit.elearning.feature.dashboard.service;

import vn.edu.nlu.fit.elearning.feature.dashboard.dto.CourseRankingDto;
import vn.edu.nlu.fit.elearning.feature.dashboard.dto.RevenueDto;

import java.util.List;

public interface DashboardService {
    List<RevenueDto> getRevenueChartData(String timeRange);

    List<CourseRankingDto> getTopSixCourses();
}
