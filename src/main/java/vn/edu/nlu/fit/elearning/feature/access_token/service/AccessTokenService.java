package vn.edu.nlu.fit.elearning.feature.access_token.service;

import java.sql.Timestamp;

public interface AccessTokenService {
    String generateToken();

    String generateTokenForVerify();

    Timestamp expireDateTime();

    boolean isExpireTime(Timestamp expireTime);

    boolean sendEmail(String email, String code, String name);

    boolean validateResetToken(int userId, String token);

    // Dùng cho đăng ký (không cần userId)
    boolean validateSignupToken(String token);

    boolean markAsUsed(String token);
}
