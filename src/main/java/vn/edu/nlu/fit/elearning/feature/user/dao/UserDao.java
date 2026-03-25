package vn.edu.nlu.fit.elearning.feature.user.dao;

import vn.edu.nlu.fit.elearning.common.database.BaseCrudDao;
import vn.edu.nlu.fit.elearning.common.helper.enums.BaseStatus;
import vn.edu.nlu.fit.elearning.feature.user.model.User;

import java.util.List;

public interface UserDao extends BaseCrudDao<User, Integer> {
//    int create(User user);
//
//    User findById(Integer integer);
//
//    List<User> findAll();
//
//    int update(User entity);
//
//    int delete(Integer integer);

    int updateRole(int userId, String role, BaseStatus status);

    User findUserByEmail(String email);

    boolean findUserByUsername(String username);

    List<User> findUsersByFilter(String username, String phone, String dateFrom, String role);

    int resetPassword(String newPassword, String userMail);

    boolean existsUserByEmail(String email);
}
