package vn.edu.nlu.fit.elearning.dao;

import vn.edu.nlu.fit.elearning.model.Category;

import java.util.List;

public class CategoryDao extends BaseDao implements BaseCrudDao<Category, Integer> {
    @Override
    public void create(Category entity) {

    }

    @Override
    public Category findById(Integer integer) {
        return null;
    }

    @Override
    public List<Category> findAll() {
        return List.of();
    }

    @Override
    public int update(Category entity) {
        return 0;
    }

    @Override
    public int delete(Integer integer) {
        return 0;
    }
}
