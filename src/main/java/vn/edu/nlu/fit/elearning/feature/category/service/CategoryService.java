package vn.edu.nlu.fit.elearning.feature.category.service;

import vn.edu.nlu.fit.elearning.feature.category.dao.CategoryDaoImpl;
import vn.edu.nlu.fit.elearning.feature.category.dao.CategoryDao;
import vn.edu.nlu.fit.elearning.feature.category.dto.CategoryDto;
import vn.edu.nlu.fit.elearning.feature.category.model.Category;

import java.util.List;

public class CategoryService implements ICategoryService {
    private CategoryDao CategoryDao;

    public CategoryService() {
        this.CategoryDao = new CategoryDaoImpl();
    }

    @Override
    public int createCategory(Category category) {
        if (category != null) {
            CategoryDao.create(category);
            return 1;
        }
        return 0;
    }

    @Override
    public List<Category> getAllCategories() {
        return CategoryDao.findAll();
    }

    @Override
    public Category getCategoryById(int id) {
        return CategoryDao.findById(id);
    }

    @Override
    public int updateCategory(Category category) {
        return CategoryDao.update(category);
    }

    @Override
    public int deleteCategory(int id) {
        return CategoryDao.delete(id);
    }

    @Override
    public List<Category> getAllCategoriesByName(String name) {
        return CategoryDao.findByName(name);
    }

    @Override
    public CategoryDto getCategoryByCourseId(int courseId) {
        return CategoryDao.getCategoryByCourseId(courseId);
    }


}
