package vn.edu.nlu.fit.elearning.feature.user.admin.service;

import vn.edu.nlu.fit.elearning.common.helper.pagination.filter.user.UserFilter;
import vn.edu.nlu.fit.elearning.feature.user.admin.dto.UserAdminDto;
import vn.edu.nlu.fit.elearning.feature.user.common.model.User;
import vn.edu.nlu.fit.elearning.feature.user.student.dto.request.UserProfileRequest;
import vn.edu.nlu.fit.elearning.feature.user.student.dto.request.UserRoleStatusRequest;
import vn.edu.nlu.fit.elearning.feature.user.student.dto.response.UserTableResponse;

import java.util.List;

public interface UserAdminService {

    int updateRole(int userId, UserRoleStatusRequest req);

    int createUser(User user);

    int deleteUser(int id);

    List<UserAdminDto> getAllUsers();

    List<User> getAllUsersByFilter(String username, String phone, String createdAt, String role);

    List<UserAdminDto> getUsersByFilter(UserFilter filter);

    int countUsersByFilter(UserFilter filter);

    UserAdminDto getUserById(int id);

}
