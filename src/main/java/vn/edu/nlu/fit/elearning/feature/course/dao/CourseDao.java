package vn.edu.nlu.fit.elearning.feature.course.dao;

import vn.edu.nlu.fit.elearning.feature.course_user.dto.CourseCardDto;
import vn.edu.nlu.fit.elearning.feature.course_user.dto.CourseDetailDto;
import vn.edu.nlu.fit.elearning.feature.course.model.Course;
import vn.edu.nlu.fit.elearning.common.helper.pagination.filter.course.CourseFilter;

import java.util.List;

public interface CourseDao {
    int create(Course entity);

    Course findById(Integer integer);

    List<Course> findAll();

    int update(Course entity);

    int delete(Integer integer);

    List<Course> findAllCourses();

    CourseDetailDto findCourseByIdForDetail(int id, int userId);

    CourseCardDto findCourseCardById(int id, int userId);

    List<Course> filterAllCourses(CourseFilter filter, int limit, int offset);

    int countAdminAllCourses(CourseFilter filter);

    List<CourseCardDto> findCourseSuggestByTitle(String keyword);

    int countAllCourses();
}
