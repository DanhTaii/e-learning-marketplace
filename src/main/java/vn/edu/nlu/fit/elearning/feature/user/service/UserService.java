package vn.edu.nlu.fit.elearning.feature.user.service;

import vn.edu.nlu.fit.elearning.feature.user.dao.UserDao;
import vn.edu.nlu.fit.elearning.utils.objects.GoogleUser;
import vn.edu.nlu.fit.elearning.feature.user.model.User;
import vn.edu.nlu.fit.elearning.utils.objects.PasswordUtils;

import java.util.List;
import java.util.Objects;

public class UserService {
    private UserDao userDao;

    public UserService() {
        this.userDao = new UserDao();
    }

    public boolean updateUserProfile(User currentUser, String newUsername, String newPhone, String avatarUrl) {

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

        if (isUsernameChanged) {
            if (userDao.findUserByUsername(newUsername) != null) {
                throw new IllegalArgumentException("Tên người dùng đã tồn tại !");
            }
        }

        if (newPhone != null && !newPhone.trim().isEmpty()) {
            if (!newPhone.matches("\\d{10,11}")) {
                throw new IllegalArgumentException("Số điện thoại không hợp lệ!");
            }
        }

        currentUser.setUsername(newUsername);
        currentUser.setPhone(newPhone);
        currentUser.setAvatarUrl(avatarUrl);
        return userDao.update(currentUser) > 0;
    }

    public List<User> getAllUsers() {
        return userDao.findAll();
    }

    public User getUserById(int id) {
        return userDao.findById(id);
    }

    public int totalUsers() {
        int result = 0;

        List<User> userList = userDao.findAll();
        for (User u : userList) {
            if (u.getRole().equals("user")) {
                result++;
            }
        }
        return result;
    }

    public int createUser(User user) {
        return userDao.create(user);
    }

    public List<User> getAllUsersByFilter(String username, String phone, String createdAt, String role) {
        return userDao.findUsersByFilter(username, phone, createdAt, role);
    }

    public User getUserByEmail(String email) {
        return userDao.findUserByEmail(email);
    }

    public User getUserByUsername(String email) {
        return userDao.findUserByUsername(email);
    }

    public int updateUser(User user) {
        return userDao.update(user);
    }
    public int updateRole(int userId, String role) {
        return userDao.updateRole(userId,role);
    }

    public int deleteUser(int id) {
        return userDao.delete(id);
    }

}
