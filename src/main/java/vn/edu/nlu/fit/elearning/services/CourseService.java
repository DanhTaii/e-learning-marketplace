package vn.edu.nlu.fit.elearning.services;

import vn.edu.nlu.fit.elearning.dao.CourseDao;
import vn.edu.nlu.fit.elearning.model.Course;

import java.util.List;

public class CourseService {
    CourseDao cd = new CourseDao();

    public List<Course> getAllCourses() {
        return cd.getAllCourses();
    }

}
