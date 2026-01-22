package vn.edu.nlu.fit.elearning.dao;

import vn.edu.nlu.fit.elearning.dto.CourseRankingDto;
import vn.edu.nlu.fit.elearning.dto.RevenueDto;

import java.util.List;

public class DashboardDao extends BaseDao {

    public List<RevenueDto> findSevenDaysRevenue() {
        return getJdbi().withHandle(handle -> {
            return handle.createQuery("SELECT DATE_FORMAT(MIN(created_at), '%d/%m') AS order_date, COUNT(DISTINCT o.id) AS total_orders, SUM(o.final_amount) AS daily_revenue, SUM(o.final_amount) / 1000000 AS revenue_million\n" +
                            "FROM orders o\n" +
                            "WHERE o.status = 'PAID' AND o.created_at >= CURDATE() - INTERVAL 6 DAY AND o.created_at < CURDATE() + INTERVAL 1 DAY   \n" +
                            "GROUP BY DATE(created_at)\n" +
                            "ORDER BY DATE(created_at) ASC;")
                    .mapToBean(RevenueDto.class)
                    .list();
        });
    }

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
