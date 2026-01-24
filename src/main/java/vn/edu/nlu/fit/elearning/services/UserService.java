package vn.edu.nlu.fit.elearning.services;

import vn.edu.nlu.fit.elearning.dao.UserDao;
import vn.edu.nlu.fit.elearning.model.GoogleUser;
import vn.edu.nlu.fit.elearning.model.User;
import vn.edu.nlu.fit.elearning.utils.PasswordUtils;

import java.util.List;
import java.util.Objects;

public class UserService {
    private UserDao userDao;

    public UserService() {
        this.userDao = new UserDao();
    }

    public User login(String email, String password) {
        email = email.trim();
        password = password.trim();
        if (email.isEmpty() || password.isEmpty()) {
            System.out.println("Mật khẩu hoặc tên người dùng không đúng");
            return null;
        }

        User user = userDao.findUserByEmail(email);
        if (user == null) {
            System.out.println("Tài khoản không tồn tại");
            return null;
        }

        String hash = PasswordUtils.hashpassword(password);
        if (email.equals(user.getEmail()) && hash.equals(user.getPassword())) {
            return user;
        }
        return null;
    }

    public User processSocialLogin(GoogleUser googleUser) {
        User user = userDao.findUserByEmail(googleUser.getEmail());

        if (user == null) {
            user = new User();
            user.setEmail(googleUser.getEmail());
            user.setFirstName(googleUser.getGivenName());
            user.setLastName(googleUser.getFamilyName());
//            user.setAvatar_url(googleUser.getPicture());
            user.setRole("user"); // Mặc định là khách hàng
            user.setPassword(""); // Đăng nhập qua Google nên không cần mật khẩu local

            // Lưu vào database và lấy lại ID vừa tạo
            createUser(user);
        } else {
            // 3. Nếu ĐÃ CÓ: Cập nhật lại ảnh đại diện hoặc tên nếu Google có thay đổi
            user.setAvatarUrl(googleUser.getPicture());
        }

        return user;
    }

    public boolean register(String email, String username, String password) {

        if (userDao.findUserByUsername(username) != null) {
            throw new IllegalArgumentException("Tên người dùng đã tồn tại");
        }

        validatePassword(password);

        User user = new User();
        String hashPass = PasswordUtils.hashpassword(password);
        user.setEmail(email);
        user.setUsername(username);
        user.setPassword(hashPass);
        return createUser(user) > 0;
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

    public boolean resetUserPassword(String oldPassword, String newPassword, String retypeNewPassword, String userMail) {
        String oldHash = PasswordUtils.hashpassword(oldPassword);

        if (this.getUserByEmail(userMail) == null) {
            throw new IllegalArgumentException("Email không tồn tại !!!");
        } else {
            User user = this.getUserByEmail(userMail);
            if (!user.getPassword().equals(oldHash)) {
                throw new IllegalArgumentException("Mật khẩu cũ không đúng !");
            }

            boolean checkReset = changePassword(newPassword, retypeNewPassword, userMail);

            if (checkReset) {
                return true;
            }
        }
        return false;
    }

    public boolean changePassword(String newPassword, String retypeNewPassword, String userMail) {
        if (!newPassword.equals(retypeNewPassword)) {
            throw new IllegalArgumentException("Mật khẩu mới không khớp !");
        }

        String newHashPassword = PasswordUtils.hashpassword(newPassword);

        validatePassword(newPassword);

        return userDao.resetPassword(newHashPassword, userMail) == 1;
    }

    public void validatePassword(String password) {
        if (password.length() < 8) {
            throw new IllegalArgumentException("Mật khẩu phải có ít nhất 8 ký tự");
        }

        if (!password.matches(".*[a-z].*") || !password.matches(".*[A-Z].*")) {
            throw new IllegalArgumentException("Mật khẩu phải chứa ít nhất 1 chữ thường và 1 chữ hoa");
        }

        if (!password.matches(".*[0-9].*")) {
            throw new IllegalArgumentException("Mật khẩu phải chứa ít nhất 1 chữ số");
        }

        if (!password.matches(".*[^A-Za-z0-9].*")) {
            throw new IllegalArgumentException("Mật khẩu phải chứa ít nhất 1 ký tự đặc biệt");
        }
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

    public int deleteUser(int id) {
        return userDao.delete(id);
    }

}
