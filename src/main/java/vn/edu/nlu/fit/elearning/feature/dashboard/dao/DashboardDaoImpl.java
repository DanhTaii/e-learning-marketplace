package vn.edu.nlu.fit.elearning.feature.dashboard.dao;

import vn.edu.nlu.fit.elearning.feature.dashboard.dto.CourseRankingDto;
import vn.edu.nlu.fit.elearning.common.database.BaseDao;
import vn.edu.nlu.fit.elearning.feature.dashboard.dto.RevenueDto;

import java.util.List;

public class DashboardDaoImpl extends BaseDao implements DashboardDao {


    public List<RevenueDto> findRevenueByTimeRange(String timeRange) {
        String timeCondition;
        String groupBy;
        String dateFormat;

        if (timeRange == null) timeRange = "7days";

        switch (timeRange) {
            case "today":
                timeCondition = "DATE(o.created_at) = CURDATE()";
                groupBy = "HOUR(o.created_at)";
                dateFormat = "DATE_FORMAT(MIN(o.created_at), '%H:00')";
                break;
            case "month":
                timeCondition = "MONTH(o.created_at) = MONTH(CURDATE()) AND YEAR(o.created_at) = YEAR(CURDATE())";
                groupBy = "DATE(o.created_at)";
                dateFormat = "DATE_FORMAT(MIN(o.created_at), '%d/%m')";
                break;
            case "year":
                timeCondition = "YEAR(o.created_at) = YEAR(CURDATE())";
                groupBy = "MONTH(o.created_at)";
                dateFormat = "CONCAT('T', MONTH(MIN(o.created_at)))";
                break;
            case "all":
                timeCondition = "1 = 1";
                groupBy = "YEAR(o.created_at)";
                dateFormat = "DATE_FORMAT(MIN(o.created_at), '%Y')";
                break;
            case "7days":
            default:
                timeCondition = "o.created_at >= CURDATE() - INTERVAL 6 DAY AND o.created_at < CURDATE() + INTERVAL 1 DAY";
                groupBy = "DATE(o.created_at)";
                dateFormat = "DATE_FORMAT(MIN(o.created_at), '%d/%m')";
                break;
        }

        String sql = "SELECT " + dateFormat + " AS order_date, " +
                "COUNT(DISTINCT o.id) AS total_orders, " +
                "SUM(o.final_amount) AS daily_revenue, " +
                "SUM(o.final_amount) / 1000000 AS revenue_million " +
                "FROM orders o " +
                "WHERE o.status = 'PAID' AND " + timeCondition + " " +
                "GROUP BY " + groupBy + " " +
                "ORDER BY MIN(o.created_at) ASC";

        return getJdbi().withHandle(handle -> {
            return handle.createQuery(sql)
                    .mapToBean(RevenueDto.class)
                    .list();
        });
    }

    @Override
    public List<CourseRankingDto> findTopSixCourses() {
        return getJdbi().withHandle(handle -> {
            return handle.createQuery("SELECT c.id, c.title, COUNT(DISTINCT e.user_id) as student_count\n" +
                            "FROM courses c JOIN enrollments e ON c.id = e.course_id\n" +
                            "GROUP BY c.id, c.title\n" +
                            "ORDER BY student_count DESC\n" +
                            "LIMIT 6\n")
                    .mapToBean(CourseRankingDto.class)
                    .list();
        });
    }

}
