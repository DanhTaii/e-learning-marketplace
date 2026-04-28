package vn.edu.nlu.fit.elearning.feature.wishlist.dao;

import vn.edu.nlu.fit.elearning.feature.course.student.dto.CourseCardDto;

import java.util.List;

public interface WishlistDao {
    int delete(int userId, int courseId);

    // Thêm vào wishlist
    int addWishlist(int userId, int courseId);

    // Kiểm tra tồn tại
    boolean exists(int userId, int courseId);

    // Lấy danh sách course trong wishlist
    List<CourseCardDto> findWishlistCoursesByUser(int userId);
}
