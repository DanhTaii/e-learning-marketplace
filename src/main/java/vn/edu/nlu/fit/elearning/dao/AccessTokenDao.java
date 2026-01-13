package vn.edu.nlu.fit.elearning.dao;

import vn.edu.nlu.fit.elearning.model.AccessToken;

public class AccessTokenDao extends BaseDao  {

    public boolean createToken(AccessToken accessToken) {
        String sql = """
            INSERT INTO token_forget_password (user_id, token, expiry_time, is_used)
            VALUES (:userId, :token, :expiryTime, :isUsed);
        """;
        return getJdbi().withHandle(handle ->
                handle.createUpdate(sql)
                        .bind("userId", accessToken.getUserId())
                        .bind("token", accessToken.getToken())
                        .bind("expiryTime", accessToken.getExpiriTime())
                        .bind("isUsed", accessToken.isUsed())
                        .execute() > 0
        );
    }

    // tìm token trong DB
    public AccessToken findByToken(String token) {
        String sql = """
            SELECT id, user_id, token, expiry_time, is_used
            FROM token_forget_password
            WHERE token = :token
        """;
        return getJdbi().withHandle(handle ->
                handle.createQuery(sql)
                        .bind("token", token)
                        .mapToBean(AccessToken.class)
                        .findOne()
                        .orElse(null)
        );
    }

    // đánh dấu token đã dùng
    public boolean markAsUsed(String token) {
        String sql = """
            UPDATE token_forget_password
            SET is_used = true
            WHERE token = :token
        """;
        return getJdbi().withHandle(handle ->
                handle.createUpdate(sql)
                        .bind("token", token)
                        .execute() > 0
        );
    }
}
