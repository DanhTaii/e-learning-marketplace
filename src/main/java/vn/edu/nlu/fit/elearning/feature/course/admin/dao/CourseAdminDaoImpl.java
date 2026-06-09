package vn.edu.nlu.fit.elearning.feature.course.admin.dao;

import org.jdbi.v3.core.Handle;
import org.jdbi.v3.core.statement.PreparedBatch;
import vn.edu.nlu.fit.elearning.common.database.BaseDao;
import vn.edu.nlu.fit.elearning.common.helper.pagination.filter.course.CourseArchivedFilter;
import vn.edu.nlu.fit.elearning.common.utils.StringUtils;
import vn.edu.nlu.fit.elearning.feature.course.admin.dto.CourseAdminDto;
import vn.edu.nlu.fit.elearning.feature.course.admin.dto.CourseArchive;
import vn.edu.nlu.fit.elearning.feature.course.student.dto.CourseDetailDto;
import vn.edu.nlu.fit.elearning.feature.course.common.model.Course;
import vn.edu.nlu.fit.elearning.common.helper.pagination.filter.course.CourseFilter;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CourseAdminDaoImpl extends BaseDao implements CourseAdminDao {

    @Override
    public int create(Course entity) {
        return getJdbi().withHandle(handle -> {
            return handle.createUpdate("INSERT INTO courses( id, title, subtitle, level, goals, description, price, discount_price, thumbnail_url, is_public, category_id, author_name)\n" +
                            "VALUES (:id, :title,  :subtitle,  :level,  :goals ,  :description, :price, :discountPrice, :thumbnailUrl, :isPublic, :categoryId, :authorName)")
                    .bindBean(entity)
                    .executeAndReturnGeneratedKeys("id")
                    .mapTo(Integer.class)
                    .one();
        });
    }

    @Override
    public Course findById(Integer integer) {
        return getJdbi().withHandle(handle -> {
                    return handle.createQuery("SELECT * FROM courses c WHERE c.id = :id ")
                            .bind("id", integer)
                            .mapToBean(Course.class)
                            .findFirst()
                            .orElse(null);
                }
        );
    }

    @Override
    public List<Course> findAll() {
        return getJdbi().withHandle(handle -> {
            return handle.createQuery("SELECT c.id, c.title, " +
                    "c.thumbnail_url, " +
//                    "c.level, " +
//                    "COALESCE((SELECT SUM(duration_minutes) FROM lessons WHERE course_id = c.id),0) / 60.0 AS durationHours, " +
//                    "c.author_name, " +
                    "c.discount_price, " +
                    "c.price, " +
//                    "c.created_at, " +
                    "c.is_public\n" +
                    "FROM courses c\n" +
//                    "LEFT JOIN lessons l ON c.id = l.course_id\n" +
//                    "WHERE c.is_public = TRUE\n" +
                    "GROUP BY c.id\n" +
                    "ORDER BY c.id DESC;").mapToBean(Course.class).list();
        });
    }

    @Override
    public int update(Course entity) {
        return getJdbi().withHandle(handle -> {
            return handle.createUpdate("UPDATE courses\n" +
                            "SET\n" +
                            "    title = :title,\n" +
                            "    subtitle = :subtitle,\n" +
                            "    level = :level,\n" +
                            "    goals = :goals,\n" +
                            "    description = :description,\n" +
                            "    price = :price,\n" +
                            "    discount_price = :discountPrice,\n" +
                            "    thumbnail_url = :thumbnailUrl,\n" +
                            "    is_public = :isPublic,\n" +
                            "    category_id = :categoryId\n" +
                            "WHERE id = :id")
                    .bindBean(entity)
                    .execute();
        });
    }

    @Override
    public int deleteById(Integer integer) {
        return getJdbi().withHandle(handle -> {
            return handle.createUpdate("DELETE FROM courses WHERE id = :id")
                    .bind("id", integer)
                    .execute();
        });
    }

    @Override
    public CourseDetailDto findCourseByIdForDetail(int id, int userId) {
        return getJdbi().withHandle(handle -> {
            return handle.createQuery("SELECT c.id, c.title, c.subtitle, c.description, c.goals, c.level, c.price, c.discount_price, \n" +
                            "c.thumbnail_url, c.is_public, c.author_name, c.created_at, c.updated_at, cat.id AS categoryId, \n" +
                            "COALESCE((SELECT SUM(duration_minutes) FROM lessons WHERE course_id = c.id),0) / 60.0 AS durationHours, \n" +
                            "(CASE WHEN :userId > 0 AND w.course_id IS NOT NULL THEN TRUE ELSE FALSE END) as inWishlist,\n" +
                            "(CASE WHEN :userId > 0 AND e.course_id IS NOT NULL THEN TRUE ELSE FALSE END)as enrolled, \n" +
                            "(SELECT COUNT(*) FROM lessons WHERE course_id = c.id) AS lessonCount,\n" +
                            "COALESCE(AVG(r.rating)) AS avgRating, " +
                            "COUNT(DISTINCT r.id) AS reviewCount\n" +
                            "FROM courses c\n" +
                            "LEFT JOIN categories cat ON c.category_id = cat.id\n" +
                            "LEFT JOIN reviews r ON r.course_id = c.id\n" +
                            "LEFT JOIN wishlist w ON w.course_id = c.id AND w.user_id = :userId\n" +
                            "LEFT JOIN enrollments e ON e.course_id = c.id AND e.user_id = :userId\n" +
                            "WHERE c.is_public = TRUE AND c.id = :id\n" +
                            "GROUP BY c.id, cat.id")
                    .bind("id", id)
                    .bind("userId", userId)
                    .mapToBean(CourseDetailDto.class)
                    .findFirst()
                    .orElse(null);

        });
    }

    String sql = "SELECT c.id, c.title, c.level, c.price, c.is_public, c.created_at, cate.name AS categoryName, " +
            "(SELECT COUNT(*) FROM enrollments WHERE course_id = c.id) AS studentCount, " +
            "COALESCE((SELECT SUM(duration_minutes) FROM lessons WHERE course_id = c.id),0) / 60.0 AS duration_hours " +
            "FROM courses c " +
            "LEFT JOIN categories cate ON c.category_id = cate.id ";

    @Override
    public List<CourseAdminDto> findByFilter(CourseFilter filter) {
        Map<String, Object> params = new HashMap<>();
        String whereClause = bulidWhereClause(filter, params);

        String finalResult = sql + whereClause + " ORDER BY c.id DESC LIMIT :limit OFFSET :offset";

        return getJdbi().withHandle(handle -> {

            var query = handle.createQuery(finalResult);
            params.forEach(query::bind);
            query.bind("limit", filter.getLimit());
            query.bind("offset", filter.getOffSet());

            return query.mapToBean(CourseAdminDto.class).list();
        });
    }

    @Override
    public int countByFilter(CourseFilter filter) {
        Map<String, Object> params = new HashMap<>();
        String whereClause = bulidWhereClause(filter, params);
        String finalSql = "SELECT COUNT(*) FROM courses c " + whereClause;

        return getJdbi().withHandle(handle -> {
            var query = handle.createQuery(finalSql);
            params.forEach(query::bind);
            return query.mapTo(Integer.class).one();
        });
    }

    private String bulidWhereClause(CourseFilter filter, Map<String, Object> params) {
        StringBuilder conditionalSentence = new StringBuilder(" WHERE 1=1");

        //(Public hay All)
        if (filter.getIsPublic() != null) {
            conditionalSentence.append(" AND c.is_public = :isPublic");
            params.put("isPublic", filter.getIsPublic());
        }

        // Danh mục
        if (filter.getCategoryId() != null && filter.getCategoryId() > 0) {
            conditionalSentence.append(" AND c.category_id = :catId");
            params.put("catId", filter.getCategoryId());
        }

        // Tìm kiếm theo tên
        if (filter.getTitle() != null && !filter.getTitle().isEmpty()) {
            conditionalSentence.append(" AND c.title LIKE :title");
            params.put("title", "%" + StringUtils.escapeLikeWildcards(filter.getTitle()) + "%");
        }

        // Kiếm theo cấp độ
        if (filter.getLevel() != null && !filter.getLevel().isEmpty()) {
            conditionalSentence.append(" AND c.level = :level");
            params.put("level", filter.getLevel());
        }

        //Bắt đầu từ ngày
        if (filter.getFromDate() != null) {
            conditionalSentence.append(" AND c.created_at >= :fromDate");
            params.put("fromDate", filter.getFromDate());
        }

        //Đến ngày
        if (filter.getToDate() != null) {
            conditionalSentence.append(" AND c.created_at <= :toDate");
            params.put("toDate", filter.getToDate());
        }

        // Khoảng giá (Sử dụng cột tính toán giá sau giảm)
        if ("under500".equals(filter.getPriceRange())) {
            conditionalSentence.append(" AND (c.price - COALESCE(c.discount_price, 0)) < 500000");
        }

        conditionalSentence.append(" AND c.is_deleted = 0");

        return conditionalSentence.toString();
    }

    @Override
    public int countAll() {
        return getJdbi().withHandle(handle -> {
            return handle.createQuery("SELECT COUNT(*) FROM courses WHERE is_deleted = 0")
                    .mapTo(Integer.class)
                    .one();
        });
    }

    @Override
    public int deleteByIds(List<Integer> ids) {
        if (ids == null || ids.isEmpty()) {
            return 0;
        }

        return getJdbi().withHandle(handle -> {
            return handle.createUpdate("DELETE FROM courses WHERE id IN (<ids>)")
                    .bindList("ids", ids)
                    .execute();
        });
    }

    @Override
    public int updateStatusByIds(List<Integer> ids) {
        if (ids == null || ids.isEmpty()) {
            return 0;
        }

        return getJdbi().withHandle(handle -> {
            return handle.createUpdate("UPDATE courses " +
                            "SET is_public = CASE WHEN is_public = 1 THEN 0 " +
                            "ELSE 1 END " +
                            "WHERE id IN (<ids>) ")
                    .bindList("ids", ids)
                    .execute();
        });
    }

    @Override
    public int countArchived() {
        return getJdbi().withHandle(handle -> {
            return handle.createQuery("SELECT COUNT(*) FROM courses WHERE is_deleted = 1")
                    .mapTo(Integer.class)
                    .one();
        });
    }

    @Override
    public int archiveByIds(List<Integer> ids, String deleteReason) {
        if (ids == null || ids.isEmpty()) return 0;

        return getJdbi().inTransaction(handle -> {
            int updatedCount = handle.createUpdate(
                            "UPDATE courses SET is_deleted = 1, deleted_at = NOW(), delete_reason = :reason " +
                                    "WHERE id IN (<ids>) AND is_deleted = 0")
                    .bindList("ids", ids)
                    .bind("reason", deleteReason)
                    .execute();

            if (updatedCount > 0) {
                handle.createUpdate(
                                "UPDATE lessons SET is_deleted = 1, deleted_at = NOW(), delete_reason = :reason " +
                                        "WHERE course_id IN (<ids>) AND is_deleted = 0")
                        .bindList("ids", ids)
                        .bind("reason", deleteReason)
                        .execute();
            }
            return updatedCount;
        });
    }

    @Override
    public List<CourseArchive> findArchivedByFilter(CourseArchivedFilter filter) {
        Map<String, Object> params = new HashMap<>();
        String whereClause = buildArchivedWhereClause(filter, params);

        String sqlArchived = "SELECT c.id, c.title, cate.name AS category_name, c.deleted_at, c.delete_reason " +
                "FROM courses c LEFT JOIN categories cate ON cate.id = c.category_id"
                + whereClause
                + " ORDER BY c.deleted_at DESC LIMIT :limit OFFSET :offset";

        return getJdbi().withHandle(handle -> {
            var query = handle.createQuery(sqlArchived);
            params.forEach(query::bind);
            query.bind("limit", filter.getSize());
            query.bind("offset", (filter.getPage() - 1) * filter.getSize());
            return query.mapToBean(CourseArchive.class).list();
        });
    }

    @Override
    public int countArchivedByFilter(CourseArchivedFilter filter) {
        Map<String, Object> params = new HashMap<>();
        String where = buildArchivedWhereClause(filter, params);

        String sqlCountArchived = "SELECT COUNT(*) FROM courses c" + where;

        return getJdbi().withHandle(handle -> {
            var query = handle.createQuery(sqlCountArchived);
            params.forEach(query::bind);
            return query.mapTo(Integer.class).one();
        });
    }

    private String buildArchivedWhereClause(CourseArchivedFilter filter, Map<String, Object> params) {
        StringBuilder where = new StringBuilder(" WHERE 1=1");

        if (filter.getTitle() != null && !filter.getTitle().trim().isEmpty()) {
            where.append(" AND c.title LIKE :nameSearch");
            params.put("nameSearch", "%" + filter.getTitle().trim() + "%");
        }

        if (filter.getCategoryId() > 0) {
            where.append(" AND c.category_id = :categoryIdSearch");
            params.put("categoryIdSearch", filter.getCategoryId());
        }

        if (filter.getDeletedFromDate() != null) {
            where.append(" AND c.deleted_at >= :fromDate");
            params.put("fromDate", filter.getDeletedFromDate());
        }

        if (filter.getDeletedToDate() != null) {
            where.append(" AND c.deleted_at <= :toDate");
            params.put("toDate", filter.getDeletedToDate());
        }

        where.append(" AND c.is_deleted = 1");

        return where.toString();
    }

    @Override
    public int restoreByIds(List<Integer> ids) {
        return getJdbi().withHandle(handle -> {
            return handle.createUpdate("UPDATE courses " +
                            "SET is_deleted = 0," +
                            "delete_reason = NULL," +
                            "deleted_at = NULL " +
                            "WHERE id IN (<ids>)")
                    .bindList("ids", ids)
                    .execute();
        });
    }

    @Override
    public int countCoursesByTimeRange(String timeRange) {
        String timeCondition = buildTimeCondition(timeRange, "created_at");
        String sql = "SELECT COUNT(id) FROM courses WHERE " + timeCondition;

        return getJdbi().withHandle(handle -> {
            return handle.createQuery(sql)
                    .mapTo(Integer.class)
                    .findFirst()
                    .orElse(0);
        });
    }

    public int createList(List<Course> courses) {
        if (courses == null || courses.isEmpty()) {
            return 0;
        }

        String sql = "INSERT INTO courses (title, subtitle, price, discount_price, level, category_id, is_public) " +
                "VALUES (:title, :subtitle, :price, :discountPrice, :level, :categoryId, :isPublic)";

        try {
            return getJdbi().withHandle(handle -> {
                PreparedBatch batch = handle.prepareBatch(sql);

                for (Course course : courses) {
                    // bindBean sẽ tự động map các thuộc tính của object Course
                    // với các tham số trong câu SQL (:title, :subtitle, ...)
                    // thông qua các hàm getter (getTitle(), getSubtitle(), ...)
                    batch.bindBean(course).add();
                }

                // Thực thi toàn bộ batch
                int[] insertedRows = batch.execute();

                System.out.println("Batch insert completed. Total rows affected: " + insertedRows.length);
                return insertedRows.length;
            });

        } catch (Exception e) {
            System.err.println("Error during batch insert of courses.");
            e.printStackTrace();
            return 0;
        }
    }
}
