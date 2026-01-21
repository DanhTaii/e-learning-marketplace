package vn.edu.nlu.fit.elearning.dao;

import vn.edu.nlu.fit.elearning.dto.TagDto;
import vn.edu.nlu.fit.elearning.model.Category;
import vn.edu.nlu.fit.elearning.model.Tag;
import vn.edu.nlu.fit.elearning.model.User;

import java.util.List;

public class TagDao extends BaseDao implements BaseCrudDao<Tag, Integer> {
    @Override
    public int create(Tag entity) {
        String sql = "INSERT INTO tags (name,slug)\n" +
                "VALUES (:name,:slug)";
        return getJdbi().withHandle(handle -> {
            return handle.createUpdate(sql)
                    .bindBean(entity)
                    .execute();
        });
    }

    @Override
    public Tag findById(Integer integer) {
        return getJdbi().withHandle(handle -> {
            return handle.createQuery("select * from tags t where t.id = :id")
                    .bind("id", integer)
                    .mapToBean(Tag.class)
                    .findFirst()
                    .orElse(null);
        });
    }


    public List<Tag> findByName(String name) {
        String nameSearch = "%" + name + "%";
        return getJdbi().withHandle(handle -> {
            return handle.createQuery("SELECT t.id, t.name, t.slug, COUNT(ct.course_id) AS course_count, t.created_at " +
                            "FROM tags t " +
                            "LEFT JOIN course_tags ct ON t.id = ct.tag_id " +
                            "WHERE t.name LIKE :nameSearch " +
                            "GROUP BY t.id")
                    .bind("nameSearch", nameSearch).mapToBean(Tag.class).list();
        });
    }

    @Override
    public List<Tag> findAll() {
        return getJdbi().withHandle(handle -> {
            return handle.createQuery("SELECT t.id ,t.name, t.slug, COUNT(ct.course_id) AS course_count, t.created_at" +
                    " FROM tags t LEFT JOIN course_tags ct ON t.id = ct.tag_id" +
                    " GROUP BY t.id;").mapToBean(Tag.class).list();
        });
    }

    @Override
    public int update(Tag entity) {
        String sql = "UPDATE tags \n" +
                "SET name= :name , slug = :slug \n" +
                "WHERE id = :id";
      return  getJdbi().withHandle(handle -> {
            return handle.createUpdate(sql)
                    .bind("name",entity.getName())
                    .bind("slug",entity.getSlug())
                    .bind("id",entity.getId())
                    .execute();

        });
    }

    @Override
    public int delete(Integer tagId) {
        String sql = "DELETE FROM tags WHERE id = :id ";
        return getJdbi().withHandle(handle -> {
            return handle.createUpdate(sql)
                    .bind("id", tagId)
                    .execute();
        });
    }

    public List<TagDto> findTagsByCourseId(int courseId) {
        return getJdbi().withHandle(handle -> {
            return handle.createQuery("SELECT t.id, t.name, t.slug, t.status, ct.course_id\n" +
                    "FROM course_tags ct\n" +
                    "JOIN tags t ON ct.tag_id = t.id\n" +
                    "WHERE t.status = 'ACTIVE' AND ct.course_id = :courseId;").bind("courseId",courseId).mapToBean(TagDto.class).list();
        });
    }

}
