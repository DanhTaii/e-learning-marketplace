package vn.edu.nlu.fit.elearning.feature.course_tag.service;

import vn.edu.nlu.fit.elearning.feature.course_tag.dao.CourseTagDao;
import vn.edu.nlu.fit.elearning.feature.course_tag.dao.CourseTagDaoImpl;
import vn.edu.nlu.fit.elearning.feature.course_tag.model.CourseTag;

import java.util.List;

public class CourseTagServiceImpl implements CourseTagService {

    private CourseTagDao ctd;

    public CourseTagServiceImpl(CourseTagDao courseTagDao) {
        this.ctd = courseTagDao;
    }

    @Override
    public int createCourseTag(int courseId, String[] tagIds) {
        if (tagIds == null || tagIds.length == 0) {
            return 1; // không chọn tag vẫn OK
        }

        int total = 0;
        for (String tagId : tagIds) {
            total += this.ctd.create(courseId, Integer.parseInt(tagId));
        }
        return total;
    }

    @Override
    public List<CourseTag> getAllCourseTags() {
        // TODO: Implement getAll logic
        return ctd.findAll();
    }

    @Override
    public CourseTag getCourseTagById(int id) {
        // TODO: Implement getById logic
        return null;
    }

    @Override
    public void updateCourseTag(CourseTag courseTag) {
        // TODO: Implement update logic
    }

    @Override
    public int deleteCourseTag(int id) {
        return ctd.delete(id);
    }

    @Override
    public List<Integer> getAllTagIdByCourseId(int courseId) {
        return ctd.findTagIdByCourseId(courseId);
    }
}