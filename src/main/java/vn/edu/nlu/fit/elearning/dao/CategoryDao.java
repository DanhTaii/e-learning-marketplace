package vn.edu.nlu.fit.elearning.dao;

import org.jdbi.v3.core.Jdbi;
import org.jdbi.v3.core.statement.PreparedBatch;
import vn.edu.nlu.fit.elearning.model.Category;
import vn.edu.nlu.fit.elearning.model.Course;

import java.util.List;

public class CategoryDao extends BaseDao implements BaseCrudDao<Category, Integer> {
    @Override
    public void create(Category entity) {
        getJdbi().useHandle(handle -> {
            PreparedBatch pb = handle.prepareBatch("INSERT INTO categories(id, name, slug, icon_url )\n" +
                    "VALUES ( :id,:name, :slug, :iconUrl )").bindBean(entity).add();
            pb.execute();
        });
    }

    @Override
    public Category findById(Integer integer) {
        return null;
    }

    @Override
    public List<Category> findAll() {
        return getJdbi().withHandle(handle -> {
            return handle.createQuery("SELECT ca.id, ca.name, ca.parent_id, ca.icon_url " +
                    "FROM categories ca").mapToBean(Category.class).list();
        });
    }

    @Override
    public int update(Category entity) {
        return 0;
    }

    @Override
    public int delete(Integer integer) {
        return 0;
    }

    public List<Category> findByName(String name) {
        String nameSearch = "%" + name + "%";
        return getJdbi().withHandle(handle -> {
            return handle.createQuery("SELECT ca.id, ca.name, ca.parent_id, ca.icon_url\n" +
                    "FROM categories ca\n" +
                    "WHERE ca.name LIKE :nameSearch").bind("nameSearch", nameSearch).mapToBean(Category.class).list();
        });
    }

    public Category findById(int id) {
        return getJdbi().withHandle(handle -> {
            return handle.createQuery("SELECT id, name, slug, parent_id, icon_url\n" +
                    "FROM Categories\n" +
                    "WHERE id = :id;").bind("id", id).mapToBean(Category.class).one();
        });
    }

}
