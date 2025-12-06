package vn.edu.nlu.fit.elearning.dao;

import vn.edu.nlu.fit.elearning.model.User;

import java.util.Optional;

public class UserDao extends BaseDao {

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
