package vn.edu.nlu.fit.elearning.common.helper.pagination.filter.certificate;

import vn.edu.nlu.fit.elearning.common.helper.enums.BaseStatus;
import vn.edu.nlu.fit.elearning.common.helper.pagination.base.BaseSearchFilter;

import java.sql.Timestamp;

public class CertificateFilter extends BaseSearchFilter {
    private String certificateCode;
    private String userName;
    private Integer courseId;
    private Timestamp fromDate;
    private Timestamp toDate;
    private BaseStatus status;

    public CertificateFilter() {
    }

    public CertificateFilter(String certificateCode, String userName, Integer courseId, Timestamp fromDate, Timestamp toDate, BaseStatus status) {
        this.certificateCode = certificateCode;
        this.userName = userName;
        this.courseId = courseId;
        this.fromDate = fromDate;
        this.toDate = toDate;
        this.status = status;
    }

    public BaseStatus getStatus() {
        return status;
    }

    public void setStatus(BaseStatus status) {
        this.status = status;
    }

    public Timestamp getToDate() {
        return toDate;
    }

    public void setToDate(Timestamp toDate) {
        this.toDate = toDate;
    }

    public Timestamp getFromDate() {
        return fromDate;
    }

    public void setFromDate(Timestamp fromDate) {
        this.fromDate = fromDate;
    }

    public Integer getCourseId() {
        return courseId;
    }

    public void setCourseId(Integer courseId) {
        this.courseId = courseId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getCertificateCode() {
        return certificateCode;
    }

    public void setCertificateCode(String certificateCode) {
        this.certificateCode = certificateCode;
    }
}
