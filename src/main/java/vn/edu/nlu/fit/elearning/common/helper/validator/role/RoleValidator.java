package vn.edu.nlu.fit.elearning.common.helper.validator.role;

import vn.edu.nlu.fit.elearning.feature.authorization.role.model.Role;

import java.util.HashMap;
import java.util.Map;

public class RoleValidator {

    public static Map<String, String> validate(Role role) {

        Map<String, String> errors = new HashMap<>();

        // ===== NAME =====
        if (role.getName() == null || role.getName().trim().isEmpty()) {
            errors.put("name", "Tên role không được để trống!");
        } else if (role.getName().length() < 3) {
            errors.put("name", "Tên role phải có ít nhất 3 ký tự!");
        } else if (role.getName().length() > 50) {
            errors.put("name", "Tên role không được vượt quá 50 ký tự!");
        }

        // ===== DESCRIPTION =====
        if (role.getDescription() != null && role.getDescription().length() > 255) {
            errors.put("description", "Mô tả không được vượt quá 255 ký tự!");
        }

        return errors;
    }
}