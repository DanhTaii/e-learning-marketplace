package vn.edu.nlu.fit.elearning.feature.user.student.service;

import vn.edu.nlu.fit.elearning.feature.user.student.dto.request.UserProfileRequest;
import vn.edu.nlu.fit.elearning.feature.user.student.dto.request.UserRoleStatusRequest;
import vn.edu.nlu.fit.elearning.feature.user.student.dto.response.UserDetailResponse;
import vn.edu.nlu.fit.elearning.feature.user.student.dto.response.UserProfileResponse;
import vn.edu.nlu.fit.elearning.feature.user.student.dto.response.UserShortResponse;
import vn.edu.nlu.fit.elearning.feature.user.student.dto.response.UserTableResponse;
import vn.edu.nlu.fit.elearning.feature.user.common.model.User;
import java.util.List;
import java.util.Set;

public interface UserService {
    //Base Crud

    UserDetailResponse getUserById(int id);

    //Lấy thông tin người dùng
    User getEntityByEmail(String email);

    UserShortResponse getUserByEmail(String email);

    //Lấy toàn bộ thông tin người dùng
    User getEntityByProviderAndProviderId(String provider, String providerId);

    //Lấy thông tin người dùng vừa đủ, không lấy quá nhiều trường không cần thiết
    UserShortResponse getUserByProviderAndProviderId(String provider, String providerId);

    UserProfileResponse getProfileById(int id);

    //Thao tác đến user
    boolean updateUserProfile(int userId, UserProfileRequest req);

    int changePasswordByEmail(String newPassword, String userMail);

    //Kiểm tra tồn tại của email và username
    boolean existsUserByEmail(String email);

    boolean existsUserByUsername(String username);

    int countUsersByTimeRange(String timeRange);

    Set<String> getPermissionsByUserId(Integer userId);

    Set<String> getRolesByUserId(Integer userId);

    int updateUser(int id, UserProfileRequest user);

    int increaseFailedAttempts(String email);

    int resetFailedAttempts(String email);

    int lockUserAccount(String email);

    int getFailedAttemptsByEmail(String email);

    boolean updateAvatar(int userId, String avatarUrl);


    boolean createRegisteredUser(User user);
    
}
