package vn.edu.nlu.fit.elearning.feature.auth.service;

import vn.edu.nlu.fit.elearning.common.helper.enums.BaseStatus;
import vn.edu.nlu.fit.elearning.feature.auth.dto.LoginRequestDto;
import vn.edu.nlu.fit.elearning.feature.facebook.model.FacebookUser;
import vn.edu.nlu.fit.elearning.feature.user.admin.service.UserAdminService;
import vn.edu.nlu.fit.elearning.feature.user.student.dto.response.UserShortResponse;
import vn.edu.nlu.fit.elearning.feature.user.mapper.UserMapper;
import vn.edu.nlu.fit.elearning.feature.user.common.model.User;
import vn.edu.nlu.fit.elearning.feature.user.student.service.UserService;
import vn.edu.nlu.fit.elearning.feature.google.model.GoogleUser;
import vn.edu.nlu.fit.elearning.common.utils.security.HashUtils;

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

        String hash = HashUtils.hashpassword(password);

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
    public UserShortResponse processSocialLogin(GoogleUser googleUser) {
        UserShortResponse user = userService.getUserByEmail(googleUser.getEmail());
//        System.out.println("Tên lấy từ Google: " + googleUser.getGiven_name());
//        System.out.println("Tên lấy từ Google: " + googleUser.getFamily_name());
//        System.out.println("Tên lấy từ Google: " + googleUser.getName());

        if (user == null) {
            User newUser = new User();
            newUser.setEmail(googleUser.getEmail());
            // Gọi đúng tên biến mới
            newUser.setUsername(googleUser.getName());
            newUser.setAvatarUrl(googleUser.getPicture());

            newUser.setPassword("");

            // Lưu vào database và lấy lại ID vừa tạo
            userAdminService.createUser(newUser);

            user = userService.getUserByEmail(googleUser.getEmail());
        } else {
            // 3. Nếu ĐÃ CÓ: Cập nhật lại ảnh đại diện hoặc tên nếu Google có thay đổi
            user.setAvatarUrl(googleUser.getPicture());
            user.setUsername(googleUser.getName());
//            userService.updateUser(user);
        }

        return user;
    }

    @Override
    public boolean register(String email, String fullName, String password) {

        fullName = fullName.trim().replaceAll("\\s+", " ");

        String[] parts = fullName.split(" ");

        String firstName;
        String lastName = "";

        if (parts.length == 1) {
            firstName = parts[0];
        } else {
            firstName = parts[parts.length - 1];

            StringBuilder lastNameBuilder = new StringBuilder();

            for (int i = 0; i < parts.length - 1; i++) {
                if (i > 0) {
                    lastNameBuilder.append(" ");
                }
                lastNameBuilder.append(parts[i]);
            }
            lastName = lastNameBuilder.toString();
        }

        HashUtils.validatePassword(password);
        String hashPass = HashUtils.hashpassword(password);

        User user = new User();
        user.setEmail(email);
        user.setUsername(fullName);
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setPassword(hashPass);

        return userAdminService.createUser(user) > 0;
    }

    @Override
    public boolean resetUserPassword(String oldPassword, String newPassword, String retypeNewPassword, String userMail) {
        String oldHash = HashUtils.hashpassword(oldPassword);

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

        String newHashPassword = HashUtils.hashpassword(newPassword);

        HashUtils.validatePassword(newPassword);

        return userService.changePasswordByEmail(newHashPassword, userMail) == 1;
    }

    @Override
    public Set<String> getUserPermissions(Integer userId) {
        return userService.getPermissionsByUserId(userId);
    }

    @Override
    public Set<String> getUserRoles(Integer userId) {
        return userService.getRolesByUserId(userId);
    }

    public UserShortResponse processFacebookLogin(FacebookUser facebookUser) {
        UserShortResponse user = userService.getUserByProviderAndProviderId("FACEBOOK", facebookUser.getId());

        String email = facebookUser.getEmail();

        // Xử lý việc có mail hay không có mail
        if (email == null || email.trim().isEmpty()) {
            // Nếu không có mail, tự sinh: "1503723471434396@facebook.wabi.id.vn"
            email = facebookUser.getId() + "@facebook.wabi.id.vn";
        }

        if (user == null) {
            User newUser = new User();
            newUser.setProvider("FACEBOOK");
            newUser.setProviderId(facebookUser.getId());
            newUser.setEmail(email);
            newUser.setFirstName(facebookUser.getFirstName());
            newUser.setLastName(facebookUser.getLastName());
            newUser.setAvatarUrl(facebookUser.getAvatar());
            newUser.setPassword("");

            userAdminService.createUser(newUser);

            user = userService.getUserByProviderAndProviderId("FACEBOOK", facebookUser.getId());
        }

        return user;
    }

}
