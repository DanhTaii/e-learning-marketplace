package vn.edu.nlu.fit.elearning.feature.category.dto;

import vn.edu.nlu.fit.elearning.common.helper.enums.BaseStatus;

public class CategoryDto {
    private int id;
    private String name;
    private String slug;
    private int parentId;
    private BaseStatus status;
    private int courseId;

    public CategoryDto() {
    }

    public CategoryDto(int id, String name, String slug, int parentId, BaseStatus status, int courseId) {
        this.id = id;
        this.name = name;
        this.slug = slug;
        this.parentId = parentId;
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

    public int getParentId() {
        return parentId;
    }

    public void setParentId(int parentId) {
        this.parentId = parentId;
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
