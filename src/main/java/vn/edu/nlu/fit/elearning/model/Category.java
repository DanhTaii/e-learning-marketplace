package vn.edu.nlu.fit.elearning.model;

import vn.edu.nlu.fit.elearning.dao.BaseCrudDao;
import vn.edu.nlu.fit.elearning.dao.BaseDao;
import vn.edu.nlu.fit.elearning.enums.BasicStatus;

import java.io.Serializable;
import java.sql.Timestamp;

public class Category implements Serializable {

    private int id;

    private String name;

    private String slug;

    private int parentId; // Vẫn phải dùng Integer vì NULL

    private String iconUrl;

    private BasicStatus status;

    private Timestamp createdAt;

    private Timestamp updatedAt;

    // --- Constructors ---
    public Category() {
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

    public String getIconUrl() {
        return iconUrl;
    }

    public void setIconUrl(String iconUrl) {
        this.iconUrl = iconUrl;
    }

    public BasicStatus getStatus() {
        return status;
    }

    public void setStatus(BasicStatus status) {
        this.status = status;
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

    @Override
    public String toString() {
        return "Category{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", slug='" + slug + '\'' +
                ", parentId=" + parentId +
                ", iconUrl='" + iconUrl + '\'' +
                ", status=" + status +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                '}';
    }
}
