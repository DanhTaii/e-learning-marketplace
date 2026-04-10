package vn.edu.nlu.fit.elearning.feature.course_user.dao;

import vn.edu.nlu.fit.elearning.common.helper.pagination.filter.course.AllCourseFilter;
import vn.edu.nlu.fit.elearning.feature.course_user.dto.CourseCardDto;

import java.util.List;

public interface CourseSearchDao {
    // làm cho phần bộ lọc
    // làm cách này thì tích 1 hay nhiều cái thì vẫn đều lọc bình thường
    List<CourseCardDto> filterResultSearchWithPagination(
            Integer categoryId, Integer tagId, String title,
            String sortPrice, String level,
            String priceRange, String rating,
            String duration, String popular,
            int limit, int offset, int userId);

    List<CourseCardDto> filterAllCoursesWithPagination(AllCourseFilter allCourseFilter);

    // Đếm tổng số sau lọc
    int countFilteredCourses(
            Integer categoryId, Integer tagId, String title,
            String sortPrice, String level,
            String priceRange, String rating,
            String duration, String popular);

}
