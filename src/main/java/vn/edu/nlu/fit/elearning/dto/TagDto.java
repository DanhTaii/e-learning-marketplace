package vn.edu.nlu.fit.elearning.dto;

import vn.edu.nlu.fit.elearning.enums.BasicStatus;

import java.sql.Timestamp;

public class TagDto {
    private int id;
    private String name;
    private String slug;
    private BasicStatus status;
    private int courseId;

    public TagDto() {
    }

    public TagDto(int id, String name, String slug, BasicStatus status, int courseId) {
        this.id = id;
        this.name = name;
        this.slug = slug;
        this.status = status;
        this.courseId = courseId;
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

    public String getSlug() {
        return slug;
    }

    public void setSlug(String slug) {
        this.slug = slug;
    }

    public BasicStatus getStatus() {
        return status;
    }

    public void setStatus(BasicStatus status) {
        this.status = status;
    }

    public int getCourseId() {
        return courseId;
    }

    public void setCourseId(int courseId) {
        this.courseId = courseId;
    }
}
