package vn.edu.nlu.fit.elearning.dao;

import vn.edu.nlu.fit.elearning.model.Tag;

import java.util.List;

public class TagDao extends BaseDao implements BaseCrudDao<Tag, Integer> {
    @Override
    public void create(Tag entity) {
        String sql = "INSERT INTO Tags (name,slug)\n" +
                "VALUES (:name,:slug)";
        getJdbi().useHandle(handle -> {
            handle.prepareBatch(sql)
                    .bindBean(entity).add()
                    .execute();
        });
    }

    @Override
    public Tag findById(Integer integer) {
        return null;
    }

    @Override
    public List<Tag> findAll() {
        return getJdbi().withHandle(handle -> {
            return handle.createQuery("SELECT t.name, t.slug, COUNT(ct.course_id) AS course_count, t.created_at" +
                    " FROM Tags t LEFT JOIN Course_Tags ct ON t.id = ct.tag_id" +
                    " GROUP BY t.id;").mapToBean(Tag.class).list();
        });
    }

    @Override
    public int update(Tag entity) {
        return 0;
    }

    @Override
    public int delete(Integer integer) {
        return 0;
    }
}
