package vn.edu.nlu.fit.elearning.common.utils.validation;

import vn.edu.nlu.fit.elearning.common.utils.StringUtils;

public class ValidationUtils {

    public static String checkLength(String value, String label, int min, int max) {
        if (value == null || value.trim().isEmpty()) {
            return "Vui lòng nhập " + label;
        }
        String trimmedValue = value.trim();
        if (trimmedValue.length() < min) {
            return label + " phải có ít nhất " + min + " ký tự";
        }
        if (trimmedValue.length() > max) {
            return label + " không được vượt quá " + max + " ký tự";
        }
        return null;
    }

    public static boolean isNumeric(String value) {
        if (StringUtils.isEmpty(value)) {
            return false;
        }
        return value.matches("\\d+");
    }

    public static boolean isValidEmail(String email) {
        if (StringUtils.isEmpty(email)) {
            return false;
        }
        return email.matches("^[A-Za-z0-9+_.-]+@([A-Za-z0-9.-]+\\.[A-Za-z]{2,})$");
    }

    public static boolean isValidPhoneNumber(String phone) {
        if (StringUtils.isEmpty(phone)) {
            return false;
        }
        return phone.matches("\\d{10,11}");
    }

        public static boolean isEmpty(String value) {
            return value == null || value.trim().isEmpty();
        }

}
