package vn.edu.nlu.fit.elearning.feature.course.admin.service;

import vn.edu.nlu.fit.elearning.common.helper.pagination.filter.course.CourseArchivedFilter;
import vn.edu.nlu.fit.elearning.common.helper.pagination.filter.lesson.LessonArchiveFilter;
import vn.edu.nlu.fit.elearning.feature.course.admin.dto.CourseAdminDto;
import vn.edu.nlu.fit.elearning.feature.course.admin.dto.CourseArchive;
import vn.edu.nlu.fit.elearning.feature.course.student.dto.CourseCardDto;
import vn.edu.nlu.fit.elearning.feature.course.student.dto.CourseDetailDto;
import vn.edu.nlu.fit.elearning.feature.course.common.model.Course;
import vn.edu.nlu.fit.elearning.common.helper.pagination.filter.course.CourseFilter;
import vn.edu.nlu.fit.elearning.feature.lesson.dto.LessonArchive;

import java.util.List;

public interface CourseAdminService {
    int createCourse(Course course);

    Course getCourseById(int id);

    int updateCourse(Course entity);

    int deleteCourseById(int id);

    List<Course> getAllCourses();

    CourseDetailDto getCourseDetail(int id, int userId);

    List<CourseAdminDto> getCourses(CourseFilter filter);

    int countCourses(CourseFilter filter);

    int getTotalCourses();

    //  =========================== BULK FUNCTION ==========================

    int deleteCoursesByIds(List<Integer> ids);

    int duplicateCoursesByIds(List<Integer> ids);

    int updateCoursesStatusByIds(List<Integer> ids);

    //  =========================== ARCHIVE FUNCTION ==========================
    int archiveCourseById(int id, String deleteReason);

    int archiveCoursesByIds(List<Integer> ids, String deleteReason);

    int restoreCourseById(int ids);

    int restoreCoursesByIds(List<Integer> ids);

    int getTotalArchivedCourses();

    List<CourseArchive> getArchivedCourses(CourseArchivedFilter filter);

    int countArchivedCourses(CourseArchivedFilter filter);


}
