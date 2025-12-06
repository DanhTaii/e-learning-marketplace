package vn.edu.nlu.fit.elearning.services;

import vn.edu.nlu.fit.elearning.dao.UserDao;
import vn.edu.nlu.fit.elearning.model.User;

public class UserService {
    private UserDao userDao;

    public UserService() {
        this.userDao = new UserDao();
    }

    public boolean login(String email, String password) {
        email = email.trim();
        password = password.trim();
        User user = userDao.findUserByEmail(email);
        if (user == null) {
            System.out.println("Account hasn't exist !");
            return false;
        }
        if (email.isEmpty() || password.isEmpty()) {
            System.out.println("Invalid username or password");
            return false;
        }
        if (email.equals(user.getEmail()) && password.equals(user.getPassword())) {
            return true;
        }
        return false;
    }
}
