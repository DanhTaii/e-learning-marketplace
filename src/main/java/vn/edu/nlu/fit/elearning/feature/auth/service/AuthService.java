package vn.edu.nlu.fit.elearning.feature.auth.service;

import vn.edu.nlu.fit.elearning.feature.auth.dto.LoginRequestDto;
import vn.edu.nlu.fit.elearning.feature.facebook.model.FacebookUser;
import vn.edu.nlu.fit.elearning.feature.user.student.dto.response.UserShortResponse;
import vn.edu.nlu.fit.elearning.feature.user.common.model.User;
import vn.edu.nlu.fit.elearning.feature.google.model.GoogleUser;

import java.util.Set;

public interface AuthService {
    UserShortResponse login(LoginRequestDto loginRequestDto);

    User processSocialLogin(GoogleUser googleUser);

    boolean register(String email, String username, String password);

    boolean resetUserPassword(String oldPassword, String newPassword, String retypeNewPassword, String userMail);

    boolean changePassword(String newPassword, String retypeNewPassword, String userMail);

    Set<String> getUserPermissions(Integer userId);

    Set<String> getUserRoles(Integer userId);

    User processFacebookLogin(FacebookUser facebookUser);

}
