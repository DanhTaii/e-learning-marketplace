package vn.edu.nlu.fit.elearning.feature.course.admin.service;

import org.apache.poi.ss.usermodel.*;
import vn.edu.nlu.fit.elearning.common.helper.excel.CourseExcelParser;
import vn.edu.nlu.fit.elearning.common.helper.pagination.filter.course.CourseArchivedFilter;
import vn.edu.nlu.fit.elearning.common.utils.excel.ExcelReaderUtils;
import vn.edu.nlu.fit.elearning.feature.course.admin.dao.CourseAdminDao;
import vn.edu.nlu.fit.elearning.feature.course.admin.dto.CourseAdminDto;
import vn.edu.nlu.fit.elearning.feature.course.admin.dto.CourseArchive;
import vn.edu.nlu.fit.elearning.feature.course.student.dto.CourseDetailDto;
import vn.edu.nlu.fit.elearning.feature.course.common.model.Course;
import vn.edu.nlu.fit.elearning.common.helper.pagination.filter.course.CourseFilter;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class CourseAdminServiceImpl implements CourseAdminService {
    private final CourseAdminDao cd;

    public CourseAdminServiceImpl(CourseAdminDao courseAdminDao) {
        this.cd = courseAdminDao;
    }

    @Override
    public int createCourse(Course course) {
        return cd.create(course);
    }

    @Override
    public Course getCourseById(int id) {
        return cd.findById(id);
    }

    @Override
    public int updateCourse(Course entity) {
        return this.cd.update(entity);
    }

    @Override
    public int deleteCourseById(int id) {
        return cd.deleteById(id);
    }

    @Override
    public List<Course> getAllCourses() {
        return cd.findAll();
    }

    @Override
    public CourseDetailDto getCourseDetail(int id, int userId) {
        return cd.findCourseByIdForDetail(id, userId);
    }

    @Override
    public List<CourseAdminDto> getCourses(CourseFilter filter) {
        return cd.findByFilter(filter);
    }

    @Override
    public int countCourses(CourseFilter filter) {
        return cd.countByFilter(filter);
    }

    @Override
    public int getTotalCourses() {
        return cd.countAll();
    }

    @Override
    public int deleteCoursesByIds(List<Integer> ids) {
        return cd.deleteByIds(ids);
    }

    @Override
    public int duplicateCoursesByIds(List<Integer> ids) {
        int count = 0;
        int result = 0;

        for (int id : ids) {
            Course odinary = this.getCourseById(id);

            if (odinary != null) {
                Course clone = new Course();
                clone.setTitle("Bản sao của " + odinary.getTitle());
                clone.setSubtitle(odinary.getSubtitle());
                clone.setDescription(odinary.getDescription());
                clone.setGoals(odinary.getGoals());

                clone.setCategoryId(odinary.getCategoryId());

                clone.setDiscountPrice(odinary.getDiscountPrice());
                clone.setPrice(odinary.getPrice());

                clone.setIsPublic(false);
                clone.setLevel(odinary.getLevel());
                clone.setThumbnailUrl(odinary.getThumbnailUrl());

                result = this.createCourse(clone);
                if (result > 0) {
                    count++;
                }
            }
        }
        return count;
    }

    @Override
    public int updateCoursesStatusByIds(List<Integer> ids) {
        return cd.updateStatusByIds(ids);
    }

    @Override
    public int archiveCourseById(int id, String deleteReason) {
        return archiveCoursesByIds(List.of(id), deleteReason);
    }

    @Override
    public int archiveCoursesByIds(List<Integer> ids, String deleteReason) {
        return cd.archiveByIds(ids, deleteReason);
    }

    @Override
    public int restoreCourseById(int id) {
        return this.restoreCoursesByIds(List.of(id));
    }

    @Override
    public int restoreCoursesByIds(List<Integer> ids) {
        return cd.restoreByIds(ids);
    }

    @Override
    public int getTotalArchivedCourses() {
        return cd.countArchived();
    }

    @Override
    public List<CourseArchive> getArchivedCourses(CourseArchivedFilter filter) {
        return cd.findArchivedByFilter(filter);
    }

    @Override
    public int countArchivedCourses(CourseArchivedFilter filter) {
        return cd.countArchivedByFilter(filter);
    }

    @Override
    public int countCoursesByTimeRange(String timeRange) {
        return cd.countCoursesByTimeRange(timeRange);
    }

    @Override
    public int createListCourses(List<Course> courses) {
        return cd.createList(courses);
    }

    public List<Course> importCoursesFromExcel(InputStream inputStream, List<String> errorMessages) throws IOException, Exception {
        return ExcelReaderUtils.readExcel(inputStream, CourseExcelParser::parseRowToCourse, errorMessages);
    }

}
