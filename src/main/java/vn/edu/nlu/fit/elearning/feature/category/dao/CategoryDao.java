package vn.edu.nlu.fit.elearning.feature.category.dao;

import vn.edu.nlu.fit.elearning.feature.category.dto.CategoryDto;
import vn.edu.nlu.fit.elearning.feature.category.model.Category;

import java.util.List;

public interface CategoryDao {
    int create(Category entity);

    Category findById(Integer integer);

    List<Category> findAll();

    int update(Category entity);

    int delete(Integer integer);

    List<Category> findByName(String name);

//    Category findById(int id);

    CategoryDto findCategoryByCourseId(int courseId);

    public Category findBySlug(String slug);

    public boolean existsByName(String name);

    public boolean existsBySlug(String slug);

    public Category findBySlugExcludeId(String slug, int excludeId);
}
