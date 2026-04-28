package vn.edu.nlu.fit.elearning.feature.wishlist.service;

import vn.edu.nlu.fit.elearning.feature.wishlist.dao.WishlistDao;
import vn.edu.nlu.fit.elearning.feature.course.student.dto.CourseCardDto;

import java.util.List;

public class WishlistServiceImpl implements WishlistService {

    private WishlistDao wd;

    public WishlistServiceImpl(WishlistDao wishlistDao) {
        this.wd = wishlistDao;
    }

    @Override
    public void deleteWishlist(int id) {
        // TODO: Implement delete logic
    }

    @Override
    public int addCourseToWishlist(int userId, int courseId) {
        return wd.addWishlist(userId, courseId);
    }

    @Override
    public int removeCourseFromWishlist(int userId, int courseId) {
        return wd.delete(userId, courseId);
    }

    @Override
    public List<CourseCardDto> getWishlistCourses(int userId) {
        return wd.findWishlistCoursesByUser(userId);
//        return null;
    }

    @Override
    public boolean exists(int userId, int courseId) {
        return wd.exists(userId, courseId);
    }

    @Override
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