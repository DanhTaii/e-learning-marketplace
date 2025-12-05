package vn.edu.nlu.fit.elearning.dao;

import com.mysql.cj.jdbc.MysqlDataSource;
import org.jdbi.v3.core.Jdbi;
import vn.edu.nlu.fit.elearning.model.Course;

import java.sql.SQLException;
import java.util.List;

public abstract class BaseDao {
    private Jdbi jdbi;

    public Jdbi getJdbi() {
        if (jdbi == null) makeConnect();
        return jdbi;
    }

    private void makeConnect() {
        MysqlDataSource dataSource = new MysqlDataSource();
        dataSource.setURL("jdbc:mysql://" + DBProperties.getDbHost() + ":" + DBProperties.getDbPort() + "/"
                + DBProperties.getDbName());
        dataSource.setUser(DBProperties.getUsername());
        dataSource.setPassword(DBProperties.getPassword());
        try {
            dataSource.setUseCompression(true);
            //Tự reconnect liên tục nếu bị hủy kết nối
            dataSource.setAutoReconnect(true);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            throw new RuntimeException(throwables);
        }
        jdbi = Jdbi.create(dataSource);
    }

//    public static void main(String[] args) {
//        BaseDao baseDao = new BaseDao();
//        Jdbi jdbi = baseDao.getJdbi();
//        //useHandle: KHông trả về gì hết (void) => update, delete, insert
//        //withHandle: Trả về dữ liệu
//        List<Course> courses = jdbi.withHandle(h -> {
//            return h.createQuery("select * from courses").mapToBean(Course.class).list();
//        });
//        System.out.println(courses);
//    }
}