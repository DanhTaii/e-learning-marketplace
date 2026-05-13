package vn.edu.nlu.fit.elearning.feature.user.admin.dao;

import vn.edu.nlu.fit.elearning.common.database.BaseCrudDao;
import vn.edu.nlu.fit.elearning.common.database.BaseDao;
import vn.edu.nlu.fit.elearning.common.helper.enums.BaseStatus;
import vn.edu.nlu.fit.elearning.common.helper.enums.Role;
import vn.edu.nlu.fit.elearning.common.helper.pagination.filter.user.UserFilter;
import vn.edu.nlu.fit.elearning.feature.user.admin.dto.UserAdminDto;
import vn.edu.nlu.fit.elearning.feature.user.common.model.User;

import java.util.List;

public interface UserAdminDao {
    int updateRole(int userId, Role role, BaseStatus status);

    List<User> findUsersByFilter(String username, String phone, String dateFrom, String role);

    int create(User user);

    List<UserAdminDto> findAll();

    int update(User entity);

    int delete(Integer integer);

    List<UserAdminDto> findUsersByFilter(UserFilter filter);

    int countUsersByFilter(UserFilter filter);

    UserAdminDto findById(int id);


}
