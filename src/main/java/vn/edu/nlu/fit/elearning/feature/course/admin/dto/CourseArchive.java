package vn.edu.nlu.fit.elearning.feature.course.admin.dto;

import java.io.Serializable;
import java.sql.Timestamp;

public class CourseArchive implements Serializable {

    private int id;

    private int categoryId;

    private String title;

    private String categoryName;

    private boolean isDeleted;

    private Timestamp deletedAt;

    private String deleteReason;

    public CourseArchive() {
    }

    public CourseArchive(int id, int categoryId, String title, String categoryName, boolean isDeleted, Timestamp deletedAt, String deleteReason) {
        this.id = id;
        this.categoryId = categoryId;
        this.title = title;
        this.categoryName = categoryName;
        this.isDeleted = isDeleted;
        this.deletedAt = deletedAt;
        this.deleteReason = deleteReason;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public boolean getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(boolean deleted) {
        isDeleted = deleted;
    }

    public Timestamp getDeletedAt() {
        return deletedAt;
    }

    public void setDeletedAt(Timestamp deletedAt) {
        this.deletedAt = deletedAt;
    }

    public String getDeleteReason() {
        return deleteReason;
    }

    public void setDeleteReason(String deleteReason) {
        this.deleteReason = deleteReason;
    }
}
