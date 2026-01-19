package vn.edu.nlu.fit.elearning.dto;

import vn.edu.nlu.fit.elearning.model.Lesson;

import java.io.Serializable;
import java.util.List;

public class EnrollmentDetailDto implements Serializable {
    private int id;
    private int courseId;
    private String title;
    private String authorName;
    private double rating;
    private double durationHours;
    private int studentCount;
    private int reviewCount;
    private List<LessonProgressDTO> listLesson;
    private LessonProgressDTO currentLesson;
    private List<ReviewDto> listReviews;

    public EnrollmentDetailDto(int id, int courseId, String title, String authorName, double rating, double durationHours, int studentCount, int reviewCount, List<LessonProgressDTO> listLesson, LessonProgressDTO currentLesson, List<ReviewDto> listReviews) {
        this.id = id;
        this.courseId = courseId;
        this.title = title;
        this.authorName = authorName;
        this.rating = rating;
        this.durationHours = durationHours;
        this.studentCount = studentCount;
        this.reviewCount = reviewCount;
        this.listLesson = listLesson;
        this.currentLesson = currentLesson;
        this.listReviews = listReviews;
    }

    public EnrollmentDetailDto() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCourseId() {
        return courseId;
    }

    public void setCourseId(int courseId) {
        this.courseId = courseId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthorName() {
        return authorName;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public double getDurationHours() {
        return durationHours;
    }

    public void setDurationHours(double durationHours) {
        this.durationHours = durationHours;
    }

    public int getStudentCount() {
        return studentCount;
    }

    public void setStudentCount(int studentCount) {
        this.studentCount = studentCount;
    }

    public int getReviewCount() {
        return reviewCount;
    }

    public void setReviewCount(int reviewCount) {
        this.reviewCount = reviewCount;
    }

    public List<LessonProgressDTO> getListLesson() {
        return listLesson;
    }

    public void setListLesson(List<LessonProgressDTO> listLesson) {
        this.listLesson = listLesson;
    }

    public LessonProgressDTO getCurrentLesson() {
        return currentLesson;
    }

    public void setCurrentLesson(LessonProgressDTO currentLesson) {
        this.currentLesson = currentLesson;
    }

    public List<ReviewDto> getListReviews() {
        return listReviews;
    }

    public void setListReviews(List<ReviewDto> listReviews) {
        this.listReviews = listReviews;
    }
}
