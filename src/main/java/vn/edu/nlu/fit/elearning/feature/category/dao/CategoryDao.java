package vn.edu.nlu.fit.elearning.feature.category.dao;

import vn.edu.nlu.fit.elearning.common.helper.pagination.filter.category.CategoryFilter;
import vn.edu.nlu.fit.elearning.feature.category.dto.CategoryDto;
import vn.edu.nlu.fit.elearning.feature.category.model.Category;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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



    public Category findBySlugExcludeId(String slug, int excludeId);

    public List<Category> findCategoriesByFilter(CategoryFilter filter);

    public int countCategoriesByFilter(CategoryFilter filter);
}
