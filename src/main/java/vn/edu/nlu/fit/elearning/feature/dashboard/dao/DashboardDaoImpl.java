package vn.edu.nlu.fit.elearning.feature.dashboard.dao;

import vn.edu.nlu.fit.elearning.feature.dashboard.dto.CourseRankingDto;
import vn.edu.nlu.fit.elearning.common.database.BaseDao;
import vn.edu.nlu.fit.elearning.feature.dashboard.dto.RevenueDto;

import java.util.List;

public class DashboardDaoImpl extends BaseDao implements DashboardDao {

    @Override
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

    // ==========================================
    // LOGIC CHO CÁC THẺ KPI (KỲ HIỆN TẠI)
    // ==========================================

    @Override
    public double getCurrentRevenueTotalByTimeRange(String timeRange) {
        String timeCondition = getCurrentTimeCondition(timeRange, "created_at");
        String sql = "SELECT COALESCE(SUM(final_amount), 0) FROM orders WHERE status = 'PAID' AND " + timeCondition;
        return getJdbi().withHandle(handle -> handle.createQuery(sql).mapTo(Double.class).one());
    }

    @Override
    public long getCurrentOrderCountByTimeRange(String timeRange) {
        String timeCondition = getCurrentTimeCondition(timeRange, "created_at");
        String sql = "SELECT COUNT(id) FROM orders WHERE status = 'PAID' AND " + timeCondition;
        return getJdbi().withHandle(handle -> handle.createQuery(sql).mapTo(Long.class).one());
    }

    @Override
    public long getCurrentUserCountByTimeRange(String timeRange) {
        String timeCondition = getCurrentTimeCondition(timeRange, "created_at");
        String sql = "SELECT COUNT(id) FROM users WHERE " + timeCondition;
        return getJdbi().withHandle(handle -> handle.createQuery(sql).mapTo(Long.class).one());
    }

    @Override
    public long getTotalCoursesCount() {
        String sql = "SELECT COUNT(id) FROM courses";
        return getJdbi().withHandle(handle -> handle.createQuery(sql).mapTo(Long.class).one());
    }

    // ==========================================
    // LOGIC CHO CÁC THẺ KPI (KỲ TRƯỚC)
    // ==========================================

    @Override
    public double getPreviousRevenueTotalByTimeRange(String timeRange) {
        String timeCondition;
        if (timeRange == null) timeRange = "7days";

        switch (timeRange) {
            case "today":
                timeCondition = "DATE(created_at) = CURDATE() - INTERVAL 1 DAY";
                break;
            case "month":
                timeCondition = "MONTH(created_at) = MONTH(CURDATE() - INTERVAL 1 MONTH) AND YEAR(created_at) = YEAR(CURDATE() - INTERVAL 1 MONTH)";
                break;
            case "year":
                timeCondition = "YEAR(created_at) = YEAR(CURDATE()) - 1";
                break;
            case "all":
                return 0.0;
            case "7days":
            default:
                timeCondition = "created_at >= CURDATE() - INTERVAL 13 DAY AND created_at < CURDATE() - INTERVAL 6 DAY";
                break;
        }

        String sql = "SELECT COALESCE(SUM(final_amount), 0) FROM orders WHERE status = 'PAID' AND " + timeCondition;
        return getJdbi().withHandle(handle -> handle.createQuery(sql).mapTo(Double.class).one());
    }

    @Override
    public long getPreviousOrderCountByTimeRange(String timeRange) {
        String timeCondition;
        if (timeRange == null) timeRange = "7days";

        switch (timeRange) {
            case "today":
                timeCondition = "DATE(created_at) = CURDATE() - INTERVAL 1 DAY";
                break;
            case "month":
                timeCondition = "MONTH(created_at) = MONTH(CURDATE() - INTERVAL 1 MONTH) AND YEAR(created_at) = YEAR(CURDATE() - INTERVAL 1 MONTH)";
                break;
            case "year":
                timeCondition = "YEAR(created_at) = YEAR(CURDATE()) - 1";
                break;
            case "all":
                return 0;
            case "7days":
            default:
                timeCondition = "created_at >= CURDATE() - INTERVAL 13 DAY AND created_at < CURDATE() - INTERVAL 6 DAY";
                break;
        }

        String sql = "SELECT COUNT(id) FROM orders WHERE status = 'PAID' AND " + timeCondition;
        return getJdbi().withHandle(handle -> handle.createQuery(sql).mapTo(Long.class).one());
    }

    @Override
    public long getPreviousUserCountByTimeRange(String timeRange) {
        String timeCondition;
        if (timeRange == null) timeRange = "7days";

        switch (timeRange) {
            case "today":
                timeCondition = "DATE(created_at) = CURDATE() - INTERVAL 1 DAY";
                break;
            case "month":
                timeCondition = "MONTH(created_at) = MONTH(CURDATE() - INTERVAL 1 MONTH) AND YEAR(created_at) = YEAR(CURDATE() - INTERVAL 1 MONTH)";
                break;
            case "year":
                timeCondition = "YEAR(created_at) = YEAR(CURDATE()) - 1";
                break;
            case "all":
                return 0;
            case "7days":
            default:
                timeCondition = "created_at >= CURDATE() - INTERVAL 13 DAY AND created_at < CURDATE() - INTERVAL 6 DAY";
                break;
        }

        String sql = "SELECT COUNT(id) FROM users WHERE " + timeCondition;
        return getJdbi().withHandle(handle -> handle.createQuery(sql).mapTo(Long.class).one());
    }

    // ==========================================
    // HÀM HELPER ĐỊNH NGHĨA ĐIỀU KIỆN KỲ HIỆN TẠI
    // ==========================================
    private String getCurrentTimeCondition(String timeRange, String columnName) {
        if (timeRange == null) timeRange = "7days";
        switch (timeRange) {
            case "today":
                return "DATE(" + columnName + ") = CURDATE()";
            case "month":
                return "MONTH(" + columnName + ") = MONTH(CURDATE()) AND YEAR(" + columnName + ") = YEAR(CURDATE())";
            case "year":
                return "YEAR(" + columnName + ") = YEAR(CURDATE())";
            case "all":
                return "1 = 1";
            case "7days":
            default:
                return columnName + " >= CURDATE() - INTERVAL 6 DAY AND " + columnName + " < CURDATE() + INTERVAL 1 DAY";
        }
    }
}
