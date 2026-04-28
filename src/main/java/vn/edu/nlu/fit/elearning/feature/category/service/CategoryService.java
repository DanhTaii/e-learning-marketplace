package vn.edu.nlu.fit.elearning.feature.category.service;

import vn.edu.nlu.fit.elearning.common.helper.pagination.filter.category.CategoryFilter;
import vn.edu.nlu.fit.elearning.feature.category.dto.CategoryDto;
import vn.edu.nlu.fit.elearning.feature.category.dto.CategoryOptionDto;
import vn.edu.nlu.fit.elearning.feature.category.model.Category;

import java.util.List;

public interface CategoryService {
    int createCategory(Category category);

    List<Category> getAllCategories();

    Category getCategoryById(int id);

    int updateCategory(Category category);

    int deleteCategory(int id);

    List<Category> getAllCategoriesByName(String name);

    CategoryDto getCategoryByCourseId(int courseId);

    public boolean existsByName(String name);

    public boolean existsBySlug(String slug);

    public boolean existsBySlug(String slug, int excludeId);

    public List<Category> getCategoriesByFilter(CategoryFilter filter);

    public int getCountCategoriesByFilter(CategoryFilter filter);

    public List<CategoryOptionDto> getCategoriesIdAndName();
}
