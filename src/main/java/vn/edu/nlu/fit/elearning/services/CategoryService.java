package vn.edu.nlu.fit.elearning.services;

import vn.edu.nlu.fit.elearning.dao.CategoryDao;
import vn.edu.nlu.fit.elearning.model.Category;

import java.util.List;

public class CategoryService {
    private CategoryDao categoryDao;

    public CategoryService() {
        this.categoryDao = new CategoryDao();
    }

    public int createCategory(Category category) {
        return 0;
    }

    public List<Category> getAllCategories() {
        return categoryDao.findAll();
    }

    public Category getCategoryById(int id) {
        return null;
    }

    public void updateCategory(Category category) {

    }

    public void deleteCategory(int id) {
    }
}
