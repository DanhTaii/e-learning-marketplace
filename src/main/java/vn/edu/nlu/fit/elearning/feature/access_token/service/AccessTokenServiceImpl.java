package vn.edu.nlu.fit.elearning.feature.access_token.service;

import vn.edu.nlu.fit.elearning.common.external.mail.MailService;
import vn.edu.nlu.fit.elearning.feature.access_token.dao.AccessTokenDao;
import vn.edu.nlu.fit.elearning.feature.access_token.model.AccessToken;

import java.sql.Timestamp;
import java.time.LocalDateTime;

public class AccessTokenServiceImpl implements AccessTokenService {
    private final AccessTokenDao accessTokenDao;

    public AccessTokenServiceImpl(AccessTokenDao accessTokenDao) {
        this.accessTokenDao = accessTokenDao;
    }


    @Override
    public int createToken(AccessToken token) {
        return accessTokenDao.create(token);
    }


    @Override
    public Timestamp expireDateTime() {
        return Timestamp.valueOf(LocalDateTime.now().plusMinutes(MailService.LIMIT_MINUTE));
    }

    @Override
    public boolean isExpireTime(Timestamp expireTime) {
        if (expireTime == null) return false;
        return LocalDateTime.now().isAfter(expireTime.toLocalDateTime().plusMinutes(MailService.LIMIT_MINUTE));
    }


    @Override
    public boolean validateResetToken(int userId, String token) {
        AccessToken accessToken = accessTokenDao.findByUserIdAndToken(userId, token);
        if (accessToken == null) return false;
        if (accessToken.isUsed()) return false;
        if (isExpireTime(accessToken.getExpiriTime())) return false;
        return true;
    }

    // Dùng cho đăng ký (không cần userId)
    @Override
    public boolean validateSignupToken(String token) {
        AccessToken accessToken = accessTokenDao.findByToken(token);
        if (accessToken == null) return false;
        if (accessToken.isUsed()) return false;
        if (isExpireTime(accessToken.getExpiriTime())) return false;
        return true;
    }

    @Override
    public void markAsUsed(String token) {
        accessTokenDao.markAsUsed(token);
    }
}