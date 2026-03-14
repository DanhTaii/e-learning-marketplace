package vn.edu.nlu.fit.elearning.feature.course_user.service;

import vn.edu.nlu.fit.elearning.common.utils.search.AllCourseFilter;
import vn.edu.nlu.fit.elearning.feature.course_user.dao.CourseSearchDao;
import vn.edu.nlu.fit.elearning.feature.course_user.dto.CourseCardDto;

import java.util.List;

public class CourseSearchServiceImpl implements CourseSearchService {
    private CourseSearchDao courseSearchDao;

    public CourseSearchServiceImpl(CourseSearchDao courseSearchDao) {
        this.courseSearchDao = courseSearchDao;
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
        return courseSearchDao.filterResultSearchWithPagination(
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

        return courseSearchDao.countFilteredCourses(
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
        return courseSearchDao.filterResultSearchWithPagination(
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

        return courseSearchDao.countFilteredCourses(
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
        return courseSearchDao.filterResultSearchWithPagination(
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

        return courseSearchDao.countFilteredCourses(
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

        return courseSearchDao.filterAllCoursesWithPagination(allCourseFilter);
    }

    //    // đếm tổng quát (dùng để tính totalPages)
    @Override
    public int countFilteredCourses(
            Integer categoryId, Integer tagId, String title,
            String sortPrice, String level, String priceRange,
            String rating, String duration, String popular) {

        return courseSearchDao.countFilteredCourses(
                categoryId, tagId, title,
                sortPrice, level, priceRange, rating, duration, popular);
    }


}
