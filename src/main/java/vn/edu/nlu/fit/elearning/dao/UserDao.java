package vn.edu.nlu.fit.elearning.dao;

import org.jdbi.v3.core.statement.PreparedBatch;
import vn.edu.nlu.fit.elearning.model.User;

import java.sql.PreparedStatement;
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

}
