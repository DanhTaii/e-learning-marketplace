package vn.edu.nlu.fit.elearning.feature.course.service;

import vn.edu.nlu.fit.elearning.feature.course.dto.CourseCardDto;
import vn.edu.nlu.fit.elearning.feature.course.dto.CourseDetailDto;
import vn.edu.nlu.fit.elearning.feature.course.model.Course;
import vn.edu.nlu.fit.elearning.common.utils.objects.CourseFilter;

import java.util.List;

public interface CourseService {
    int createCourse(Course course);

    Course getCourseById(int id);

    int updateCourse(Course entity);

    int deleteCourse(int id);

    List<Course> getAllCourses();

    double avgRating();

    CourseDetailDto getCourse(int id, int userId);

    CourseCardDto getCourseCardById(int id, int userId);

    List<Course> getAllCourses(CourseFilter filter, int pageSize, int offset);

    int countAllCourseAdmin(CourseFilter filter);

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
    List<CourseCardDto> filterCoursesForResultSearch(
            Integer categoryId, Integer tagId, String title,
            String sortPrice, String level, String priceRange,
            String rating, String duration, String popular,
            int limit, int offset, int userId);

    // tổng quát nhất
    List<CourseCardDto> filterCoursesForAllCourses(
            Integer categoryId,
            String sortPrice, boolean popular, boolean newest,
            int limit, int offset, int userId);

    //    // đếm tổng quát (dùng để tính totalPages)
    int countFilteredCourses(
            Integer categoryId, Integer tagId, String title,
            String sortPrice, String level, String priceRange,
            String rating, String duration, String popular);

    List<CourseCardDto> getCourseSuggestByTitle(String keyword);
}
