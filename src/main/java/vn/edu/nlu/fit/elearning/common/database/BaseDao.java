package vn.edu.nlu.fit.elearning.common.database;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.jdbi.v3.core.Jdbi;

public abstract class BaseDao {
    //Phải dùng static do mỗi khi lớp con của BaseDao được khởi tạo nó sẽ tạo 1 Connection Pool mới
    //=> 100 request thì 100 cái pool
    private static Jdbi jdbi;

    public Jdbi getJdbi() {
        //Kiểm tra lúc vào cửa
        if (jdbi == null) makeConnect();
        return jdbi;
    }

    // Thêm synchronized để an toàn khi nhiều người truy cập cùng lúc ?
    //Hieeur synchronized như 1 chốt khóa khi 1 request được gửi đến thì sẽ chỉ có mình nó dùng
    private synchronized void makeConnect() {
        //Kiểm tra lần 2
        //Do là nếu có 10 request cùng lúc thì nó sẽ vào cửa đợi thì thằng đầu null nhưng sau khi thằng đầu xong
        // là nó đã tạo ra được 1 cái thì lúc này đã có CP thì những thằng sau không cần tạo nữa
        if (jdbi != null) return;
        //Tạo kết nối đến DB dựa theo kiểu DB đang xài (MySQL Database)
        HikariConfig config = new HikariConfig();
        config.setDriverClassName("com.mysql.cj.jdbc.Driver");
        config.setJdbcUrl("jdbc:mysql://" + DBProperties.getDbHost() + ":" + DBProperties.getDbPort() + "/" + DBProperties.getDbName());
        config.setUsername(DBProperties.getUsername());
        config.setPassword(DBProperties.getPassword());
        config.setMaximumPoolSize(5);
        config.setIdleTimeout(30000);

        HikariDataSource ds = new HikariDataSource(config);
        jdbi = Jdbi.create(ds);
    }
    protected String buildTimeCondition(String timeRange,String columnName) {
        if (timeRange == null) return "1 = 1";

        switch (timeRange) {
            case "today":
                return "DATE(created_at) = CURDATE()";
            case "7days":
                return "created_at >= CURDATE() - INTERVAL 6 DAY AND created_at < CURDATE() + INTERVAL 1 DAY";
            case "month":
                return "MONTH(created_at) = MONTH(CURDATE()) AND YEAR(created_at) = YEAR(CURDATE())";
            case "year":
                return "YEAR(created_at) = YEAR(CURDATE())";
            case "all":
            default:
                return "1 = 1";
        }
    }
}