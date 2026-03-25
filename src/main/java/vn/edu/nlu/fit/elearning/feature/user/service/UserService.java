package vn.edu.nlu.fit.elearning.feature.user.service;

import vn.edu.nlu.fit.elearning.common.helper.enums.BaseStatus;
import vn.edu.nlu.fit.elearning.feature.user.dto.UserShortDto;
import vn.edu.nlu.fit.elearning.feature.user.model.User;

import java.util.List;

public interface UserService {
    boolean updateUserProfile(User currentUser, String newUsername, String newPhone, String avatarUrl);

    List<User> getAllUsers();

    User getUserById(int id);

    User getEntityByEmail(String email);

    int totalUsers();

    int createUser(User user);

    List<User> getAllUsersByFilter(String username, String phone, String createdAt, String role);

    UserShortDto getUserByEmail(String email);

    boolean getUserByUsername(String email);

    int updateUser(User user);

    int updateRole(int userId, String role, BaseStatus status);

    int deleteUser(int id);

    int changePasswordByEmail(String newPassword, String userMail);

    boolean existsUserByEmail(String email);
}
