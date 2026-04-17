package vn.edu.nlu.fit.elearning.common.utils.servlet;

import jakarta.servlet.http.HttpServletRequest;
import vn.edu.nlu.fit.elearning.common.helper.enums.BaseStatus;
import vn.edu.nlu.fit.elearning.common.helper.enums.Role;

import java.sql.Timestamp;

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
        String value = request.getParameter(paramStatus);

        if (value == null || value.trim().isEmpty()) {
            return null;
        }

        try {
            return BaseStatus.valueOf(value.toUpperCase());
        } catch (IllegalArgumentException e) {
            return null;
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
//        String value = getParameterAsString(request, paramBoolean, "false");
//        return Boolean.parseBoolean(value);
        String value = request.getParameter(paramBoolean);
        return value != null;
    }

    public static Timestamp getParameterAsFromDate(HttpServletRequest request, String fromDate, Timestamp defaultValue) {
        String value = getParameterAsString(request, fromDate, null);
        if (value == null || value.trim().isEmpty()) {
            return defaultValue;
        }
        try {
            String timestampValue = value + " 00:00:00";
            return Timestamp.valueOf(timestampValue);
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Invalid date format for parameter: " + fromDate + ". Expected format: yyyy-MM-dd HH:mm:ss");
        }
    }

    public static Timestamp getParameterAsToDate(HttpServletRequest request, String toDate, Timestamp defaultValue) {
        String value = getParameterAsString(request, toDate, null);
        if (value == null || value.trim().isEmpty()) {
            return defaultValue;
        }
        try {
            String timestampValue = value + " 23:59:59";
            return Timestamp.valueOf(timestampValue);
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Invalid date format for parameter: " + toDate + ". Expected format: yyyy-MM-dd HH:mm:ss");
        }
    }
}
