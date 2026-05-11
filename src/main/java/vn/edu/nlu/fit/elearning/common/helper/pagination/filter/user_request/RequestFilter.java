package vn.edu.nlu.fit.elearning.common.helper.pagination.filter.user_request;

import vn.edu.nlu.fit.elearning.common.helper.enums.ContactStatus;
import vn.edu.nlu.fit.elearning.common.helper.pagination.base.BaseSearchFilter;

import java.sql.Timestamp;

public class RequestFilter extends BaseSearchFilter {

    private String email;
    private String subject;
    private ContactStatus status;

    private Timestamp fromDate;
    private Timestamp toDate;

    public RequestFilter() {
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public ContactStatus getStatus() {
        return status;
    }

    public void setStatus(ContactStatus status) {
        this.status = status;
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