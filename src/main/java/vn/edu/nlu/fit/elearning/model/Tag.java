package vn.edu.nlu.fit.elearning.model;

import java.io.Serializable;
import java.time.LocalDateTime;

public class Tag implements Serializable {

    private Integer id;

    private String name;

    private int courseCount;

    private String slug;

    private java.sql.Timestamp createdAt;

    public Tag() {
    }

    public Tag(Integer id, String name, int courseCount, String slug, java.sql.Timestamp createdAt) {
        this.id = id;
        this.name = name;
        this.courseCount = courseCount;
        this.slug = slug;
        this.createdAt = createdAt;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
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

    public java.sql.Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(java.sql.Timestamp createdAt) {
        this.createdAt = createdAt;
    }
}
