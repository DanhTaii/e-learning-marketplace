package vn.edu.nlu.fit.elearning.feature.category.service;

import vn.edu.nlu.fit.elearning.common.helper.pagination.filter.category.CategoryFilter;
import vn.edu.nlu.fit.elearning.common.helper.pagination.filter.lesson.LessonFilter;
import vn.edu.nlu.fit.elearning.feature.category.dao.CategoryDao;
import vn.edu.nlu.fit.elearning.feature.category.dto.CategoryDto;
import vn.edu.nlu.fit.elearning.feature.category.dto.CategoryOptionDto;
import vn.edu.nlu.fit.elearning.feature.category.model.Category;
import vn.edu.nlu.fit.elearning.feature.lesson.model.Lesson;

import java.util.List;

public class CategoryServiceImpl implements CategoryService {
    private CategoryDao categoryDao;

    public CategoryServiceImpl(CategoryDao categoryDao) {
        this.categoryDao = categoryDao;
    }

    @Override
    public int createCategory(Category category) {
        if (category != null) {
            return categoryDao.create(category);
        }
        return 0;
    }

    @Override
    public List<Category> getAllCategories() {
        return categoryDao.findAll();
    }

    @Override
    public Category getCategoryById(int id) {
        return categoryDao.findById(id);
    }

    @Override
    public int updateCategory(Category category) {
        return categoryDao.update(category);
    }

    @Override
    public int deleteCategory(int id) {
        return categoryDao.delete(id);
    }

    @Override
    public List<Category> getAllCategoriesByName(String name) {
        return categoryDao.findByName(name);
    }

    @Override
    public CategoryDto getCategoryByCourseId(int courseId) {
        return categoryDao.findCategoryByCourseId(courseId);
    }

    @Override
    public boolean existsByName(String name) {
        List<Category> list = categoryDao.findByName(name);
        return list != null && !list.isEmpty();
    }

    @Override
    public boolean existsBySlug(String slug) {
        Category c = categoryDao.findBySlug(slug);
        return c != null;
    }

    @Override
    public boolean existsBySlug(String slug, int excludeId) {
        Category c = categoryDao.findBySlugExcludeId(slug, excludeId);
        return c != null;
    }

    @Override
    public List<Category> getCategoriesByFilter(CategoryFilter filter) {
        return categoryDao.findCategoriesByFilter(filter);
    }

    @Override
    public int getCountCategoriesByFilter(CategoryFilter filter) {
        return categoryDao.countCategoriesByFilter(filter);
    }

    @Override
    public List<CategoryOptionDto> getCategoriesIdAndName() {
        return categoryDao.findIdAndName();
    }


}
