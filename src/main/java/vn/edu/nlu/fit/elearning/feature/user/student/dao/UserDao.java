package vn.edu.nlu.fit.elearning.feature.user.student.dao;

import vn.edu.nlu.fit.elearning.common.database.BaseCrudDao;
import vn.edu.nlu.fit.elearning.common.helper.enums.BaseStatus;
import vn.edu.nlu.fit.elearning.common.helper.enums.Role;
import vn.edu.nlu.fit.elearning.feature.user.common.model.User;

import java.util.List;
import java.util.Set;

public interface UserDao {

    User findById(Integer integer);

    User findUserByEmail(String email);

    User findByProviderAndProviderId(String provider, String providerId);

    boolean findUserByUsername(String username);

    int resetPassword(String newPassword, String userMail);

    boolean existsUserByEmail(String email);

    int countUsersByTimeRange(String timeRange);

    Set<String> findPermissionsByUserId(Integer userId);

    Set<String> findRolesByUserId(Integer userId);

    // này là làm cho đăng nhập sai mật khẩu 5 lần là tài khoản sẽ bị khóa
    int increaseFailedAttempts(String email);

    int resetFailedAttempts(String email);

    int lockUserAccount(String email);

    int getFailedAttemptsByEmail(String email);

    int updateAvatar(int userId, String avatarUrl);

    User findByUsername(String username);

    int updateProfile(User user);

}
