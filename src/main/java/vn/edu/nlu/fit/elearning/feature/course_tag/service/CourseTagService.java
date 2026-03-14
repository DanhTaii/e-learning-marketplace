package vn.edu.nlu.fit.elearning.feature.course_tag.service;

import vn.edu.nlu.fit.elearning.feature.course_tag.model.CourseTag;

import java.util.List;

public interface CourseTagService {
    int createCourseTag(int courseId, String[] tagIds);

    List<CourseTag> getAllCourseTags();

    CourseTag getCourseTagById(int id);

    void updateCourseTag(CourseTag courseTag);

    int deleteCourseTag(int id);

    List<Integer> getAllTagIdByCourseId(int courseId);
}
