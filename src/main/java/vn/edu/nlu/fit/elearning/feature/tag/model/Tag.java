package vn.edu.nlu.fit.elearning.feature.tag.model;

import vn.edu.nlu.fit.elearning.common.helper.enums.BaseStatus;

import java.io.Serializable;
import java.sql.Timestamp;

public class Tag implements Serializable {

    private int id;

    private String name;

    private int courseCount;

    private String slug;

    private BaseStatus status;

    private Timestamp createdAt;
    private Timestamp updatedAt;

    public Tag() {
    }

    public Tag(int id, String name, int courseCount, String slug, BaseStatus status, Timestamp createdAt, Timestamp updatedAt) {
        this.id = id;
        this.name = name;
        this.courseCount = courseCount;
        this.slug = slug;
        this.status = status;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public BaseStatus getStatus() {
        return status;
    }

    public void setStatus(BaseStatus status) {
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public Timestamp getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Timestamp updatedAt) {
        this.updatedAt = updatedAt;
    }
}
