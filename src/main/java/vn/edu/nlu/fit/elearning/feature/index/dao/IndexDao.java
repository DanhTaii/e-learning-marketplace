package vn.edu.nlu.fit.elearning.feature.index.dao;

import vn.edu.nlu.fit.elearning.feature.course_user.dto.CourseCardDto;

import java.util.List;

public interface IndexDao {

    // 3 khóa học được yêu thích nhiều nhất
    List<CourseCardDto> findThreeCoursesWereLiked(Integer userId);

    // 6 khóa học mới nhất
    List<CourseCardDto> findSixCoursesLast(Integer userId);

    // 6 khóa học phổ biến nhất
    List<CourseCardDto> findSixCoursesMostPopular(Integer userId);

    // 1 khóa học phổ biến nhất
    CourseCardDto findCourseMostPopular(Integer userId);

}
