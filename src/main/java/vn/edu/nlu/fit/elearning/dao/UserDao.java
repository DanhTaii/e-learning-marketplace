package vn.edu.nlu.fit.elearning.dao;

import org.jdbi.v3.core.statement.PreparedBatch;
import vn.edu.nlu.fit.elearning.model.User;

import java.sql.PreparedStatement;
import java.sql.Timestamp;
import java.util.List;

public class UserDao extends BaseDao implements BaseCrudDao<User, Integer> {


    @Override
    public void create(User user) {
        getJdbi().useHandle(handle -> {
            handle.createUpdate("INSERT INTO users (id, email, username, password) " +
                    "VALUES (:id, :email, :username, :password)").bindBean(user).execute();
        });
        return;
    }

    @Override
    public User findById(Integer integer) {

        return null;
    }

    @Override
    public List<User> findAll() {
        return getJdbi().withHandle(h -> {
            return h.createQuery("SELECT u.id, u.username, u.email, u.phone, u.role, u.created_at AS createdAt FROM Users u")
                    .mapToBean(User.class)
                    .list();
        });
    }

    @Override
    public int update(User entity) {

        return 0;
    }

    @Override
    public int delete(Integer integer) {

        return 0;
    }

    public User findUserByEmail(String email) {
        return getJdbi().withHandle(handle -> {
//            Với hàm cũ là :handle.createQuery("select * from users u where u.email = :email").bind("email", email).mapToBean(User.class).one();
//            Sẽ lỗi do nếu email KHÔNG tồn tại (không có kết quả nào),
//            .one() sẽ ném ra ngoại lệ IllegalStateException: Expected one element, but found none,
//            dẫn đến lỗi HTTP 500.
            return handle.createQuery("select * from users u where u.email = :email")
                    .bind("email", email)
                    .mapToBean(User.class)
                    .findFirst()
                    .orElse(null);
        });
    }

    public List<User> findUsersByFilter(String username, String phone, String dateFrom, String role) {
        StringBuilder sql = new StringBuilder("SELECT u.id, u.username, u.email, u.phone, u.role, u.created_at AS createdAt FROM Users u WHERE 1=1");

//      Phải có lúc username.trim().isEmpty() vì có thể sẽ không nhập tên nhưng để khoảng trắng thì DB nó sẽ kiếm khoảng trắng đó
        if (username != null && !username.trim().isEmpty()) {
//            String usernameSearch = "%" + username + "%";
            sql.append(" AND u.username LIKE :usernameSearch");
        }
        if (phone != null && !phone.trim().isEmpty()) {
            sql.append(" AND u.phone LIKE :phoneSearch");
        }
        if (role != null && !role.trim().isEmpty()) {
            sql.append(" AND u.role = :roleSearch");
        }
        if (dateFrom != null && !dateFrom.trim().isEmpty()) {
            sql.append(" AND u.created_at >= :dateFromSearch");
        }

        return getJdbi().withHandle(handle -> {
            var query = handle.createQuery(sql.toString());
            if (username != null && !username.trim().isEmpty()) {
                String usernameSearch = "%" + username.trim() + "%";
                query.bind("usernameSearch", usernameSearch);
            }
            if (phone != null && !phone.trim().isEmpty()) {
                String phoneSearch = "%" + phone.trim() + "%";
                query.bind("phoneSearch", phoneSearch);
            }
            if (role != null && !role.trim().isEmpty()) {
                query.bind("roleSearch", role);
            }
            if (dateFrom != null && !dateFrom.trim().isEmpty()) {
                query.bind("dateFromSearch", dateFrom);
            }
            return query.mapToBean(User.class).list();
        });
    }

    public int resetPassword(String newPassword, String userMail) {
//        Do là với withHandle thì nó sẽ trả về kiểu dữ liệu và có đủ CRUD nene có thể return về chính nó luôn
        return getJdbi().withHandle(handle -> {
            return handle.createUpdate(
                    "UPDATE Users\n" +
                    "SET password =  :newPassword, updated_at = CURRENT_TIMESTAMP\n" +
                    "WHERE email = :userMail")
                    .bind("userMail", userMail)
                    .bind("newPassword", newPassword)
                    .execute();
        });
    }
}
