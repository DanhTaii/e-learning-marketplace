package vn.edu.nlu.fit.elearning.common.utils.servlet;

import jakarta.servlet.http.HttpServletRequest;
import vn.edu.nlu.fit.elearning.common.helper.enums.BaseStatus;
import vn.edu.nlu.fit.elearning.common.helper.enums.Role;

public class RequestUtils {

    public static int getParameterAsInt(HttpServletRequest request, String paramNumber, int defaultValue) {
        String value = request.getParameter(paramNumber);
        if (value == null || value.trim().isEmpty()){
            return defaultValue;
        }
        try {
            return Integer.parseInt(value);
        } catch (NumberFormatException e) {
            return defaultValue;
        }
    }

    //Ép kiểu số an toàn, nếu lỗi hoặc rỗng thì trả về giá trị mặc định
    public static int getParameterAsIntOrDefault(String value, int defaultValue) {
        try {
            return (value == null || value.isBlank()) ? defaultValue : Integer.parseInt(value);
        } catch (NumberFormatException e) {
            return defaultValue;
        }
    }

    public static String getParameterAsString(HttpServletRequest request, String paramString, String defaultValue) {
        String value = request.getParameter(paramString);
        if (value == null || value.trim().isEmpty()){
            return defaultValue;
        }

        return value;
    }

    public static BaseStatus getParameterAsStatus(HttpServletRequest request, String paramStatus) {
        String value = getParameterAsString(request, paramStatus, "INACTIVE");

        try {
            return BaseStatus.valueOf(value.toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Invalid value for parameter: " + paramStatus);
        }
    }

    public static Role getParameterAsRole(HttpServletRequest request, String paramRole) {
        String value = getParameterAsString(request, paramRole, "USER");
        try {
            return Role.valueOf(value.toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Invalid value for parameter: " + paramRole);
        }
    }

    public static boolean getParameterAsBoolean(HttpServletRequest request, String paramBoolean) {
        String value = getParameterAsString(request, paramBoolean, "false");
        return Boolean.parseBoolean(value);
    }

}
