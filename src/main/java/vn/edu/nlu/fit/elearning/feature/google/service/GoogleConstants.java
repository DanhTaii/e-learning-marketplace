package vn.edu.nlu.fit.elearning.feature.google.service;

public class GoogleConstants {

    public static final String GOOGLE_CLIENT_ID = System.getenv("GOOGLE_CLIENT_ID");

    public static final String GOOGLE_CLIENT_SECRET = System.getenv("GOOGLE_CLIENT_SECRET");

    public static final String GOOGLE_REDIRECT_URI_LOCAL = "http://localhost:8080/e_learning_war_exploded/sign-in/google";

    public static final String GOOGLE_REDIRECT_URI_PRODUCTION = "https://wabi.id.vn/sign-in/google";

    public static final String GOOGLE_LINK_GET_TOKEN = "https://accounts.google.com/o/oauth2/token";

    public static final String GOOGLE_LINK_GET_USER_INFO = "https://www.googleapis.com/oauth2/v1/userinfo?access_token=";

    public static String getRedirectUri() {
        String env = System.getenv("ENV");
        if ("production".equals(env)) {
            return GOOGLE_REDIRECT_URI_PRODUCTION;
        }
        return GOOGLE_REDIRECT_URI_LOCAL;
    }
}