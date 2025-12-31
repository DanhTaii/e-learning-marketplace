package vn.edu.nlu.fit.elearning.services;

import vn.edu.nlu.fit.elearning.dao.WishlistDao;
import vn.edu.nlu.fit.elearning.model.Course;

import java.util.List;

public class WishlistService {

    private WishlistDao wd;

    public WishlistService() {
        this.wd = new WishlistDao();
    }

    public void deleteWishlist(int id) {
        // TODO: Implement delete logic
    }

    public boolean addCourseToWishlist(int userId, int courseId) {
        if (wd.exists(userId, courseId)) return false;
        return wd.addWishlist(userId, courseId);
    }

    public int removeCourseFromWishlist(int id) {
        return wd.delete(id);
    }

    public List<Course> getWishlistCourses(int userId) {
        return wd.findWishlistCoursesByUser(userId);
    }

    public boolean exists(int userId, int courseId) {
        return wd.exists(userId, courseId);
    }

}