package vn.edu.nlu.fit.elearning.common.utils;

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

}
