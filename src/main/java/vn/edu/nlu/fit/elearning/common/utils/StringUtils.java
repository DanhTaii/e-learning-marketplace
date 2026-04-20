package vn.edu.nlu.fit.elearning.common.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringUtils {

    public static String escapeLikeWildcards(String value) {
        if (value == null) {
            return null;
        }
        return value.trim()
                .replace("!", "!!")      // Escape the escape character
                .replace("%", "!%")      // Escape % wildcard
                .replace("_", "!_");     // Escape _ wildcard
    }

    public static String toLikePattern(String value) {
        if (value == null) {
            return "%";
        }
        String escaped = escapeLikeWildcards(value);
        return "%" + escaped + "%";
    }

    public static boolean isEmpty(String value) {
        return value == null || value.trim().isEmpty();
    }

    public static boolean isNotEmpty(String value) {
        return !isEmpty(value);
    }

    public static String limit(String value, int maxLength) {
        if (isEmpty(value)) {
            return "";
        }
        if (value.length() > maxLength) {
            return value.substring(0, maxLength) + "...";
        }
        return value;
    }

    public static String generateCloneTitle(String originalTitle, String nameClone) {
        // Regex giải thích:
        // (.*)             : Group 1 - Tên gốc
        // \s               : Khoảng trắng
        // \(               : Mở ngoặc đơn
        // Pattern.quote    : Chèn chữ "Bản sao" an toàn
        // \s               : Khoảng trắng giữa chữ và số
        // (\d+)            : Group 2 - Con số
        // \)               : Đóng ngoặc đơn
        // $                : Kết thúc chuỗi
        String regex = "(.*) \\(" + Pattern.quote(nameClone) + " (\\d+)\\)$";

        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(originalTitle);

        if(matcher.matches()) {
            String baseTitle = matcher.group(1);
            int nextNumber = Integer.parseInt(matcher.group(2)) + 1;
            return baseTitle + " (" + nameClone + " " + nextNumber + ")";
        } else {
            return originalTitle + " (" + nameClone + " 1)";
        }

    }

}
