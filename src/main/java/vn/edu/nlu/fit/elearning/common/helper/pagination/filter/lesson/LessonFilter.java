package vn.edu.nlu.fit.elearning.common.helper.pagination.filter.lesson;

import vn.edu.nlu.fit.elearning.common.helper.enums.BaseStatus;
import vn.edu.nlu.fit.elearning.common.helper.pagination.base.BaseSearchFilter;

import java.sql.Timestamp;

public class LessonFilter extends BaseSearchFilter {
    private String title;
    private int courseId;
    private Timestamp fromDate;
    private Timestamp toDate;
    private BaseStatus status;
    private boolean missingVideo;

    public LessonFilter() {
    }

    public LessonFilter(String title, int courseId, Timestamp fromDate, Timestamp toDate, BaseStatus status, boolean missingVideo) {
        this.title = title;
        this.courseId = courseId;
        this.fromDate = fromDate;
        this.toDate = toDate;
        this.status = status;
        this.missingVideo = missingVideo;
    }

    public BaseStatus getStatus() {
        return status;
    }

    public void setStatus(BaseStatus status) {
        this.status = status;
    }

    public boolean isMissingVideo() {
        return missingVideo;
    }

    public void setMissingVideo(boolean missingVideo) {
        this.missingVideo = missingVideo;
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
