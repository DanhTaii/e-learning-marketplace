package vn.edu.nlu.fit.elearning.services;

import vn.edu.nlu.fit.elearning.dao.CourseDao;
import vn.edu.nlu.fit.elearning.model.Course;
import vn.edu.nlu.fit.elearning.model.User;

import java.util.ArrayList;
import java.util.List;

public class CourseService {
    CourseDao cd = new CourseDao();

    public List<Course> getAllCourses() {
        return cd.getAllCourses();
    }

    public int totalCourses() {
        int result = 0;
        List<Course> courseList = cd.getAllCourses();
        for (Course c : courseList){
            result++;
        }
        return result;
    }

    public double avgRating() {
        double result = 0.0;
        int count = 0;
        double sum = 0.0;
        List<Course> courseList = cd.getAllCourses();
        for (Course c : courseList){
            sum += c.getRating();
            count++;
        }
        result += sum/count;
        // làm tròn 1 chữ số sau dấu phẩy
        return Math.round(result * 10.0) / 10.0;
    }

    public List<Course> getThreeCoursesWereLiked() {
        return cd.getThreeCoursesWereLiked();
    }

    public List<Course> getSixCoursesMostPopular() {
        return cd.getSixCoursesMostPopular();
    }

    public Course getCoursesMostPopular() {
        return cd.getCoursesMostPopular();
    }

    public List<Course> getSixCoursesLast() {
        return cd.getSixCoursesLast();
    }

    public Course getCourse(int id) {
        return cd.getCourse(id);
    }

    public List<Course> getCoursesByIdCategory(int idCategory) {
        return cd.getCoursesByIdCategory(idCategory);
    }

    public List<Course> getCoursesByTitle(String search) {
        return cd.getCoursesByTitle(search);
    }

    // gọi lại phương thức filterCourses trong DAO cho trường hợp lọc theo category
    public List<Course> filterCoursesByCategory(int idCategory,
                                                String sortPrice,
                                                String level,
                                                String priceRange,
                                                String rating,
                                                String duration,
                                                String popular) {
        return cd.filterCourses(idCategory, null, sortPrice, level, priceRange, rating, duration, popular);
    }
    // gọi lại phương thức filterCourses trong DAO cho trường hợp search theo title
    public List<Course> filterCoursesByTitle(String search,
                                             String sortPrice,
                                             String level,
                                             String priceRange,
                                             String rating,
                                             String duration,
                                             String popular) {
        return cd.filterCourses(null, search, sortPrice, level, priceRange, rating, duration, popular);
    }


}
