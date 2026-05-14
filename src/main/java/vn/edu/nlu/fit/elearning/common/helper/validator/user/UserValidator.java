package vn.edu.nlu.fit.elearning.common.helper.validator.user;

import vn.edu.nlu.fit.elearning.common.utils.validation.ValidationUtils;
import vn.edu.nlu.fit.elearning.feature.user.admin.dto.UserAdminDto;
import vn.edu.nlu.fit.elearning.feature.user.student.service.UserService;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class UserValidator {
    private static final Set<String> RESERVED_WORDS = Set.of(
            "admin", "root", "system", "test", "null", "superuser"
    );

    public static Map<String, String> validate(UserAdminDto user, String password, String confirmPassword, UserService userService, boolean isCreate) {
        Map<String, String> errors = new HashMap<>();

        // FIRST NAME
        if (ValidationUtils.isEmpty(user.getFirstName())) {
            errors.put("firstName", "Họ không được để trống!");
        } else {
            String err = ValidationUtils.checkLength(
                    user.getFirstName(), "Họ", 2, 50
            );
            if (err != null) {
                errors.put("firstName", err);
            }
        }

        // LAST NAME
        if (ValidationUtils.isEmpty(user.getLastName())) {
            errors.put("lastName", "Tên không được để trống!");
        } else {
            String err = ValidationUtils.checkLength(
                    user.getLastName(),
                    "Tên", 2, 50
            );

            if (err != null) {
                errors.put("lastName", err);
            }
        }

        // USERNAME
        if (ValidationUtils.isEmpty(user.getUsername())) {
            errors.put("username", "Tên người dùng không được để trống!");

        } else {
            String username = user.getUsername().trim();
            String err = ValidationUtils.checkLength(
                    username, "Tên người dùng", 3, 20
            );

            if (err != null) {
                errors.put("username", err);

            } else if (!username.matches("^[a-zA-Z0-9._]+$")) {
                errors.put("username", "Chỉ được chứa chữ, số, dấu chấm hoặc dấu gạch dưới!");

            } else if (isReservedWord(username)) {
                errors.put("username", "Tên người dùng không hợp lệ!");
            } else {
                boolean usernameExists;
                // create
                if (isCreate) {
                    usernameExists = userService.existsUserByUsername(username.toLowerCase());
                }

                // update
                else {
                    usernameExists = userService.existsUserByUsername(username.toLowerCase());
                }

                if (usernameExists) {
                    errors.put("username", "Tên người dùng đã tồn tại!");
                }
            }
        }

        // EMAIL
        if (ValidationUtils.isEmpty(user.getEmail())) {
            errors.put("email", "Email không được để trống!");
        } else {

            String email = user.getEmail().trim();
            if (!email.matches("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$")) {
                errors.put("email", "Email không đúng định dạng!");

            } else {

                boolean emailExists;
                if (isCreate) {
                    emailExists = userService.getUserByEmail(email) != null;
                } else {
                    emailExists = userService.getUserByEmail(email) != null;
                }
                if (emailExists) {
                    errors.put("email", "Email đã tồn tại!");
                }
            }
        }

        // PHONE
        if (ValidationUtils.isEmpty(user.getPhone())) {
            errors.put("phone", "Số điện thoại không được để trống!");
        } else {
            String phone = user.getPhone().trim();
            if (!phone.matches("^(0|\\+84)[0-9]{9,10}$")) {
                errors.put("phone", "Số điện thoại không hợp lệ!");
            }
        }

        // PASSWORD
        if (isCreate) {
            if (ValidationUtils.isEmpty(password)) {
                errors.put("password", "Mật khẩu không được để trống!");
            } else {
                if (password.length() < 8 || password.length() > 25) {
                    errors.put("password", "Mật khẩu phải từ 8 đến 25 ký tự!");

                } else if (!password.matches(".*[A-Z].*")) {
                    errors.put("password", "Phải có ít nhất 1 chữ hoa!");

                } else if (!password.matches(".*[a-z].*")) {

                    errors.put("password", "Phải có ít nhất 1 chữ thường!");

                } else if (!password.matches(".*\\d.*")) {
                    errors.put("password", "Phải có ít nhất 1 chữ số!");

                } else if (!password.matches(".*[!@#$%^&*(),.?\":{}|<>_\\-\\[\\]\\\\/+=~`].*")) {
                    errors.put("password", "Phải có ít nhất 1 ký tự đặc biệt!");
                }
            }

            // confirm password
            if (ValidationUtils.isEmpty(confirmPassword)) {

                errors.put("confirmPassword",
                        "Xác nhận mật khẩu không được để trống!");

            } else if (!password.equals(confirmPassword)) {

                errors.put("confirmPassword",
                        "Mật khẩu xác nhận không khớp!");
            }
        }

        // STATUS
        if (user.getStatus() == null) {
            errors.put("status", "Trạng thái không hợp lệ!");
        }

        // ROLE
        if (ValidationUtils.isEmpty(user.getRoleName())) {
            errors.put("roleName", "Vai trò không được để trống!");
        }
        return errors;
    }

    private static boolean isReservedWord(String username) {
        return username != null &&
                RESERVED_WORDS.contains(username.toLowerCase());
    }
}