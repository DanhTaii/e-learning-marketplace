package vn.edu.nlu.fit.elearning.feature.course.service;

import vn.edu.nlu.fit.elearning.feature.course.dao.CourseDao;
import vn.edu.nlu.fit.elearning.feature.course.dto.CourseCardDto;
import vn.edu.nlu.fit.elearning.feature.course.dto.CourseDetailDto;
import vn.edu.nlu.fit.elearning.feature.course.model.Course;
import vn.edu.nlu.fit.elearning.common.utils.objects.CourseFilter;

import java.util.List;

public class CourseServiceImpl implements CourseService {
    private CourseDao cd;

    public CourseServiceImpl(CourseDao courseDao) {
        this.cd = courseDao;
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
    public int deleteCourse(int id) {
        return cd.delete(id);
    }

    @Override
    public List<Course> getAllCourses() {
        return cd.findAllCourses();
    }

//    public int totalCourses() {
//        int result = 0;
//        List<Course> courseList = cd.findAllCourses();
//        for (Course c : courseList) {
//            result++;
//        }
//        return result;
//    }

    @Override
    public double avgRating() {
        double result = 0.0;
        int count = 0;
        double sum = 0.0;
        List<Course> courseList = cd.findAllCourses();
        for (Course c : courseList) {
            sum += c.getRating();
            count++;
        }
        result += sum / count;
        // làm tròn 1 chữ số sau dấu phẩy
        return Math.round(result * 10.0) / 10.0;
    }


    @Override
    public CourseDetailDto getCourse(int id, int userId) {
        return cd.findCourseByIdForDetail(id, userId);
    }

    @Override
    public CourseCardDto getCourseCardById(int id, int userId) {
        return cd.findCourseCardById(id, userId);
    }

//    public List<CourseCardDto> getCoursesByTitle(String search) {
//        return cd.findCoursesByTitle(search);
//    }

    @Override
    public List<Course> getAllCourses(CourseFilter filter, int pageSize, int offset) {
        return cd.filterAllCourses(filter, pageSize, offset);
    }

    @Override
    public int countAllCourseAdmin(CourseFilter filter){
        return cd.countAdminAllCourses(filter);
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
        return cd.filterResultSearchWithPagination(
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

        return cd.countFilteredCourses(
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
        return cd.filterResultSearchWithPagination(
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

        return cd.countFilteredCourses(
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
        return cd.filterResultSearchWithPagination(
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

        return cd.countFilteredCourses(
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
    public List<CourseCardDto> filterCoursesForResultSearch(
            Integer categoryId, Integer tagId, String title,
            String sortPrice, String level, String priceRange,
            String rating, String duration, String popular,
            int limit, int offset, int userId) {

        return cd.filterResultSearchWithPagination(
                categoryId, tagId, title,
                sortPrice, level, priceRange, rating, duration, popular,
                limit, offset, userId);
    }

    // tổng quát nhất
    @Override
    public List<CourseCardDto> filterCoursesForAllCourses(
            Integer categoryId,
            String sortPrice, boolean popular, boolean newest,
            int limit, int offset, int userId) {

        return cd.filterAllCoursesWithPagination(
                categoryId,
                sortPrice, popular, newest,
                limit, offset, userId);
    }

    //    // đếm tổng quát (dùng để tính totalPages)
    @Override
    public int countFilteredCourses(
            Integer categoryId, Integer tagId, String title,
            String sortPrice, String level, String priceRange,
            String rating, String duration, String popular) {

        return cd.countFilteredCourses(
                categoryId, tagId, title,
                sortPrice, level, priceRange, rating, duration, popular);
    }

    @Override
    public List<CourseCardDto> getCourseSuggestByTitle(String keyword) {
        return cd.findCourseSuggestByTitle(keyword);
    }

}
