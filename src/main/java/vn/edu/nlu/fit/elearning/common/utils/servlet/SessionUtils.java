package vn.edu.nlu.fit.elearning.common.utils.servlet;

import jakarta.servlet.http.HttpServletRequest;

public class SessionUtils {

    public static Object getCurrentUser(HttpServletRequest request){
        Object object = request.getSession().getAttribute("userSession");
        if (object == null){
            throw new IllegalArgumentException("User not logged in");
        }
        return object;
    }

    public static int getCurrentUserId(HttpServletRequest request){
        String value = request.getSession().getAttribute("userId").toString();
        if (value == null || value.isEmpty()){
            throw new IllegalArgumentException("User not logged in");
        }
        try {
            return Integer.parseInt(value);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Invalid user ID in session");
        }
    }

    public static void setFlashSuccess(HttpServletRequest req, String message) {
        req.getSession(true).setAttribute("flashSuccess", message);
    }

    public static void setFlashError(HttpServletRequest req, String message) {
        req.getSession(true).setAttribute("flashError", message);
    }

}
