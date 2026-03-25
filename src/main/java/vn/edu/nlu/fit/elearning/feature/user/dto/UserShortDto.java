package vn.edu.nlu.fit.elearning.feature.user.dto;

import vn.edu.nlu.fit.elearning.common.helper.enums.BaseStatus;
import vn.edu.nlu.fit.elearning.common.helper.enums.Role;

public class UserShortDto {
    private int id;
    private String username;
    private String email;
    private String avatarUrl;
    private BaseStatus status;
    private Role role;

    public UserShortDto() {
    }

    public UserShortDto(int id, String username, String email, String avatarUrl, BaseStatus status, Role role) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.avatarUrl = avatarUrl;
        this.status = status;
        this.role = role;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }

    public BaseStatus getStatus() {
        return status;
    }

    public void setStatus(BaseStatus status) {
        this.status = status;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }
}
