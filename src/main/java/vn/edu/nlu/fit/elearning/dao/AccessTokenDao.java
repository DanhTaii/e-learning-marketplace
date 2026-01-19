package vn.edu.nlu.fit.elearning.dao;

import vn.edu.nlu.fit.elearning.model.AccessToken;

public class AccessTokenDao extends BaseDao {

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

    public boolean createToken(AccessToken accessToken) {
        String sql = """
            INSERT INTO token_forget_password (user_id, token, expiry_time, is_used)
            VALUES (:userId, :token, :expiryTime, :isUsed)
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

    public AccessToken findByUserIdAndToken(int userId, String token) {
        String sql = """
            SELECT id, user_id, token, expiry_time, is_used
            FROM token_forget_password
            WHERE user_id = :userId AND token = :token
        """;
        return getJdbi().withHandle(handle ->
                handle.createQuery(sql)
                        .bind("userId", userId)
                        .bind("token", token)
                        .mapToBean(AccessToken.class)
                        .findOne()
                        .orElse(null)
        );
    }

    // Đánh dấu token đã sử dụng
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