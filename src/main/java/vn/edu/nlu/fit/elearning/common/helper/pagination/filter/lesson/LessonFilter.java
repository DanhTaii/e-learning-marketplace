package vn.edu.nlu.fit.elearning.common.helper.pagination.filter.lesson;

import vn.edu.nlu.fit.elearning.common.helper.pagination.base.BaseSearchFilter;

import java.sql.Timestamp;

public class LessonFilter extends BaseSearchFilter {
    private String title;
    private int courseId;
    private Timestamp createdAt;

    public LessonFilter() {
    }

    public LessonFilter(String title, int courseId, Timestamp createdAt) {
        this.title = title;
        this.courseId = courseId;
        this.createdAt = createdAt;
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

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }
}
