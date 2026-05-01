package vn.edu.nlu.fit.elearning.common.helper.validator.sign_up;

import vn.edu.nlu.fit.elearning.feature.user.student.service.UserService;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class SignUpValidator {

    private static final Set<String> RESERVED_WORDS = Set.of(
            "admin", "root", "system", "test", "null", "superuser"
    );

    public static Map<String, String> validate(
            String email,
            String username,
            String password,
            String confirmPassword,
            UserService userService
    ) {
        Map<String, String> errors = new HashMap<>();

        // 1. Empty
        if (email == null || email.isEmpty()) {
            errors.put("email", "Email không được để trống!");
        }

        if (username == null || username.isEmpty()) {
            errors.put("username", "Tên người dùng không được để trống!");
        }

        if (password == null || password.isEmpty()) {
            errors.put("password", "Mật khẩu không được để trống!");
        }

        if (confirmPassword == null || confirmPassword.isEmpty()) {
            errors.put("confirmPassword", "Xác nhận mật khẩu không được để trống!");
        }

        // 2. Password rule (THÊM VÀO ĐÂY)
        if (password != null && !password.isEmpty()) {

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

        // 3. Password match
        if (password != null && confirmPassword != null && !password.equals(confirmPassword)) {
            errors.put("confirmPassword", "Mật khẩu xác nhận không khớp!");
        }

        // 4. Email tồn tại
        if (email != null && !email.isEmpty() && userService.getUserByEmail(email) != null) {
            errors.put("email", "Email đã tồn tại!");
        }

        // 5. Username validate
        if (username != null && !username.isEmpty()) {

            if (username.length() < 3 || username.length() > 20) {
                errors.put("username", "Tên người dùng phải từ 3 đến 20 ký tự!");
            } else if (!username.matches("^[a-zA-Z0-9._]+$")) {
                errors.put("username", "Chỉ được chứa chữ, số, . hoặc _");
            } else if (userService.existsUserByUsername(username.toLowerCase())) {
                errors.put("username", "Tên người dùng đã tồn tại!");
            } else if (isReservedWord(username)) {
                errors.put("username", "Tên người dùng không hợp lệ!");
            }
        }

        return errors;
    }

    private static boolean isReservedWord(String username) {
        return username != null && RESERVED_WORDS.contains(username.toLowerCase());
    }
}