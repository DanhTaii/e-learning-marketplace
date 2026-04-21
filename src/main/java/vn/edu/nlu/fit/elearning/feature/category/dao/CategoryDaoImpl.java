package vn.edu.nlu.fit.elearning.feature.category.dao;

import vn.edu.nlu.fit.elearning.common.database.BaseDao;
import vn.edu.nlu.fit.elearning.common.helper.pagination.filter.category.CategoryFilter;
import vn.edu.nlu.fit.elearning.feature.category.dto.CategoryDto;
import vn.edu.nlu.fit.elearning.feature.category.model.Category;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CategoryDaoImpl extends BaseDao implements CategoryDao {
    @Override
    public int create(Category entity) {
        return getJdbi().withHandle(handle ->
                handle.createUpdate("""
            INSERT INTO categories(name, slug, parent_id, icon_url, status)
            VALUES (:name, :slug, :parentId, :iconUrl, :status)
        """)
                        .bind("name", entity.getName())
                        .bind("slug", entity.getSlug())
                        .bind("parentId", entity.getParentId() == 0 ? null : entity.getParentId()) 
                        .bind("iconUrl", entity.getIconUrl())
                        .bind("status", entity.getStatus().name())
                        .execute()
        );
    }

    @Override
    public Category findById(Integer integer) {
        return getJdbi().withHandle(handle -> {
            return handle.createQuery("SELECT id, name, slug, status, parent_id, created_at, updated_at " +
                            "FROM categories WHERE id = :id")
                    .bind("id", integer)
                    .mapToBean(Category.class)
                    .findFirst()
                    .orElse(null);
        });
    }

    @Override
    public List<Category> findAll() {
        return getJdbi().withHandle(handle -> {
            return handle.createQuery("SELECT ca.id, ca.name, ca.slug, ca.parent_id, ca.status, ca.created_at\n " +
                    "FROM categories ca").mapToBean(Category.class).list();
        });
    }

    @Override
    public int update(Category entity) {
        return getJdbi().withHandle(handle -> {
            return handle.createUpdate("""
            UPDATE categories
            SET name = :name, slug = :slug, parent_id = :parentId, icon_url = :icon, status = :status
            WHERE id = :id
        """)
                    .bind("name", entity.getName())
                    .bind("slug", entity.getSlug())
                    .bind("parentId", entity.getParentId() == 0 ? null : entity.getParentId())
                    .bind("icon", entity.getIconUrl())
                    .bind("status", entity.getStatus().name())
                    .bind("id", entity.getId())
                    .execute();
        });
    }

    @Override
    public int delete(Integer integer) {
        return getJdbi().withHandle(handle -> {
            return handle.createUpdate("DELETE FROM categories WHERE id = :id").bind("id", integer).execute();
        });
    }

    @Override
    public List<Category> findByName(String name) {
        String nameSearch = "%" + name + "%";
        return getJdbi().withHandle(handle -> {
            return handle.createQuery("SELECT ca.id, ca.name, ca.parent_id, ca.icon_url\n" +
                    "FROM categories ca\n" +
                    "WHERE ca.name LIKE :nameSearch").bind("nameSearch", nameSearch).mapToBean(Category.class).list();
        });
    }

//    @Override
//    public Category findById(int id) {
//        return getJdbi().withHandle(handle -> {
//            return handle.createQuery("SELECT id, name, slug, parent_id, icon_url\n" +
//                    "FROM categories\n" +
//                    "WHERE id = :id;").bind("id", id).mapToBean(Category.class).findFirst().orElse(null);
//        });
//    }

    @Override
    public CategoryDto findCategoryByCourseId(int courseId) {
        return getJdbi().withHandle(handle -> {
            return handle.createQuery("SELECT c.id AS category_id, c.name, c.slug, c.status, cs.id AS course_id, cs.title AS course_title\n" +
                    "FROM courses cs\n" +
                    "JOIN categories c ON cs.category_id = c.id\n" +
                    "WHERE cs.id = :courseId AND c.status = 'ACTIVE';").bind("courseId", courseId).mapToBean(CategoryDto.class).findFirst().orElse(null);
        });
    }

    @Override
    public Category findBySlug(String slug) {
        return getJdbi().withHandle(handle ->
                handle.createQuery("""
                SELECT id, name, slug, parent_id, icon_url, status
                FROM categories
                WHERE slug = :slug
            """)
                        .bind("slug", slug)
                        .mapToBean(Category.class)
                        .findFirst()
                        .orElse(null)
        );
    }

    @Override
    public Category findBySlugExcludeId(String slug, int excludeId) {
        return getJdbi().withHandle(handle ->
                handle.createQuery("""
                SELECT id, name, slug, parent_id, icon_url, status
                FROM categories
                WHERE slug = :slug AND id != :id
            """)
                        .bind("slug", slug)
                        .bind("id", excludeId)
                        .mapToBean(Category.class)
                        .findFirst()
                        .orElse(null)
        );
    }

    @Override
    public List<Category> findCategoriesByFilter(CategoryFilter filter) {
        Map<String, Object> params = new HashMap<>();
        String whereClause = buildCategoryWhereClause(filter, params);

        String sql = "SELECT c.id, c.name, c.slug, c.parent_id, c.created_at, c.status " +
                "FROM categories c "
                + whereClause +
                " ORDER BY c.created_at DESC LIMIT :limit OFFSET :offset";

        return getJdbi().withHandle(handle -> {
            var query = handle.createQuery(sql);
            params.forEach(query::bind);
            query.bind("limit", filter.getSize());
            query.bind("offset", (filter.getPage() - 1) * filter.getSize());
            return query.mapToBean(Category.class).list();
        });
    }

    @Override
    public int countCategoriesByFilter(CategoryFilter filter) {
        Map<String, Object> params = new HashMap<>();
        String where = buildCategoryWhereClause(filter, params);

        String sql = "SELECT COUNT(*) FROM categories c " + where;

        return getJdbi().withHandle(handle -> {
            var query = handle.createQuery(sql);
            params.forEach(query::bind);
            return query.mapTo(Integer.class).one();
        });
    }

    private String buildCategoryWhereClause(CategoryFilter filter, Map<String, Object> params) {
        StringBuilder where = new StringBuilder(" WHERE 1=1");

        // 🔍 Tìm theo tên
        if (filter.getName() != null && !filter.getName().trim().isEmpty()) {
            where.append(" AND c.name LIKE :nameSearch");
            params.put("nameSearch", "%" + filter.getName().trim() + "%");
        }

        if (filter.getSlug() != null && !filter.getSlug().trim().isEmpty()) {
            where.append(" AND c.slug LIKE :slugSearch");
            params.put("slugSearch", "%" + filter.getSlug().trim() + "%");
        }

        if (filter.getParentId() != null && filter.getParentId() >= 0) {
            where.append(" AND c.parent_id = :parentId");
            params.put("parentId", filter.getParentId());
        }

        if (filter.getFromDate() != null) {
            where.append(" AND c.created_at >= :fromDate");
            params.put("fromDate", filter.getFromDate());
        }

        if (filter.getToDate() != null) {
            where.append(" AND c.created_at <= :toDate");
            params.put("toDate", filter.getToDate());
        }

        if (filter.getStatus() != null) {
            where.append(" AND c.status = :status");
            params.put("status", filter.getStatus().name());
        }

        return where.toString();
    }


}
