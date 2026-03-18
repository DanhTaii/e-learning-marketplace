package vn.edu.nlu.fit.elearning.feature.auth.dto;

import vn.edu.nlu.fit.elearning.common.helper.enums.BasicStatus;
import vn.edu.nlu.fit.elearning.common.helper.enums.Role;

public class LoginRequestDto {
    private String email;
    private String password;

    public LoginRequestDto() {
    }

    public LoginRequestDto(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
