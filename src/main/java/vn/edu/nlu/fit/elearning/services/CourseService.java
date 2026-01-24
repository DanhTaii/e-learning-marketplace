package vn.edu.nlu.fit.elearning.services;

import vn.edu.nlu.fit.elearning.dao.CourseDao;
import vn.edu.nlu.fit.elearning.dto.CourseCardDto;
import vn.edu.nlu.fit.elearning.dto.CourseDetailDto;
import vn.edu.nlu.fit.elearning.model.Category;
import vn.edu.nlu.fit.elearning.model.Course;
import vn.edu.nlu.fit.elearning.model.User;
import vn.edu.nlu.fit.elearning.utils.CourseFilter;

import java.util.ArrayList;
import java.util.List;

public class CourseService {
    private CourseDao cd;

    public CourseService() {
        this.cd = new CourseDao();
    }

    public int createCourse(Course course) {
        return cd.create(course);
    }

    public Course getCourseById(int id) {
        return cd.findById(id);
    }

    public int updateCourse(Course entity) {
        return this.cd.update(entity);
    }

    public int deleteCourse(int id) {
        return cd.delete(id);
    }

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

    public List<CourseCardDto> getThreeCoursesWereLiked(Integer userId) {
        return cd.findThreeCoursesWereLiked(userId);
    }

    public List<CourseCardDto> getSixCoursesMostPopular(Integer userId) {
        return cd.findSixCoursesMostPopular(userId);
    }

    public CourseCardDto getCoursesMostPopular(Integer userId) {
        return cd.findCourseMostPopular(userId);
    }

    public List<CourseCardDto> getSixCoursesLast(Integer userId) {
        return cd.findSixCoursesLast(userId);
    }

    public CourseDetailDto getCourse(int id, int userId) {
        return cd.findCourseByIdForDetail(id, userId);
    }

    public CourseCardDto getCourseCardById(int id, int userId) {
        return cd.findCourseCardById(id, userId);
    }

//    public List<CourseCardDto> getCoursesByTitle(String search) {
//        return cd.findCoursesByTitle(search);
//    }

    public List<Course> getAllCourses(CourseFilter filter) {
        return cd.filterAllCourses(filter);
    }

    // Filter theo category + phân trang
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
    public int countFilteredCourses(
            Integer categoryId, Integer tagId, String title,
            String sortPrice, String level, String priceRange,
            String rating, String duration, String popular) {

        return cd.countFilteredCourses(
                categoryId, tagId, title,
                sortPrice, level, priceRange, rating, duration, popular);
    }
}
