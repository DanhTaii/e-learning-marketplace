package vn.edu.nlu.fit.elearning.feature.tag.dto;

import vn.edu.nlu.fit.elearning.common.helper.enums.BaseStatus;

import java.io.Serializable;

public class TagDto implements Serializable {
    private int id;
    private String name;
    private String slug;
    private BaseStatus status;
    private int courseId;

    public TagDto() {
    }

    public TagDto(int id, String name, String slug, BaseStatus status, int courseId) {
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

    public BaseStatus getStatus() {
        return status;
    }

    public void setStatus(BaseStatus status) {
        this.status = status;
    }

    public int getCourseId() {
        return courseId;
    }

    public void setCourseId(int courseId) {
        this.courseId = courseId;
    }
}
