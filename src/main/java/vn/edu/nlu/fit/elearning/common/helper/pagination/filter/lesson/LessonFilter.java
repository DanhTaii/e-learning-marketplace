package vn.edu.nlu.fit.elearning.common.helper.pagination.filter.lesson;

import vn.edu.nlu.fit.elearning.common.helper.pagination.base.BaseSearchFilter;

import java.sql.Timestamp;

public class LessonFilter extends BaseSearchFilter {
    private String title;
    private int courseId;
    private Timestamp fromDate;
    private Timestamp toDate;

    public LessonFilter() {
    }

    public LessonFilter(String title, int courseId, Timestamp fromDate, Timestamp toDate) {
        this.title = title;
        this.courseId = courseId;
        this.fromDate = fromDate;
        this.toDate = toDate;
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

    public Timestamp getFromDate() {
        return fromDate;
    }

    public void setFromDate(Timestamp fromDate) {
        this.fromDate = fromDate;
    }

    public Timestamp getToDate() {
        return toDate;
    }

    public void setToDate(Timestamp toDate) {
        this.toDate = toDate;
    }
}
