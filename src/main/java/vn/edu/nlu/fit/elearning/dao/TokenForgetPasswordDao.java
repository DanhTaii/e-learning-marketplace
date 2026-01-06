package vn.edu.nlu.fit.elearning.dao;

import vn.edu.nlu.fit.elearning.model.TokenForgetPassword;

public class TokenForgetPasswordDao extends BaseDao  {

    public boolean createToken(TokenForgetPassword tokenForgetPassword) {
        String sql = """
        INSERT INTO token_forget_password (user_id, token, expiry_time, is_used)
        VALUES (:userId, :token, :expiryTime, :isUsed);
    """;
        return getJdbi().withHandle(handle ->
                handle.createUpdate(sql)
                        .bind("userId", tokenForgetPassword.getUserId())
                        .bind("token", tokenForgetPassword.getToken())
                        .bind("expiryTime", tokenForgetPassword.getExpiriTime())
                        .bind("isUsed", tokenForgetPassword.isUsed())
                        .execute() > 0
        );
    }


}