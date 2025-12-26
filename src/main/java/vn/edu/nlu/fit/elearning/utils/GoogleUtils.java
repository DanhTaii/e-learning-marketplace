package vn.edu.nlu.fit.elearning.utils;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import org.apache.http.client.fluent.Form;
import org.apache.http.client.fluent.Request;
import vn.edu.nlu.fit.elearning.controller.auth.GoogleConstants;
import vn.edu.nlu.fit.elearning.model.GoogleUser;

import java.io.IOException;

public class GoogleUtils {

    // Bước 1: Đổi mã code lấy Access Token
    public static String getToken(String code) throws IOException {
        String response = Request.Post(GoogleConstants.GOOGLE_LINK_GET_TOKEN)
                .bodyForm(Form.form()
                        .add("client_id", GoogleConstants.GOOGLE_CLIENT_ID)
                        .add("client_secret", GoogleConstants.GOOGLE_CLIENT_SECRET)
                        .add("redirect_uri", GoogleConstants.GOOGLE_REDIRECT_URI)
                        .add("code", code)
                        .add("grant_type", "authorization_code")
                        .build())
                .execute().returnContent().asString();

        JsonObject jobj = new Gson().fromJson(response, JsonObject.class);
        return jobj.get("access_token").toString().replaceAll("\"", "");
    }

    // Bước 2: Dùng Access Token để lấy thông tin người dùng
    public static GoogleUser getUserInfo(final String accessToken) throws IOException {
        String link = GoogleConstants.GOOGLE_LINK_GET_USER_INFO + accessToken;
        String response = Request.Get(link).execute().returnContent().asString();

        return new Gson().fromJson(response, GoogleUser.class);
    }
}