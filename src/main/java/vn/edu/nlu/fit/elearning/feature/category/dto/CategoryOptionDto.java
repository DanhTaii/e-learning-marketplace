package vn.edu.nlu.fit.elearning.feature.category.dto;

import java.io.Serializable;

public class CategoryOptionDto implements Serializable {
    private int id;
    private String name;

    public CategoryOptionDto() {
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
