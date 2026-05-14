package vn.edu.nlu.fit.elearning.feature.auth.service;

import vn.edu.nlu.fit.elearning.common.helper.enums.BaseStatus;
import vn.edu.nlu.fit.elearning.common.helper.enums.Role;
import vn.edu.nlu.fit.elearning.feature.auth.dto.LoginRequestDto;
import vn.edu.nlu.fit.elearning.feature.user.admin.service.UserAdminService;
import vn.edu.nlu.fit.elearning.feature.user.admin.service.UserAdminServiceImpl;
import vn.edu.nlu.fit.elearning.feature.user.student.dto.response.UserShortResponse;
import vn.edu.nlu.fit.elearning.feature.user.mapper.UserMapper;
import vn.edu.nlu.fit.elearning.feature.user.common.model.User;
import vn.edu.nlu.fit.elearning.feature.user.student.service.UserService;
import vn.edu.nlu.fit.elearning.feature.google.model.GoogleUser;
import vn.edu.nlu.fit.elearning.common.utils.security.PasswordUtils;

import java.util.Set;

public class AuthServiceImpl implements AuthService {
    private final UserService userService;
    private final UserAdminService userAdminService;

    public AuthServiceImpl(UserService userService, UserAdminService userAdminService) {
        this.userService = userService;
        this.userAdminService = userAdminService;
    }

    @Override
    public UserShortResponse login(LoginRequestDto loginRequestDto) {

        String email = loginRequestDto.getEmail().trim();
        String password = loginRequestDto.getPassword().trim();

        if (!userService.existsUserByEmail(email)) {
            throw new IllegalArgumentException("Tài khoản không tồn tại!");
        }

        User user = userService.getEntityByEmail(email);

        if (user.getStatus() == BaseStatus.INACTIVE) {
            throw new IllegalArgumentException("Tài khoản của bạn đang bị khóa, vui lòng liên hệ quản trị viên!");
        }

        String hash = PasswordUtils.hashpassword(password);

        if (!hash.equals(user.getPassword())) {

            userService.increaseFailedAttempts(email);

            int failedAttempts = userService.getFailedAttemptsByEmail(email);

            if (failedAttempts >= 5) {
                userService.lockUserAccount(email);
                throw new IllegalArgumentException("Tài khoản đã bị khóa do nhập sai mật khẩu quá 5 lần!");
            }

            throw new IllegalArgumentException("Bạn đã nhập sai mật khẩu " + failedAttempts + "/5 lần. Sai 5 lần tài khoản sẽ bị khóa!"
            );
        }

        // đăng nhập đúng -> reset lại số lần sai
        userService.resetFailedAttempts(email);

        return UserMapper.toUserShortDto(user);
    }

    @Override
    public User processSocialLogin(GoogleUser googleUser) {
        User user = userService.getEntityByEmail(googleUser.getEmail());
//        System.out.println("Tên lấy từ Google: " + googleUser.getGiven_name());
//        System.out.println("Tên lấy từ Google: " + googleUser.getFamily_name());
//        System.out.println("Tên lấy từ Google: " + googleUser.getName());

        if (user == null) {
            user = new User();
            user.setEmail(googleUser.getEmail());
            // Gọi đúng tên biến mới
            user.setUsername(googleUser.getName());
            user.setAvatarUrl(googleUser.getPicture());

            user.setPassword("");

            // Lưu vào database và lấy lại ID vừa tạo
            userAdminService.createUser(user);
        } else {
            // 3. Nếu ĐÃ CÓ: Cập nhật lại ảnh đại diện hoặc tên nếu Google có thay đổi
            user.setAvatarUrl(googleUser.getPicture());
            user.setUsername(googleUser.getName());
//            userService.updateUser(user);
        }

        return user;
    }

    @Override
    public boolean register(String email, String username, String password) {

        if (userService.existsUserByUsername(username)) {
            throw new IllegalArgumentException("Tên người dùng đã tồn tại");
        }

        PasswordUtils.validatePassword(password);

        User user = new User();
        String hashPass = PasswordUtils.hashpassword(password);
        user.setEmail(email);
        user.setUsername(username);
        user.setPassword(hashPass);
        return userAdminService.createUser(user) > 0;
    }

    @Override
    public boolean resetUserPassword(String oldPassword, String newPassword, String retypeNewPassword, String userMail) {
        String oldHash = PasswordUtils.hashpassword(oldPassword);

        if (userService.getUserByEmail(userMail) == null) {
            throw new IllegalArgumentException("Email không tồn tại !!!");
        } else {
            User user = userService.getEntityByEmail(userMail);
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

        PasswordUtils.validatePassword(newPassword);

        return userService.changePasswordByEmail(newHashPassword, userMail) == 1;
    }

    @Override
    public Set<String> getUserPermissions(Integer userId){
        return userService.getPermissionsByUserId(userId);
    }

    @Override
    public Set<String> getUserRoles(Integer userId){
        return userService.getRolesByUserId(userId);
    }


}
