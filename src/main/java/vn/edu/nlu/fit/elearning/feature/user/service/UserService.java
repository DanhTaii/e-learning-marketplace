package vn.edu.nlu.fit.elearning.feature.user.service;

import vn.edu.nlu.fit.elearning.feature.user.model.User;

import java.util.List;

public interface UserService {
    boolean updateUserProfile(User currentUser, String newUsername, String newPhone, String avatarUrl);

    List<User> getAllUsers();

    User getUserById(int id);

    int totalUsers();

    int createUser(User user);

    List<User> getAllUsersByFilter(String username, String phone, String createdAt, String role);

    User getUserByEmail(String email);

    User getUserByUsername(String email);

    int updateUser(User user);

    int updateRole(int userId, String role);

    int deleteUser(int id);
}
