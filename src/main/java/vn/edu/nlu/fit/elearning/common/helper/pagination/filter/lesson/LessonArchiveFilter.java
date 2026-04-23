package vn.edu.nlu.fit.elearning.common.helper.pagination.filter.lesson;

import vn.edu.nlu.fit.elearning.common.helper.pagination.base.BaseSearchFilter;

import java.sql.Timestamp;

public class LessonArchiveFilter extends BaseSearchFilter {
    private String title;

    private int courseId;

    private Timestamp deletedFromDate;

    private Timestamp deletedToDate;

    public LessonArchiveFilter() {
    }

    public LessonArchiveFilter(String title, int courseId, Timestamp deletedFromDate, Timestamp deletedToDate) {
        this.title = title;
        this.courseId = courseId;
        this.deletedFromDate = deletedFromDate;
        this.deletedToDate = deletedToDate;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getCourseId() {
        return courseId;
    }

    public void setCourseId(int courseId) {
        this.courseId = courseId;
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
