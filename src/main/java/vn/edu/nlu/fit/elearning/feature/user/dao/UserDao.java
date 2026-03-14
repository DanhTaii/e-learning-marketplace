package vn.edu.nlu.fit.elearning.feature.user.dao;

import vn.edu.nlu.fit.elearning.feature.user.model.User;

import java.util.List;

public interface UserDao {
    int create(User user);

    User findById(Integer integer);

    List<User> findAll();

    int update(User entity);

    int updateRole(int userId, String role);

    int delete(Integer integer);

    User findUserByEmail(String email);

    User findUserByUsername(String username);

    List<User> findUsersByFilter(String username, String phone, String dateFrom, String role);

    int resetPassword(String newPassword, String userMail);
}
