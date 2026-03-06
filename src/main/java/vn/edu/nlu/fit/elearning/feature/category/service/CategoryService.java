package vn.edu.nlu.fit.elearning.feature.category.service;

import vn.edu.nlu.fit.elearning.feature.category.dao.CategoryDao;
import vn.edu.nlu.fit.elearning.feature.category.dto.CategoryDto;
import vn.edu.nlu.fit.elearning.feature.category.model.Category;

import java.util.List;

public class CategoryService {
    private CategoryDao categoryDao;

    public CategoryService() {
        this.categoryDao = new CategoryDao();
    }

    public int createCategory(Category category) {
        if (category != null) {
            categoryDao.create(category);
            return 1;
        }
        return 0;
    }

    public List<Category> getAllCategories() {
        return categoryDao.findAll();
    }

    public Category getCategoryById(int id) {
        return categoryDao.findById(id);
    }

    public int updateCategory(Category category) {
        return categoryDao.update(category);
    }

    public int deleteCategory(int id) {
        return categoryDao.delete(id);
    }

    public List<Category> getAllCategoriesByName(String name) {
        return categoryDao.findByName(name);
    }

    public CategoryDto getCategoryByCourseId(int courseId) {
        return categoryDao.getCategoryByCourseId(courseId);
    }


}
