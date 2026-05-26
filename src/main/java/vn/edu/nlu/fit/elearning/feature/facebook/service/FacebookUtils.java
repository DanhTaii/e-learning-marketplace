package vn.edu.nlu.fit.elearning.feature.facebook.service;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import org.apache.http.client.fluent.Form;
import org.apache.http.client.fluent.Request;
import vn.edu.nlu.fit.elearning.feature.facebook.model.FacebookUser;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

public class FacebookUtils {

    // Bước 1: đổi code lấy access token
    public static String getToken(String code, String redirectUri) throws IOException {

        String response = Request.Post(FacebookConstants.FACEBOOK_LINK_GET_TOKEN).bodyForm(Form.form()
                        .add("client_id", FacebookConstants.FACEBOOK_APP_ID)
                        .add("client_secret", FacebookConstants.FACEBOOK_APP_SECRET)
                        .add("redirect_uri", redirectUri)
                        .add("code", code)
                        .build())

                .execute()
                .returnContent()
                .asString(StandardCharsets.UTF_8);

        JsonObject json = new Gson().fromJson(response, JsonObject.class);

        return json.get("access_token").getAsString();
    }

    // Bước 2: lấy user info
    public static FacebookUser getUserInfo(final String accessToken) throws IOException {

        String link = FacebookConstants.FACEBOOK_LINK_GET_USER_INFO + accessToken;

        String response = Request.Get(link).execute().returnContent().asString(StandardCharsets.UTF_8);

        System.out.println(response);

        return new Gson().fromJson(response, FacebookUser.class);
    }
}