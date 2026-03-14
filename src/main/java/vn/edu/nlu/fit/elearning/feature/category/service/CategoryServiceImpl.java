package vn.edu.nlu.fit.elearning.feature.category.service;

import vn.edu.nlu.fit.elearning.feature.category.dao.CategoryDao;
import vn.edu.nlu.fit.elearning.feature.category.dto.CategoryDto;
import vn.edu.nlu.fit.elearning.feature.category.model.Category;

import java.util.List;

public class CategoryServiceImpl implements CategoryService {
    private CategoryDao categoryDao;

    public CategoryServiceImpl(CategoryDao categoryDao) {
        this.categoryDao = categoryDao;
    }

    @Override
    public int createCategory(Category category) {
        if (category != null) {
            categoryDao.create(category);
            return 1;
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
        return categoryDao.getCategoryByCourseId(courseId);
    }


}
