package vn.edu.nlu.fit.elearning.feature.course_user.service;

import vn.edu.nlu.fit.elearning.common.utils.search.AllCourseFilter;
import vn.edu.nlu.fit.elearning.feature.course_user.dto.CourseCardDto;

import java.util.List;

public interface CourseSearchService {
//    // tổng quát nhất
//    List<CourseCardDto> filterCoursesForResultSearch(
//            Integer categoryId, Integer tagId, String title,
//            String sortPrice, String level, String priceRange,
//            String rating, String duration, String popular,
//            int limit, int offset, int userId);

    // Filter theo category + phân trang
    List<CourseCardDto> filterCoursesByCategoryWithPagination(
            int idCategory,
            String sortPrice,
            String level,
            String priceRange,
            String rating,
            String duration,
            String popular,
            int page,
            int pageSize, int userId);

    //    // Đếm tổng số khóa học sau lọc theo category
    int countFilteredCoursesByCategory(
            int idCategory,
            String sortPrice,
            String level,
            String priceRange,
            String rating,
            String duration,
            String popular);

    // Tương tự cho search theo title (nếu bạn có controller by-title)
    List<CourseCardDto> filterCoursesByTitleWithPagination(
            String search,
            String sortPrice,
            String level,
            String priceRange,
            String rating,
            String duration,
            String popular,
            int page,
            int pageSize,
            int userId);

    int countFilteredCoursesByTitle(
            String search,
            String sortPrice,
            String level,
            String priceRange,
            String rating,
            String duration,
            String popular);

    // Tương tự cho tag (nếu cần)
    List<CourseCardDto> filterCoursesByTagWithPagination(
            int idTag,
            String sortPrice,
            String level,
            String priceRange,
            String rating,
            String duration,
            String popular,
            int page,
            int pageSize, int userId);

    int countFilteredCoursesByTag(
            int idTag,
            String sortPrice,
            String level,
            String priceRange,
            String rating,
            String duration,
            String popular);

    // tổng quát nhất
    List<CourseCardDto> filterCoursesForAllCourses(AllCourseFilter allCourseFilter);

    //    // đếm tổng quát (dùng để tính totalPages)
    int countFilteredCourses(
            Integer categoryId, Integer tagId, String title,
            String sortPrice, String level, String priceRange,
            String rating, String duration, String popular);
}
