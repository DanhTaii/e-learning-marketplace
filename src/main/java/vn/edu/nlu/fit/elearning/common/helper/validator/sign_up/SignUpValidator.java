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
            String fullName,
            String password,
            String confirmPassword,
            UserService userService
    ) {
        Map<String, String> errors = new HashMap<>();

        // 1. Empty
        if (email == null || email.isEmpty()) {
            errors.put("email", "Email không được để trống!");
        }

        if (fullName == null || fullName.isEmpty()) {
            errors.put("fullName", "Tên người dùng không được để trống!");
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

        // 5. fullName validate
        if (fullName != null && !fullName.isEmpty()) {

            if (fullName.length() < 2 || fullName.length() > 50) {
                errors.put("fullName", "Họ tên phải từ 2 đến 50 ký tự!");
            }
            else if (!fullName.matches("^[\\p{L}\\s]+$")) {
                errors.put("fullName", "Họ tên chỉ được chứa chữ cái và khoảng trắng!");
            } else if (isReservedWord(fullName)) {
                errors.put("fullName", "Tên người dùng không hợp lệ!");
            }
        }

        return errors;
    }

    private static boolean isReservedWord(String fullName) {
        return fullName != null && RESERVED_WORDS.contains(fullName.toLowerCase());
    }
}