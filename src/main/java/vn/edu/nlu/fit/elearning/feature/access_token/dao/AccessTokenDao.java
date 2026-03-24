package vn.edu.nlu.fit.elearning.feature.access_token.dao;

import vn.edu.nlu.fit.elearning.feature.access_token.model.AccessToken;

public interface AccessTokenDao {
    AccessToken findByToken(String token);

    int create(AccessToken accessToken);

    AccessToken findByUserIdAndToken(int userId, String token);

    // Đánh dấu token đã sử dụng
    boolean markAsUsed(String token);
}
