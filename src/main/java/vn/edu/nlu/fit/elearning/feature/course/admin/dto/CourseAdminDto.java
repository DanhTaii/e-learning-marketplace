package vn.edu.nlu.fit.elearning.feature.course.admin.dto;

import vn.edu.nlu.fit.elearning.common.helper.enums.Level;

import java.io.Serializable;
import java.sql.Timestamp;

public class CourseAdminDto implements Serializable {
    private int id;
    private String title;
    private Level level;
    private int studentCount;
    private boolean isPublic;
    private int categoryId;
    private String categoryName;
    private double durationHours;
    private String durationText;
    private Timestamp createdAt;

    public CourseAdminDto() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Level getLevel() {
        return level;
    }

    public void setLevel(Level level) {
        this.level = level;
    }

    public int getStudentCount() {
        return studentCount;
    }

    public void setStudentCount(int studentCount) {
        this.studentCount = studentCount;
    }

    public boolean getIsPublic() {
        return isPublic;
    }

    public void setIsPublic(boolean aPublic) {
        isPublic = aPublic;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public double getDurationHours() {
        return durationHours;
    }

    public void setDurationHours(double durationHours) {
        this.durationHours = durationHours;
        this.durationText = calculateDurationText(durationHours); // Tự động cập nhật text
    }

    public String getDurationText() {
        return durationText;
    }

    public void setDurationText(String durationText) {
        this.durationText = durationText;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }

    private String calculateDurationText(double duration) {
        int hours = (int) duration;
        int minutes = (int) Math.round((duration - hours) * 60);
        if (hours == 0) return minutes + "p";
        if (minutes == 0) return hours + "h";
        return hours + "h " + minutes + "p";
    }
}
