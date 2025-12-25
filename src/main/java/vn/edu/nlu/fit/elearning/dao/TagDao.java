package vn.edu.nlu.fit.elearning.dao;

import vn.edu.nlu.fit.elearning.model.Category;
import vn.edu.nlu.fit.elearning.model.Tag;

import java.util.List;

public class TagDao extends BaseDao implements BaseCrudDao<Tag, Integer> {
    @Override
    public int create(Tag entity) {
        String sql = "INSERT INTO Tags (name,slug)\n" +
                "VALUES (:name,:slug)";
        return getJdbi().withHandle(handle -> {
            return handle.createUpdate(sql)
                    .bindBean(entity)
                    .execute();
        });
    }

    @Override
    public Tag findById(Integer integer) {
        return null;
    }

    public List<Tag> findByName(String name) {
        String nameSearch = "%" + name + "%";
        return getJdbi().withHandle(handle -> {
            return handle.createQuery("SELECT t.name, t.slug, COUNT(ct.course_id) AS course_count, t.created_at " +
                            "FROM Tags t " +
                            "LEFT JOIN Course_Tags ct ON t.id = ct.tag_id " +
                            "WHERE t.name LIKE :nameSearch " +
                            "GROUP BY t.id")
                    .bind("nameSearch", nameSearch).mapToBean(Tag.class).list();
        });
    }

    @Override
    public List<Tag> findAll() {
        return getJdbi().withHandle(handle -> {
            return handle.createQuery("SELECT t.id ,t.name, t.slug, COUNT(ct.course_id) AS course_count, t.created_at" +
                    " FROM Tags t LEFT JOIN Course_Tags ct ON t.id = ct.tag_id" +
                    " GROUP BY t.id;").mapToBean(Tag.class).list();
        });
    }

    @Override
    public int update(Tag entity) {
        return 0;
    }

    @Override
    public int delete(Integer tagId) {
        String sql = "DELETE FROM Tags WHERE id = :id ";
        return getJdbi().withHandle(handle -> {
            return handle.createUpdate(sql)
                    .bind("id", tagId)
                    .execute();
        });
    }
}
