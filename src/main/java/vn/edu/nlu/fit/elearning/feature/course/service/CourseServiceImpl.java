package vn.edu.nlu.fit.elearning.feature.course.service;

import vn.edu.nlu.fit.elearning.feature.course.dao.CourseDao;
import vn.edu.nlu.fit.elearning.feature.course_user.dto.CourseCardDto;
import vn.edu.nlu.fit.elearning.feature.course_user.dto.CourseDetailDto;
import vn.edu.nlu.fit.elearning.feature.course.model.Course;
import vn.edu.nlu.fit.elearning.common.helper.pagination.filter.course.CourseFilter;

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
    public List<Course> getAllCourses(CourseFilter filter) {
        return cd.filterAllCourses(filter);
    }

    @Override
    public int countAllCourseAdmin(CourseFilter filter) {
        return cd.countAdminAllCourses(filter);
    }


//    // tổng quát nhất
//    @Override
//    public List<CourseCardDto> filterCoursesForResultSearch(
//            Integer categoryId, Integer tagId, String title,
//            String sortPrice, String level, String priceRange,
//            String rating, String duration, String popular,
//            int limit, int offset, int userId) {
//
//        return cd.filterResultSearchWithPagination(
//                categoryId, tagId, title,
//                sortPrice, level, priceRange, rating, duration, popular,
//                limit, offset, userId);
//    }


    @Override
    public List<CourseCardDto> getCourseSuggestByTitle(String keyword) {
        return cd.findCourseSuggestByTitle(keyword);
    }

    @Override
    public int getTotalCourses() {
        return cd.countAllCourses();
    }

    @Override
    public int deleteCoursesByIds(List<Integer> ids) {
        return cd.deleteCoursesByIds(ids);
    }

    @Override
    public int bulkDuplicateCourses(List<Integer> ids) {
        int count = 0;
        int result = 0;

        for (int id : ids) {
            Course odinary = this.getCourseById(id);

            if(odinary != null) {
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
                if(result > 0) {
                    count++;
                }
            }
        }
        return count;
    }

    @Override
    public int changeCoursesStatusByIds(List<Integer> ids) {
        return cd.updateCoursesStatusByIds(ids);
    }

}
