package vn.edu.nlu.fit.elearning.common.helper.pagination.filter.permission;

import vn.edu.nlu.fit.elearning.common.helper.pagination.base.BaseSearchFilter;

import java.sql.Timestamp;

public class PermissionFilter extends BaseSearchFilter {

    private String name;
    private String description;
    private String groupName;

    private Timestamp fromDate;
    private Timestamp toDate;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
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