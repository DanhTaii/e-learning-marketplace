package vn.edu.nlu.fit.elearning.feature.dashboard.dao;

import vn.edu.nlu.fit.elearning.feature.dashboard.dto.CourseRankingDto;
import vn.edu.nlu.fit.elearning.feature.dashboard.dto.RevenueDto;

import java.util.List;

public interface DashboardDao {
    List<CourseRankingDto> findTopSixCourses();
    List<RevenueDto> findRevenueByTimeRange(String timeRange);
}
