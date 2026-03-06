package vn.edu.nlu.fit.elearning.feature.wishlist.service;

import vn.edu.nlu.fit.elearning.feature.course.dao.WishlistDao;
import vn.edu.nlu.fit.elearning.feature.course.dto.CourseCardDto;

import java.util.List;

public class WishlistService {

    private WishlistDao wd;

    public WishlistService() {
        this.wd = new WishlistDao();
    }

    public void deleteWishlist(int id) {
        // TODO: Implement delete logic
    }

    public int addCourseToWishlist(int userId, int courseId) {
        return wd.addWishlist(userId, courseId);
    }

    public int removeCourseFromWishlist(int userId, int courseId) {
        return wd.delete(userId, courseId);
    }

    public List<CourseCardDto> getWishlistCourses(int userId) {
        return wd.findWishlistCoursesByUser(userId);
//        return null;
    }

    public boolean exists(int userId, int courseId) {
        return wd.exists(userId, courseId);
    }

    public boolean toggleWishlist(int userId, int courseId) {
        if (wd.exists(userId, courseId)) {
            wd.delete(userId, courseId);
            return false;
        } else {
            wd.addWishlist(userId, courseId);
            return true;
        }
    }
}