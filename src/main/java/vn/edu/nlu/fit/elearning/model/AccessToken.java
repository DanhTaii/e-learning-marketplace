package vn.edu.nlu.fit.elearning.model;

import java.sql.Timestamp;

public class AccessToken {
    private int id;
    private int userId;
    private String token;
    private Timestamp expiriTime;
    private boolean isUsed;

    public AccessToken() {
    }

    public AccessToken(int id, int userId, String token, Timestamp expiriTime, boolean isUsed) {
        this.id = id;
        this.userId = userId;
        this.token = token;
        this.expiriTime = expiriTime;
        this.isUsed = isUsed;
    }
    public AccessToken(int userId, String token, Timestamp expiriTime, boolean isUsed) {
        this.userId = userId;
        this.token = token;
        this.expiriTime = expiriTime;
        this.isUsed = isUsed;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Timestamp getExpiriTime() {
        return expiriTime;
    }

    public void setExpiriTime(Timestamp expiriTime) {
        this.expiriTime = expiriTime;
    }

    public boolean isUsed() {
        return isUsed;
    }

    public void setUsed(boolean used) {
        isUsed = used;
    }
}
