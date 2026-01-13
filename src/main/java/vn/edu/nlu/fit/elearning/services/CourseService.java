package vn.edu.nlu.fit.elearning.services;

import vn.edu.nlu.fit.elearning.dao.CourseDao;
import vn.edu.nlu.fit.elearning.dto.CourseCardDto;
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

    public List<Course> getAllCourses() {
        return cd.findAllCourses();
    }

    public int totalCourses() {
        int result = 0;
        List<Course> courseList = cd.findAllCourses();
        for (Course c : courseList) {
            result++;
        }
        return result;
    }

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

    public List<Course> getThreeCoursesWereLiked() {
        return cd.findThreeCoursesWereLiked();
    }

    public List<Course> getSixCoursesMostPopular() {
        return cd.findSixCoursesMostPopular();
    }

    public Course getCoursesMostPopular() {
        return cd.findCoursesMostPopular();
    }

    public List<Course> getSixCoursesLast() {
        return cd.findSixCoursesLast();
    }

    public Course getCourse(int id) {
        return cd.findCourseByIdForDetail(id);
    }

    public List<CourseCardDto> getCourseCards() {
        return cd.findAllCoursesCard();
    }

    public List<CourseCardDto> getCoursesByIdCategory(int idCategory) {
        return cd.findCoursesByIdCategory(idCategory);
    }

    public List<CourseCardDto> getCoursesByIdTag(int idTag) {
        return cd.findCoursesByIdTag(idTag);
    }

    public List<CourseCardDto> getCoursesByTitle(String search) {
        return cd.findCoursesByTitle(search);
    }

    public List<Course> getAllCourses(CourseFilter filter) {
        return cd.filterAllCourses(filter);
    }

    public List<CourseCardDto> getCourseCardsByPage(int page, int pageSize) {
        return cd.findCoursesCardByPage(page, pageSize);
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
            int pageSize) {

        int offset = (page - 1) * pageSize;
        return cd.filterCoursesWithPagination(
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
                offset        // offset
        );
    }

    // Đếm tổng số khóa học sau lọc theo category
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
            int pageSize) {

        int offset = (page - 1) * pageSize;
        return cd.filterCoursesWithPagination(
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
                offset
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
            int pageSize) {

        int offset = (page - 1) * pageSize;
        return cd.filterCoursesWithPagination(
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
                offset
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
}
