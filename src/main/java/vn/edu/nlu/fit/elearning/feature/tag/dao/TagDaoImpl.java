package vn.edu.nlu.fit.elearning.feature.tag.dao;

import vn.edu.nlu.fit.elearning.common.database.BaseDao;
import vn.edu.nlu.fit.elearning.common.helper.pagination.filter.tag.TagFilter;
import vn.edu.nlu.fit.elearning.feature.category.model.Category;
import vn.edu.nlu.fit.elearning.feature.tag.dto.TagDto;
import vn.edu.nlu.fit.elearning.feature.tag.model.Tag;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TagDaoImpl extends BaseDao implements TagDao {
    @Override
    public int create(Tag entity) {
        return getJdbi().withHandle(handle ->
                handle.createUpdate("""
            INSERT INTO tags(name, slug, status)
            VALUES (:name, :slug, :status)
        """)
                        .bind("name", entity.getName())
                        .bind("slug", entity.getSlug())
                        .bind("status", entity.getStatus().name())
                        .execute()
        );
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


    @Override
    public List<Tag> findByName(String name) {
        String nameSearch = "%" + name + "%";
        return getJdbi().withHandle(handle -> {
            return handle.createQuery("SELECT t.id, t.name, t.slug, t.status, COUNT(ct.course_id) AS course_count, t.created_at " +
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
            return handle.createQuery("SELECT t.id ,t.name, t.slug, t.status, COUNT(ct.course_id) AS course_count, t.created_at" +
                    " FROM tags t LEFT JOIN course_tags ct ON t.id = ct.tag_id" +
                    " GROUP BY t.id;").mapToBean(Tag.class).list();
        });
    }

    @Override
    public List<Tag> findTags(TagFilter filter) {

        StringBuilder sql = new StringBuilder("""
        SELECT t.id, t.name, t.slug, t.status,
               COUNT(ct.course_id) AS course_count, t.created_at
        FROM tags t
        LEFT JOIN course_tags ct ON t.id = ct.tag_id
        WHERE 1=1
    """);

        Map<String, Object> params = new HashMap<>();

        if (filter.getName() != null && !filter.getName().trim().isEmpty()) {
            sql.append(" AND t.name LIKE :name");
            params.put("name", "%" + filter.getName().trim() + "%");
        }

        if (filter.getSlug() != null && !filter.getSlug().trim().isEmpty()) {
            sql.append(" AND t.slug LIKE :slug");
            params.put("slug", "%" + filter.getSlug().trim() + "%");
        }

        if (filter.getStatus() != null) {
            sql.append(" AND t.status = :status");
            params.put("status", filter.getStatus().name());
        }

        sql.append(" GROUP BY t.id, t.name, t.slug, t.status, t.created_at");
        sql.append(" ORDER BY t.created_at DESC");
        sql.append(" LIMIT :limit OFFSET :offset");

        return getJdbi().withHandle(handle -> {
            var query = handle.createQuery(sql.toString());

            params.forEach(query::bind);

            query.bind("limit", filter.getSize());
            query.bind("offset", (filter.getPage() - 1) * filter.getSize());

            return query.mapToBean(Tag.class).list();
        });
    }

    @Override
    public int countTags() {
        String sql = "SELECT COUNT(*) FROM tags";
        return getJdbi().withHandle(handle ->
                handle.createQuery(sql)
                        .mapTo(Integer.class)
                        .one()
        );
    }




    @Override
    public int update(Tag entity) {
        String sql = """
        UPDATE tags
        SET name = :name,
            slug = :slug,
            status = :status
        WHERE id = :id
    """;
        return getJdbi().withHandle(handle ->
                handle.createUpdate(sql)
                        .bind("name", entity.getName())
                        .bind("slug", entity.getSlug())
                        .bind("status", entity.getStatus().name())
                        .bind("id", entity.getId())
                        .execute()
        );
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

    @Override
    public List<TagDto> findTagsByCourseId(int courseId) {
        return getJdbi().withHandle(handle ->
                handle.createQuery(
                                "SELECT t.id AS id, " +
                                        "       t.name AS name, " +
                                        "       t.slug AS slug, " +
                                        "       t.status AS status, " +
                                        "       ct.course_id AS courseId " +
                                        "FROM course_tags ct " +
                                        "JOIN tags t ON ct.tag_id = t.id " +
                                        "WHERE t.status = 'ACTIVE' AND ct.course_id = :courseId"
                        )
                        .bind("courseId", courseId)
                        .mapToBean(TagDto.class)
                        .list()
        );
    }

    @Override
    public Tag findBySlug(String slug) {
        return getJdbi().withHandle(handle ->
                handle.createQuery("""
            SELECT id, name, slug, status
            FROM tags
            WHERE slug = :slug
        """)
                        .bind("slug", slug)
                        .mapToBean(Tag.class)
                        .findFirst()
                        .orElse(null)
        );
    }

    @Override
    public Tag findBySlugExcludeId(String slug, int excludeId) {
        return getJdbi().withHandle(handle ->
                handle.createQuery("""
            SELECT id, name, slug, status
            FROM tags
            WHERE slug = :slug AND id != :id
        """)
                        .bind("slug", slug)
                        .bind("id", excludeId)
                        .mapToBean(Tag.class)
                        .findFirst()
                        .orElse(null)
        );
    }

}
