package vn.edu.nlu.fit.elearning.feature.auth.service;

import vn.edu.nlu.fit.elearning.feature.auth.dto.LoginRequestDto;
import vn.edu.nlu.fit.elearning.feature.user.dto.response.UserShortResponse;
import vn.edu.nlu.fit.elearning.feature.user.model.User;
import vn.edu.nlu.fit.elearning.feature.google.model.GoogleUser;

public interface AuthService {
    UserShortResponse login(LoginRequestDto loginRequestDto);

    User processSocialLogin(GoogleUser googleUser);

    boolean register(String email, String username, String password);

    boolean resetUserPassword(String oldPassword, String newPassword, String retypeNewPassword, String userMail);

    boolean changePassword(String newPassword, String retypeNewPassword, String userMail);

}
