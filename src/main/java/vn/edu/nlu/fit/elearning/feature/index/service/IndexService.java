package vn.edu.nlu.fit.elearning.feature.index.service;

import vn.edu.nlu.fit.elearning.feature.course.dto.CourseCardDto;

import java.util.List;

public interface IndexService {
    List<CourseCardDto> getThreeCoursesWereLiked(Integer userId);

    List<CourseCardDto> getSixCoursesMostPopular(Integer userId);

    CourseCardDto getCoursesMostPopular(Integer userId);

    List<CourseCardDto> getSixCoursesLast(Integer userId);

}
