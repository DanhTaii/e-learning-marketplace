package vn.edu.nlu.fit.elearning.feature.auth.service;

import vn.edu.nlu.fit.elearning.common.container.BeanContainer;
import vn.edu.nlu.fit.elearning.feature.user.dao.UserDao;
import vn.edu.nlu.fit.elearning.feature.user.dao.UserDaoImpl;
import vn.edu.nlu.fit.elearning.feature.user.model.User;
import vn.edu.nlu.fit.elearning.feature.user.service.UserService;
import vn.edu.nlu.fit.elearning.feature.google.model.GoogleUser;
import vn.edu.nlu.fit.elearning.common.utils.objects.PasswordUtils;

public class AuthServiceImpl implements AuthService {
    private UserService userService;

    public AuthServiceImpl() {
        this.userService = BeanContainer.getBean(UserService.class);
    }

    @Override
    public User login(String email, String password) {
        email = email.trim();
        password = password.trim();
        if (email.isEmpty() || password.isEmpty()) {
            throw new IllegalArgumentException("Vui lòng điền thông tin !");
        }

        User user = userService.getUserByEmail(email);
        if (user == null) {
            throw new IllegalArgumentException("Tài khoản không tồn tại");
        }

        String hash = PasswordUtils.hashpassword(password);
        if (email.equals(user.getEmail()) && hash.equals(user.getPassword())) {
            return user;
        }
        return null;
    }

    @Override
    public User processSocialLogin(GoogleUser googleUser) {
        User user = userService.getUserByEmail(googleUser.getEmail());
//        System.out.println("Tên lấy từ Google: " + googleUser.getGiven_name());
//        System.out.println("Tên lấy từ Google: " + googleUser.getFamily_name());
//        System.out.println("Tên lấy từ Google: " + googleUser.getName());

        if (user == null) {
            user = new User();
            user.setEmail(googleUser.getEmail());
            // Gọi đúng tên biến mới
            user.setUsername(googleUser.getName());
            user.setAvatarUrl(googleUser.getPicture());

            user.setRole("user");
            user.setPassword("");

            // Lưu vào database và lấy lại ID vừa tạo
            userService.createUser(user);
        } else {
            // 3. Nếu ĐÃ CÓ: Cập nhật lại ảnh đại diện hoặc tên nếu Google có thay đổi
            user.setAvatarUrl(googleUser.getPicture());
            user.setUsername(googleUser.getName());
            userService.updateUser(user);
        }

        return user;
    }

    @Override
    public boolean register(String email, String username, String password) {

        if (userService.getUserByEmail(username) != null) {
            throw new IllegalArgumentException("Tên người dùng đã tồn tại");
        }

        validatePassword(password);

        User user = new User();
        String hashPass = PasswordUtils.hashpassword(password);
        user.setEmail(email);
        user.setUsername(username);
        user.setPassword(hashPass);
        return userService.createUser(user) > 0;
    }

    @Override
    public boolean resetUserPassword(String oldPassword, String newPassword, String retypeNewPassword, String userMail) {
        String oldHash = PasswordUtils.hashpassword(oldPassword);

        if (userService.getUserByEmail(userMail) == null) {
            throw new IllegalArgumentException("Email không tồn tại !!!");
        } else {
            User user = userService.getUserByEmail(userMail);
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

    @Override
    public boolean changePassword(String newPassword, String retypeNewPassword, String userMail) {
        if (!newPassword.equals(retypeNewPassword)) {
            throw new IllegalArgumentException("Mật khẩu mới không khớp !");
        }

        String newHashPassword = PasswordUtils.hashpassword(newPassword);

        validatePassword(newPassword);

        return userService.changePasswordByEmail(newHashPassword, userMail) == 1;
    }

    @Override
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
}
