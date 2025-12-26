package vn.edu.nlu.fit.elearning.services;

import vn.edu.nlu.fit.elearning.dao.UserDao;
import vn.edu.nlu.fit.elearning.model.GoogleUser;
import vn.edu.nlu.fit.elearning.model.User;
import vn.edu.nlu.fit.elearning.utils.PasswordUtils;

import java.sql.Timestamp;
import java.util.List;

public class UserService {
    private UserDao userDao;
    private static final String SALT = "SECRET";

    public UserService() {
        this.userDao = new UserDao();
    }

    public User login(String email, String password) {
        email = email.trim();
        password = password.trim();
        if (email.isEmpty() || password.isEmpty()) {
            System.out.println("Invalid username or password");
            return null;
        }

        User user = userDao.findUserByEmail(email);
        if (user == null) {
            System.out.println("Account hasn't exist !");
            return null;
        }

        String hash = PasswordUtils.hashpassword(password + SALT);
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

        if (password.length() < 8) {
            throw new IllegalArgumentException("Mật khẩu phải có ít nhất 8 ký tự");
        }

        if (!password.matches(".*[A-Za-z].*")) {
            throw new IllegalArgumentException("Mật khẩu phải chứa ít nhất 1 chữ cái");
        }

        if (!password.matches(".*[0-9].*")) {
            throw new IllegalArgumentException("Mật khẩu phải chứa ít nhất 1 chữ số");
        }

        if (!password.matches(".*[^A-Za-z0-9].*")) {
            throw new IllegalArgumentException("Mật khẩu phải chứa ít nhất 1 ký tự đặc biệt");
        }

        User user = new User();
        String hashPass = PasswordUtils.hashpassword(password + SALT);
        user.setEmail(email);
        user.setUsername(username);
        user.setPassword(hashPass);
        return createUser(user) > 0;
    }

    public List<User> getAllUsers() {
        return userDao.findAll();
    }

    public User getUserById(int id) {
        // Gọi DAO (CRUD: READ ONE)

        return null;
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

    public boolean resetUserPassword(String oldPassword, String newPassword, String retypeNewPassword, String userMail) {
        if (this.getUserByEmail(userMail) == null) {
            throw new IllegalArgumentException("Email không tồn tại !!!");
        } else {
            User user = this.getUserByEmail(userMail);
            if (!user.getPassword().equals(oldPassword)) {
                throw new IllegalArgumentException("Mật khẩu cũ không đúng !");
            }
            if (!newPassword.equals(retypeNewPassword)) {
                throw new IllegalArgumentException("Mật khẩu mới không khớp !");
            }
            if (userDao.resetPassword(newPassword, userMail) == 1) {
                return true;
            }
        }
        return false;
    }

    public User getUserByEmail(String email) {
        return userDao.findUserByEmail(email);
    }

}
