package vn.edu.nlu.fit.elearning.feature.user.dao;

import vn.edu.nlu.fit.elearning.common.database.BaseCrudDao;
import vn.edu.nlu.fit.elearning.common.helper.enums.BaseStatus;
import vn.edu.nlu.fit.elearning.common.helper.enums.Role;
import vn.edu.nlu.fit.elearning.feature.permission.model.Permission;
import vn.edu.nlu.fit.elearning.feature.user.model.User;

import java.util.List;
import java.util.Set;

public interface UserDao extends BaseCrudDao<User, Integer> {

    int updateRole(int userId, Role role, BaseStatus status);

    User findUserByEmail(String email);

    boolean findUserByUsername(String username);

    List<User> findUsersByFilter(String username, String phone, String dateFrom, String role);

    int resetPassword(String newPassword, String userMail);

    boolean existsUserByEmail(String email);

    int countUsersByTimeRange(String timeRange);

    Set<String> findPermissionsByUserId(Integer userId);

    Set<String> findRolesByUserId(Integer userId);

}
