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

    public List<Course> getSixCoursesLast() {
        return cd.getSixCoursesLast();
    }

    public Course getCourse(int id) {
        return cd.getCourse(id);
    }
}
