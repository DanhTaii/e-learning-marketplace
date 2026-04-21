package vn.edu.nlu.fit.elearning.feature.course.service;

import vn.edu.nlu.fit.elearning.feature.course_user.dto.CourseCardDto;
import vn.edu.nlu.fit.elearning.feature.course_user.dto.CourseDetailDto;
import vn.edu.nlu.fit.elearning.feature.course.model.Course;
import vn.edu.nlu.fit.elearning.common.helper.pagination.filter.course.CourseFilter;

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

    List<Course> getAllCourses(CourseFilter filter);

    int countAllCourseAdmin(CourseFilter filter);

    List<CourseCardDto> getCourseSuggestByTitle(String keyword);

    int getTotalCourses();
}
