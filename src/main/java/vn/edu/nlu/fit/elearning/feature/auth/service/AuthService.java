package vn.edu.nlu.fit.elearning.feature.auth.service;

import vn.edu.nlu.fit.elearning.feature.user.model.User;
import vn.edu.nlu.fit.elearning.utils.objects.GoogleUser;

public interface AuthService {
    User login(String email, String password);

    User processSocialLogin(GoogleUser googleUser);

    boolean register(String email, String username, String password);

    boolean resetUserPassword(String oldPassword, String newPassword, String retypeNewPassword, String userMail);

    boolean changePassword(String newPassword, String retypeNewPassword, String userMail);

    void validatePassword(String password);
}
