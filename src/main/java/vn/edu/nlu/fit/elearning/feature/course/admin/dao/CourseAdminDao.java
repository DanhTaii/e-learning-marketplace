package vn.edu.nlu.fit.elearning.feature.course.admin.dao;

import vn.edu.nlu.fit.elearning.common.helper.pagination.filter.course.CourseArchivedFilter;
import vn.edu.nlu.fit.elearning.feature.course.student.dto.CourseCardDto;
import vn.edu.nlu.fit.elearning.feature.course.student.dto.CourseDetailDto;
import vn.edu.nlu.fit.elearning.feature.course.common.model.Course;
import vn.edu.nlu.fit.elearning.common.helper.pagination.filter.course.CourseFilter;
import vn.edu.nlu.fit.elearning.feature.lesson.dto.LessonArchive;

import java.util.List;

public interface CourseAdminDao {
    int create(Course entity);

    Course findById(Integer integer);

    List<Course> findAll();

    int update(Course entity);

    int deleteById(Integer integer);

    int deleteByIds(List<Integer> ids);

    int updateStatusByIds(List<Integer> ids);

    CourseDetailDto findCourseByIdForDetail(int id, int userId);

    int countAll();

    List<Course> findByFilter(CourseFilter filter);

    int countByFilter(CourseFilter filter);

    //    =================== ARCHIVE FUNCTION ========================
    int archiveByIds(List<Integer> ids, String deleteReason);

    int restoreByIds(List<Integer> ids);

    int countArchived();

    List<LessonArchive> findArchivedByFilter(CourseArchivedFilter filter);

    int countArchivedByFilter(CourseArchivedFilter filter);

}
