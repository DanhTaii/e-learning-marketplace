package vn.edu.nlu.fit.elearning.feature.wishlist.service;

import vn.edu.nlu.fit.elearning.feature.course.student.dto.CourseCardDto;

import java.util.List;

public interface WishlistService {
    void deleteWishlist(int id);

    int addCourseToWishlist(int userId, int courseId);

    int removeCourseFromWishlist(int userId, int courseId);

    List<CourseCardDto> getWishlistCourses(int userId);

    boolean exists(int userId, int courseId);

    boolean toggleWishlist(int userId, int courseId);
}
