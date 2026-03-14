package vn.edu.nlu.fit.elearning.feature.category.dao;

import vn.edu.nlu.fit.elearning.feature.category.dto.CategoryDto;
import vn.edu.nlu.fit.elearning.feature.category.model.Category;

import java.util.List;

public interface CategoryDao extends BaseCrudDao<Category, Integer> {
    List<Category> findByName(String name);

    Category findById(int id);

    CategoryDto getCategoryByCourseId(int courseId);
}
