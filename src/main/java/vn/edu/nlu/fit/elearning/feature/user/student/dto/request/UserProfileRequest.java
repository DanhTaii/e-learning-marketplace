package vn.edu.nlu.fit.elearning.feature.user.student.dto.request;

import vn.edu.nlu.fit.elearning.common.helper.enums.BaseStatus;
import vn.edu.nlu.fit.elearning.common.helper.enums.Role;

public class UserProfileRequest {
    private String username;
    private String email;
    private String firstName;
    private String lastName;
    private String phone;
    private BaseStatus status;
    private String avatarUrl;

    public UserProfileRequest() {
    }

    public UserProfileRequest(String username, String email, String phone, String firstName, String lastName, BaseStatus status, String avatarUrl) {
        this.username = username;
        this.email = email;
        this.phone = phone;
        this.status = status;
        this.avatarUrl = avatarUrl;
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

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public BaseStatus getStatus() {
        return status;
    }

    public void setStatus(BaseStatus status) {
        this.status = status;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }
}
