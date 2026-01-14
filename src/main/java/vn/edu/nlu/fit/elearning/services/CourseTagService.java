package vn.edu.nlu.fit.elearning.services;

import vn.edu.nlu.fit.elearning.dao.CourseTagDao;
import vn.edu.nlu.fit.elearning.model.CourseTag;

import java.util.List;

public class CourseTagService {

    private CourseTagDao ctd;

    public CourseTagService() {
        this.ctd = new CourseTagDao();
    }

    public int createCourseTag(CourseTag courseTag) {
        // TODO: Implement creation logic
        return 0;
    }

    public List<CourseTag> getAllCourseTags() {
        // TODO: Implement getAll logic
        return ctd.findAll();
    }

    public CourseTag getCourseTagById(int id) {
        // TODO: Implement getById logic
        return null;
    }

    public void updateCourseTag(CourseTag courseTag) {
        // TODO: Implement update logic
    }

    public void deleteCourseTag(int id) {
        // TODO: Implement delete logic
    }

    public List<Integer> getAllTagIdByCourseId(int courseId) {
        return ctd.findTagIdByCourseId(courseId);
    }
}