package vn.edu.nlu.fit.elearning.feature.user.student.service;

import vn.edu.nlu.fit.elearning.common.helper.enums.BaseStatus;
import vn.edu.nlu.fit.elearning.common.helper.enums.Role;
import vn.edu.nlu.fit.elearning.feature.user.admin.dao.UserAdminDao;
import vn.edu.nlu.fit.elearning.feature.user.student.dao.UserDao;
import vn.edu.nlu.fit.elearning.feature.user.student.dto.request.UserProfileRequest;
import vn.edu.nlu.fit.elearning.feature.user.student.dto.request.UserRoleStatusRequest;
import vn.edu.nlu.fit.elearning.feature.user.student.dto.response.UserDetailResponse;
import vn.edu.nlu.fit.elearning.feature.user.student.dto.response.UserProfileResponse;
import vn.edu.nlu.fit.elearning.feature.user.student.dto.response.UserShortResponse;
import vn.edu.nlu.fit.elearning.feature.user.student.dto.response.UserTableResponse;
import vn.edu.nlu.fit.elearning.feature.user.mapper.UserMapper;
import vn.edu.nlu.fit.elearning.feature.user.common.model.User;

import java.util.List;
import java.util.Objects;
import java.util.Set;

public class UserServiceImpl implements UserService {
    private final UserDao userDao;
    private final UserAdminDao userAdminDao;

    public UserServiceImpl(UserDao userDao, UserAdminDao userAdminDao) {
        this.userDao = userDao;
        this.userAdminDao = userAdminDao;
    }

    //Base Crud

    @Override
    public UserDetailResponse getUserById(int id) {
        User user = userDao.findById(id);
        return UserMapper.toUserDetailDto(user);
    }

    @Override
    public int updateUser(int userId, UserProfileRequest req) {
        User user = userDao.findById(userId);
        user.setEmail(req.getEmail());
        user.setPhone(req.getPhone());
        user.setAvatarUrl(req.getAvatarUrl());
        return userAdminDao.update(user);
    }

    //Lấy thông tin người dùng
    @Override
    public User getEntityByEmail(String email) {
        return userDao.findUserByEmail(email);
    }

    @Override
    public UserShortResponse getUserByEmail(String email) {
        User user = userDao.findUserByEmail(email);
        return UserMapper.toUserShortDto(user);
    }

    @Override
    public UserProfileResponse getProfileById(int id) {
        User user = userDao.findById(id);
        return UserMapper.toUserProfileDto(user);
    }

    //Thao tác đến user

    @Override
    public boolean updateUserProfile(int userId, UserProfileRequest req) {
        String newUsername = req.getUsername();
        String newPhone = req.getPhone();
        String avatarUrl = req.getAvatarUrl();
        User currentUser = userDao.findById(userId);

        if (newUsername == null || newUsername.trim().isEmpty()) {
            throw new IllegalArgumentException("Tên hiển thị không được để trống!");
        }
        if (avatarUrl == null || avatarUrl.trim().isEmpty()) {
            throw new IllegalArgumentException("Đường link ảnh không được để trống!");
        }

        //So sánh bằng object sẽ an toàn do nếu dùng equal sẽ không xử lý được TH null
        boolean isUsernameChanged = !newUsername.equals(currentUser.getUsername());
        boolean isPhoneChanged = !Objects.equals(newPhone, currentUser.getPhone());
        boolean isAvatarChanged = !Objects.equals(avatarUrl, currentUser.getAvatarUrl());

        if (!isUsernameChanged && !isPhoneChanged && !isAvatarChanged) {
            throw new IllegalArgumentException("Bạn chưa thay đổi thông tin nào.");
        }

        if (isUsernameChanged && userDao.findUserByUsername(newUsername)) {
            throw new IllegalArgumentException("Tên người dùng đã tồn tại !");
        }

        if (newPhone != null && !newPhone.trim().isEmpty() && !newPhone.matches("\\d{10,11}")) {
            throw new IllegalArgumentException("Số điện thoại không hợp lệ!");
        }

        currentUser.setUsername(newUsername);
        currentUser.setPhone(newPhone);
        currentUser.setAvatarUrl(avatarUrl);
        return userAdminDao.update(currentUser) > 0;
    }

    @Override
    public int changePasswordByEmail(String newPassword, String userMail) {
        return userDao.resetPassword(newPassword, userMail);
    }

    //Kiểm tra tồn tại của email và username
    @Override
    public boolean existsUserByUsername(String username) {
        return userDao.findUserByUsername(username);
    }

    @Override
    public boolean existsUserByEmail(String email) {
        return userDao.existsUserByEmail(email);
    }
@Override
public int countUsersByTimeRange(String timeRange){
        return userDao.countUsersByTimeRange(timeRange);
}

    @Override
    public Set<String> getPermissionsByUserId(Integer userId) {
        return userDao.findPermissionsByUserId(userId);
    }

    @Override
    public Set<String> getRolesByUserId(Integer userId) {
        return userDao.findRolesByUserId(userId);
    }

    @Override
    public int increaseFailedAttempts(String email) {
        return userDao.increaseFailedAttempts(email);
    }

    @Override
    public int resetFailedAttempts(String email) {
        return userDao.resetFailedAttempts(email);
    }

    @Override
    public int lockUserAccount(String email) {
        return userDao.lockUserAccount(email);
    }

    @Override
    public int getFailedAttemptsByEmail(String email) {
        return userDao.getFailedAttemptsByEmail(email);
    }

}
