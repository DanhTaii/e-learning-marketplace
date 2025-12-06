package vn.edu.nlu.fit.elearning.services;

import vn.edu.nlu.fit.elearning.dao.UserDao;
import vn.edu.nlu.fit.elearning.model.User;

import java.util.List;

public class UserService {
    private UserDao userDao;

    public UserService() {
        this.userDao = new UserDao();
    }

    public User login(String email, String password) {
        email = email.trim();
        password = password.trim();
        if (email.isEmpty() || password.isEmpty()) {
            System.out.println("Invalid username or password");
            return null;
        }

        User user = userDao.findUserByEmail(email);
        if (user == null) {
            System.out.println("Account hasn't exist !");
            return null;
        }

        if (email.equals(user.getEmail()) && password.equals(user.getPassword())) {
            return user;
        }
        return null;
    }

    public List<User> getAllUsers() {
        return userDao.findAll();
    }

    public User getUserById(int id) {
        // Gọi DAO (CRUD: READ ONE)

        return null;
    }
}
