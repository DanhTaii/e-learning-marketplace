package vn.edu.nlu.fit.elearning.common.helper.pagination.filter.role;

import vn.edu.nlu.fit.elearning.common.helper.pagination.base.BaseSearchFilter;

import java.sql.Timestamp;

public class RoleFilter extends BaseSearchFilter {

    private String name;
    private String description;
    private Integer permissionId;
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

    public Integer getPermissionId() {
        return permissionId;
    }

    public void setPermissionId(Integer permissionId) {
        this.permissionId = permissionId;
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