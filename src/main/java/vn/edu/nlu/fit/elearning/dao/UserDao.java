package vn.edu.nlu.fit.elearning.dao;

import vn.edu.nlu.fit.elearning.model.User;

import java.util.List;
import java.util.Optional;

public class UserDao extends BaseDao implements BaseCrudDao<User, Integer> {


    @Override
    public void create(User entity) {
        getJdbi().useHandle(h -> {
            h.createUpdate("INSERT INTO Users (username, email, password)\n" +
                    "VALUES ('ngminh', 'ngminh@gmail.com',  'Abc123456789');");
        });
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
            Optional<User> userOptional = handle.createQuery("select * from users u where u.email = :email").bind("email", email).mapToBean(User.class).findOne();
            return userOptional.orElse(null);
        });
    }

}
