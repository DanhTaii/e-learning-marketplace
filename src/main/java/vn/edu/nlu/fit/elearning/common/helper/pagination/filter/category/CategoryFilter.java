package vn.edu.nlu.fit.elearning.common.helper.pagination.filter.category;

import vn.edu.nlu.fit.elearning.common.helper.enums.BaseStatus;
import vn.edu.nlu.fit.elearning.common.helper.pagination.base.BaseSearchFilter;

import java.sql.Timestamp;

public class CategoryFilter extends BaseSearchFilter {

    private String name;        // searchName
    private String slug;        // slug
    private Integer parentId;
    private Timestamp fromDate;
    private Timestamp toDate;
    private BaseStatus status;

    public CategoryFilter() {}

    public CategoryFilter(String name, String slug, Integer parentId,
                          Timestamp fromDate, Timestamp toDate, BaseStatus status) {
        this.name = name;
        this.slug = slug;
        this.parentId = parentId;
        this.fromDate = fromDate;
        this.toDate = toDate;
        this.status = status;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSlug() {
        return slug;
    }

    public void setSlug(String slug) {
        this.slug = slug;
    }

    public Integer getParentId() {
        return parentId;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
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

    public BaseStatus getStatus() {
        return status;
    }

    public void setStatus(BaseStatus status) {
        this.status = status;
    }
}