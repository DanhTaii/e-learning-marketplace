package vn.edu.nlu.fit.elearning.feature.user.service;

import vn.edu.nlu.fit.elearning.common.helper.enums.BaseStatus;
import vn.edu.nlu.fit.elearning.common.helper.enums.Role;
import vn.edu.nlu.fit.elearning.feature.user.dao.UserDao;
import vn.edu.nlu.fit.elearning.feature.user.dto.request.UserProfileRequest;
import vn.edu.nlu.fit.elearning.feature.user.dto.request.UserRoleStatusRequest;
import vn.edu.nlu.fit.elearning.feature.user.dto.response.UserDetailResponse;
import vn.edu.nlu.fit.elearning.feature.user.dto.response.UserProfileResponse;
import vn.edu.nlu.fit.elearning.feature.user.dto.response.UserShortResponse;
import vn.edu.nlu.fit.elearning.feature.user.dto.response.UserTableResponse;
import vn.edu.nlu.fit.elearning.feature.user.mapper.UserMapper;
import vn.edu.nlu.fit.elearning.feature.user.model.User;

import java.util.List;
import java.util.Objects;

public class UserServiceImpl implements UserService {
    private final UserDao userDao;

    public UserServiceImpl(UserDao userDao) {
        this.userDao = userDao;
    }

    //Base Crud
    @Override
    public int createUser(User user) {
        return userDao.create(user);
    }

    @Override
    public List<UserTableResponse> getAllUsers() {
        List<User> users = userDao.findAll();
        return UserMapper.toUserTableDto(users);
    }

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
        return userDao.update(user);
    }

    @Override
    public int deleteUser(int id) {
        return userDao.delete(id);
    }

    //Lấy thông tin người dùng
    @Override
    public User getEntityByEmail(String email) {
        return userDao.findUserByEmail(email);
    }

    @Override
    public List<User> getAllUsersByFilter(String username, String phone, String createdAt, String role) {
        return userDao.findUsersByFilter(username, phone, createdAt, role);
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
    public int updateRole(int userId, UserRoleStatusRequest req) {
        Role role = req.getRole();
        BaseStatus status = req.getStatus();
        return userDao.updateRole(userId, role, status);
    }

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
        return userDao.update(currentUser) > 0;
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
}
