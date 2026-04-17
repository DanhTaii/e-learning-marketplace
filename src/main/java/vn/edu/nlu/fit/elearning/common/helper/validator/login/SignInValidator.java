package vn.edu.nlu.fit.elearning.common.helper.validator.login;

import java.util.HashMap;
import java.util.Map;

public class SignInValidator {

    public static Map<String, String> validate(String email, String password) {
        Map<String, String> errors = new HashMap<>();

        // Email
        if (email == null || email.isEmpty()) {
            errors.put("email", "Email không được để trống!");
        } else if (!email.matches("^[A-Za-z0-9+_.-]+@(.+)$")) {
            errors.put("email", "Email không đúng định dạng!");
        }

        // Password
        if (password == null || password.isEmpty()) {
            errors.put("password", "Mật khẩu không được để trống!");
        }

        return errors;
    }
}