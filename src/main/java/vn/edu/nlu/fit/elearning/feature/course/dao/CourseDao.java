package vn.edu.nlu.fit.elearning.feature.course.dao;

import vn.edu.nlu.fit.elearning.feature.course.dto.CourseCardDto;
import vn.edu.nlu.fit.elearning.feature.course.dto.CourseDetailDto;
import vn.edu.nlu.fit.elearning.feature.course.model.Course;
import vn.edu.nlu.fit.elearning.utils.objects.CourseFilter;

import java.util.List;

public interface CourseDao {
    int create(Course entity);

    Course findById(Integer integer);

    List<Course> findAll();

    int update(Course entity);

    int delete(Integer integer);

    List<Course> findAllCourses();

    // 3 khóa học được yêu thích nhiều nhất
    List<CourseCardDto> findThreeCoursesWereLiked(Integer userId);

    // 6 khóa học mới nhất
    List<CourseCardDto> findSixCoursesLast(Integer userId);

    // 6 khóa học phổ biến nhất
    List<CourseCardDto> findSixCoursesMostPopular(Integer userId);

    // 1 khóa học phổ biến nhất
    CourseCardDto findCourseMostPopular(Integer userId);

    CourseDetailDto findCourseByIdForDetail(int id, int userId);

    CourseCardDto findCourseCardById(int id, int userId);

    // làm cho phần bộ lọc
    // làm cách này thì tích 1 hay nhiều cái thì vẫn đều lọc bình thường
    List<CourseCardDto> filterResultSearchWithPagination(
            Integer categoryId, Integer tagId, String title,
            String sortPrice, String level,
            String priceRange, String rating,
            String duration, String popular,
            int limit, int offset, int userId);

    List<CourseCardDto> filterAllCoursesWithPagination(
            Integer categoryId, String sortPrice, boolean popular, boolean newest,
            int limit, int offset, int userId);

    // Đếm tổng số sau lọc
    int countFilteredCourses(
            Integer categoryId, Integer tagId, String title,
            String sortPrice, String level,
            String priceRange, String rating,
            String duration, String popular);

    List<Course> filterAllCourses(CourseFilter filter, int limit, int offset);

    int countAdminAllCourses(CourseFilter filter);

    List<CourseCardDto> findCourseSuggestByTitle(String keyword);
}
