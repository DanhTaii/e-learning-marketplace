package vn.edu.nlu.fit.elearning.feature.category.dao;

import vn.edu.nlu.fit.elearning.common.database.BaseDao;
import vn.edu.nlu.fit.elearning.feature.category.dto.CategoryDto;
import vn.edu.nlu.fit.elearning.feature.category.model.Category;
import java.util.List;

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
            return handle.createQuery("SELECT id, name, slug, parent_id, icon_url FROM categories WHERE id = :id")
                    .bind("id", integer)
                    .mapToBean(Category.class)
                    .findFirst()
                    .orElse(null);
        });
    }

    @Override
    public List<Category> findAll() {
        return getJdbi().withHandle(handle -> {
            return handle.createQuery("SELECT ca.id, ca.name, ca.parent_id, ca.icon_url, ca.created_at\n " +
                    "FROM categories ca").mapToBean(Category.class).list();
        });
    }

    @Override
    public int update(Category entity) {
        return getJdbi().withHandle(handle -> {
            return handle.createUpdate("UPDATE categories\n" +
                            "SET name = :name, slug = :slug, parent_id = :parentId, icon_url = :icon, status = :status \n" +
                            "WHERE id = :id")
                    .bind("name", entity.getName())
                    .bind("slug", entity.getSlug())
                    .bind("parentId", entity.getParentId())
                    .bind("icon", entity.getIconUrl())
                    .bind("id", entity.getId())
                    .bind("status", entity.getStatus())
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
    public boolean existsByName(String name) {
        return getJdbi().withHandle(handle ->
                handle.createQuery("""
                SELECT COUNT(*) FROM categories WHERE name = :name
            """)
                        .bind("name", name)
                        .mapTo(int.class)
                        .one() > 0
        );
    }

    @Override
    public boolean existsBySlug(String slug) {
        return getJdbi().withHandle(handle ->
                handle.createQuery("""
                SELECT COUNT(*) FROM categories WHERE slug = :slug
            """)
                        .bind("slug", slug)
                        .mapTo(int.class)
                        .one() > 0
        );
    }


}
