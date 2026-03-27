package vn.edu.nlu.fit.elearning.feature.user.service;

import vn.edu.nlu.fit.elearning.feature.user.dto.request.UserProfileRequest;
import vn.edu.nlu.fit.elearning.feature.user.dto.request.UserRoleStatusRequest;
import vn.edu.nlu.fit.elearning.feature.user.dto.response.UserDetailResponse;
import vn.edu.nlu.fit.elearning.feature.user.dto.response.UserProfileResponse;
import vn.edu.nlu.fit.elearning.feature.user.dto.response.UserShortResponse;
import vn.edu.nlu.fit.elearning.feature.user.dto.response.UserTableResponse;
import vn.edu.nlu.fit.elearning.feature.user.model.User;
import java.util.List;

public interface UserService {
    //Base Crud
    int createUser(User user);

    int updateUser(int id, UserProfileRequest user);

    int deleteUser(int id);

    List<UserTableResponse> getAllUsers();

    UserDetailResponse getUserById(int id);

    //Lấy thông tin người dùng
    User getEntityByEmail(String email);

    List<User> getAllUsersByFilter(String username, String phone, String createdAt, String role);

    UserShortResponse getUserByEmail(String email);

    UserProfileResponse getProfileById(int id);

    //Thao tác đến user
    boolean updateUserProfile(int userId, UserProfileRequest req);

    int updateRole(int userId, UserRoleStatusRequest req);

    int changePasswordByEmail(String newPassword, String userMail);

    //Kiểm tra tồn tại của email và username
    boolean existsUserByEmail(String email);

    boolean existsUserByUsername(String username);
}
