package vn.edu.nlu.fit.elearning.common.helper.pagination.filter.course;

import vn.edu.nlu.fit.elearning.common.helper.pagination.base.BaseSearchFilter;

import java.sql.Timestamp;

public class CourseArchivedFilter extends BaseSearchFilter {
    private String title;

    private int categoryId;

    private Timestamp deletedFromDate;

    private Timestamp deletedToDate;

    public CourseArchivedFilter() {
    }

    public CourseArchivedFilter(String title, int categoryId, Timestamp deletedFromDate, Timestamp deletedToDate) {
        this.title = title;
        this.categoryId = categoryId;
        this.deletedFromDate = deletedFromDate;
        this.deletedToDate = deletedToDate;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public Timestamp getDeletedFromDate() {
        return deletedFromDate;
    }

    public void setDeletedFromDate(Timestamp deletedFromDate) {
        this.deletedFromDate = deletedFromDate;
    }

    public Timestamp getDeletedToDate() {
        return deletedToDate;
    }

    public void setDeletedToDate(Timestamp deletedToDate) {
        this.deletedToDate = deletedToDate;
    }
}
