package vn.edu.nlu.fit.elearning.services;

import vn.edu.nlu.fit.elearning.dao.WishlistDao;
import vn.edu.nlu.fit.elearning.model.Course;
import vn.edu.nlu.fit.elearning.model.Wishlist;

import java.util.List;

public class WishlistService {

    private WishlistDao wd;

    public WishlistService() {
        this.wd = new WishlistDao();
    }

    public int createWishlist(Wishlist wishlist) {
        // TODO: Implement creation logic
        return 0;
    }

    public List<Wishlist> getAllWishlists() {
        // TODO: Implement getAll logic
        return wd.findAll();
    }

    public Wishlist getWishlistById(int id) {
        // TODO: Implement getById logic
        return null;
    }

    public void updateWishlist(Wishlist wishlist) {
        // TODO: Implement update logic
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