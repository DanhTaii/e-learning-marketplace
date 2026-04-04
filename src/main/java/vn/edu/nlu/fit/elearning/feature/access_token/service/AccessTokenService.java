package vn.edu.nlu.fit.elearning.feature.access_token.service;

import vn.edu.nlu.fit.elearning.feature.access_token.model.AccessToken;

import java.sql.Timestamp;

public interface AccessTokenService {

    int createToken(AccessToken token);

    Timestamp expireDateTime();

    boolean isExpireTime(Timestamp expireTime);

    boolean validateResetToken(int userId, String token);

    // Dùng cho đăng ký (không cần userId)
    boolean validateSignupToken(String token);

    void markAsUsed(String token);
}
