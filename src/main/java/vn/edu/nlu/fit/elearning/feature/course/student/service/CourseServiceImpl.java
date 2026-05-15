package vn.edu.nlu.fit.elearning.feature.course.student.service;

import vn.edu.nlu.fit.elearning.common.helper.pagination.filter.course.AllCourseFilter;
import vn.edu.nlu.fit.elearning.feature.course.student.dao.CourseDao;
import vn.edu.nlu.fit.elearning.feature.course.student.dto.CourseCardDto;

import java.util.List;

public class CourseServiceImpl implements CourseService {
    private CourseDao courseDao;

    public CourseServiceImpl(CourseDao courseDao) {
        this.courseDao = courseDao;
    }

    // Filter theo category + phân trang
    @Override
    public List<CourseCardDto> filterCoursesByCategoryWithPagination(
            int idCategory,
            String sortPrice,
            String level,
            String priceRange,
            String rating,
            String duration,
            String popular,
            int page,
            int pageSize, int userId) {

        int offset = (page - 1) * pageSize;
        return courseDao.filterResultSearchWithPagination(
                idCategory,   // categoryId
                null,         // tagId
                null,         // title
                sortPrice,
                level,
                priceRange,
                rating,
                duration,
                popular,
                pageSize,     // limit
                offset,        // offsetus
                userId
        );
    }

    //    // Đếm tổng số khóa học sau lọc theo category
    @Override
    public int countFilteredCoursesByCategory(
            int idCategory,
            String sortPrice,
            String level,
            String priceRange,
            String rating,
            String duration,
            String popular) {

        return courseDao.countFilteredCourses(
                idCategory,
                null,
                null,
                sortPrice,
                level,
                priceRange,
                rating,
                duration,
                popular
        );
    }

    // Tương tự cho search theo title (nếu bạn có controller by-title)
    @Override
    public List<CourseCardDto> filterCoursesByTitleWithPagination(
            String search,
            String sortPrice,
            String level,
            String priceRange,
            String rating,
            String duration,
            String popular,
            int page,
            int pageSize,
            int userId) {

        int offset = (page - 1) * pageSize;
        return courseDao.filterResultSearchWithPagination(
                null,
                null,
                search,
                sortPrice,
                level,
                priceRange,
                rating,
                duration,
                popular,
                pageSize,
                offset,
                userId
        );
    }

    @Override
    public int countFilteredCoursesByTitle(
            String search,
            String sortPrice,
            String level,
            String priceRange,
            String rating,
            String duration,
            String popular) {

        return courseDao.countFilteredCourses(
                null,
                null,
                search,
                sortPrice,
                level,
                priceRange,
                rating,
                duration,
                popular
        );
    }

    // Tương tự cho tag (nếu cần)
    @Override
    public List<CourseCardDto> filterCoursesByTagWithPagination(
            int idTag,
            String sortPrice,
            String level,
            String priceRange,
            String rating,
            String duration,
            String popular,
            int page,
            int pageSize, int userId) {

        int offset = (page - 1) * pageSize;
        return courseDao.filterResultSearchWithPagination(
                null,
                idTag,
                null,
                sortPrice,
                level,
                priceRange,
                rating,
                duration,
                popular,
                pageSize,
                offset,
                userId
        );
    }

    @Override
    public int countFilteredCoursesByTag(
            int idTag,
            String sortPrice,
            String level,
            String priceRange,
            String rating,
            String duration,
            String popular) {

        return courseDao.countFilteredCourses(
                null,
                idTag,
                null,
                sortPrice,
                level,
                priceRange,
                rating,
                duration,
                popular
        );
    }

    // tổng quát nhất
    @Override
    public List<CourseCardDto> filterCoursesForAllCourses(AllCourseFilter allCourseFilter) {

        return courseDao.filterAllCoursesWithPagination(allCourseFilter);
    }

    //    // đếm tổng quát (dùng để tính totalPages)
    @Override
    public int countFilteredCourses(
            Integer categoryId, Integer tagId, String title,
            String sortPrice, String level, String priceRange,
            String rating, String duration, String popular) {

        return courseDao.countFilteredCourses(
                categoryId, tagId, title,
                sortPrice, level, priceRange, rating, duration, popular);
    }

    @Override
    public CourseCardDto getCourseCardById(int id, int userId) {
        return courseDao.findCourseCardById(id, userId);
    }

    @Override
    public List<CourseCardDto> getCourseSuggestByTitle(String keyword) {
        return courseDao.findCourseSuggestByTitle(keyword);
    }

    @Override
    public List<CourseCardDto> filterCourses(AllCourseFilter filter) {
        return courseDao.filterCourses(filter);
    }

    @Override
    public int countFilterCourses(AllCourseFilter filter) {
        return courseDao.countFilterCourses(filter);
    }

}
