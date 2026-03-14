package vn.edu.nlu.fit.elearning.feature.user.dao;

import vn.edu.nlu.fit.elearning.database.BaseCrudDao;
import vn.edu.nlu.fit.elearning.feature.user.model.User;

import java.util.List;

public interface UserDao extends BaseCrudDao<User, Integer> {
    @Override
    int create(User user);

    @Override
    User findById(Integer integer);

    @Override
    List<User> findAll();

    @Override
    int update(User entity);

    int updateRole(int userId, String role);

    @Override
    int delete(Integer integer);

    User findUserByEmail(String email);

    User findUserByUsername(String username);

    List<User> findUsersByFilter(String username, String phone, String dateFrom, String role);

    int resetPassword(String newPassword, String userMail);
}
