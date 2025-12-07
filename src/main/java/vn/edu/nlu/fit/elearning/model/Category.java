package vn.edu.nlu.fit.elearning.model;

import vn.edu.nlu.fit.elearning.dao.BaseCrudDao;
import vn.edu.nlu.fit.elearning.dao.BaseDao;

import java.io.Serializable;

public class Category implements Serializable {

    private int id;

    private String name;

    private String slug;

    private int parentId; // Vẫn phải dùng Integer vì NULL

    private String iconUrl;

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
}
