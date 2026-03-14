package vn.edu.nlu.fit.elearning.feature.course_tag.dao;

import vn.edu.nlu.fit.elearning.feature.course_tag.model.CourseTag;

import java.util.List;

public interface CourseTagDao {
    int create(int courseId, int tagId);

    CourseTag findById(Integer id);

    List<CourseTag> findAll();

    int update(CourseTag entity);

    int delete(Integer id);

    List<Integer> findTagIdByCourseId(int courseId);
}
