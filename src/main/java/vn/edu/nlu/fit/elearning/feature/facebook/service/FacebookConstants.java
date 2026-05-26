package vn.edu.nlu.fit.elearning.feature.facebook.service;

public class FacebookConstants {

    public static final String FACEBOOK_APP_ID = System.getenv("FACEBOOK_APP_ID");

    public static final String FACEBOOK_APP_SECRET = System.getenv("FACEBOOK_APP_SECRET");

    public static final String FACEBOOK_REDIRECT_URI = "https://e-learning.id.vn/sign-in/facebook";

    public static final String FACEBOOK_LINK_GET_TOKEN = "https://graph.facebook.com/v19.0/oauth/access_token";

    public static final String FACEBOOK_LINK_GET_USER_INFO = "https://graph.facebook.com/me?fields=id,name,email,picture.type(large)&access_token=";

    public static String getRedirectUri() {
        return FACEBOOK_REDIRECT_URI;
    }
}