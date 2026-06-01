package vn.edu.nlu.fit.elearning.feature.enrollment.dto;

import vn.edu.nlu.fit.elearning.feature.lesson_progress.dto.LessonProgressDTO;
import vn.edu.nlu.fit.elearning.feature.review.dto.ReviewDto;

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
    private int percentCompleted;
    private List<LessonProgressDTO> listLesson;
    private LessonProgressDTO currentLesson;
    private List<ReviewDto> listReviews;
    private String description;
    private String goals;
    private boolean isReviewed;

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

    public int getPercentCompleted() {
        return percentCompleted;
    }

    public void setPercentCompleted(int percentCompleted) {
        this.percentCompleted = percentCompleted;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getGoals() {
        return goals;
    }

    public void setGoals(String goals) {
        this.goals = goals;
    }

    public boolean getIsReviewed() {
        return isReviewed;
    }

    public void setIsReviewed(boolean reviewed) {
        isReviewed = reviewed;
    }

    public String getDurationText() {
        int hours = (int) this.durationHours;
        int minutes = (int) ((this.durationHours - hours) * 60);
        if(hours == 0){
            return minutes + "p";
        }else if(minutes == 0) {
            return hours + "h ";
        }else{
            return hours + "h " + minutes + "p";
        }

    }

    @Override
    public String toString() {
        return "EnrollmentDetailDto{" +
                "id=" + id +
                ", courseId=" + courseId +
                ", title='" + title + '\'' +
                ", authorName='" + authorName + '\'' +
                ", rating=" + rating +
                ", durationHours=" + durationHours +
                ", studentCount=" + studentCount +
                ", reviewCount=" + reviewCount +
                ", listLesson=" + listLesson +
                ", currentLesson=" + currentLesson +
                ", listReviews=" + listReviews +
                '}';
    }
}
