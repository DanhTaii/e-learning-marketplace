package vn.edu.nlu.fit.elearning.common.helper.pagination.filter.tag;

import vn.edu.nlu.fit.elearning.common.helper.pagination.base.BaseSearchFilter;

import java.sql.Timestamp;

public class TagFilter extends BaseSearchFilter {
    private String name;
    private int courseCount;
    private String slug;
    private Timestamp createdAt;

    public TagFilter(String name, int courseCount, String slug, Timestamp createdAt) {
        this.name = name;
        this.courseCount = courseCount;
        this.slug = slug;
        this.createdAt = createdAt;
    }

    public TagFilter() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCourseCount() {
        return courseCount;
    }

    public void setCourseCount(int courseCount) {
        this.courseCount = courseCount;
    }

    public String getSlug() {
        return slug;
    }

    public void setSlug(String slug) {
        this.slug = slug;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }
}
