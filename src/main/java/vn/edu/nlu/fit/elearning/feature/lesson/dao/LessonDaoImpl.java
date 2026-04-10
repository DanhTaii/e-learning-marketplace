package vn.edu.nlu.fit.elearning.feature.lesson.dao;

import vn.edu.nlu.fit.elearning.common.database.BaseDao;
import vn.edu.nlu.fit.elearning.common.helper.pagination.filter.lesson.LessonFilter;
import vn.edu.nlu.fit.elearning.feature.lesson.model.Lesson;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LessonDaoImpl extends BaseDao implements LessonDao {
//    =========================================== BASIC CRUD ===========================================
    @Override
    public int create(Lesson entity) {
        String sql = "INSERT INTO lessons (course_id , title, video_url, duration_minutes, order_index) \n" +
                "VALUES (:courseId, :title , :videoUrl , :durationMinutes, " +
                "(SELECT COALESCE(MAX(order_index), 0) + 1 FROM lessons l WHERE l.course_id = :courseId))";
        return getJdbi().withHandle(handle -> {
            return handle.createUpdate(sql)
                    .bindBean(entity)
                    .executeAndReturnGeneratedKeys("id")
                    .mapTo(Integer.class)
                    .one();
        });
    }

    @Override
    public Lesson findById(Integer integer) {
        return getJdbi().withHandle(handle -> {
            return handle.createQuery("select * from lessons l where l.id = :id")
                    .bind("id", integer)
                    .mapToBean(Lesson.class)
                    .findFirst()
                    .orElse(null);
        });
    }

    @Override
    public List<Lesson> findAll() {
        return getJdbi().withHandle(handle -> {
            return handle.createQuery("SELECT l.id, l.title, l.order_index, c.title AS course_title, l.video_url, l.duration_minutes, l.created_at\n" +
                    "FROM lessons l JOIN courses c ON l.course_id = c.id\n" +
                    "ORDER BY l.order_index ASC;").mapToBean(Lesson.class).list();
        });
    }

    @Override
    public int update(Lesson entity) {
        String sql = "UPDATE lessons \n" +
                "SET course_id=:courseId ,title= :title , video_url = :videoUrl, duration_minutes = :durationMinutes \n" +
                "WHERE id = :id";
        return getJdbi().withHandle(handle -> {
            return handle.createUpdate(sql)
                    .bind("courseId", entity.getCourseId())
                    .bind("title", entity.getTitle())
                    .bind("videoURL", entity.getVideoUrl())
                    .bind("durationMinutes", entity.getDurationMinutes())
                    .execute();

        });
    }

    @Override
    public int delete(Integer lessonId) {
        String sql = "DELETE FROM lessons WHERE id = :id ";
        return getJdbi().withHandle(handle -> {
            return handle.createUpdate(sql)
                    .bind("id", lessonId)
                    .execute();
        });
    }

//    =========================================== OTHERS METHODS ===========================================

    @Override
    public List<Lesson> findByName(String name) {
        String nameSearch = "%" + name + "%";
        return getJdbi().withHandle(handle -> {
            return handle.createQuery("SELECT l.title, l.order_index, l.duration_minutes, l.created_at " +
                            "FROM lessons l " +
                            "WHERE l.title LIKE :nameSearch " +
                            "GROUP BY l.id")
                    .bind("nameSearch", nameSearch).mapToBean(Lesson.class).list();
        });
    }

    @Override
    public boolean checkExists(String title, int courseId) {
        String sql = "SELECT count(*) FROM lessons WHERE title = :title AND course_id = :courseId";
        return getJdbi().withHandle(handle -> {
            return handle.createQuery(sql)
                    .bind("title", title)
                    .bind("courseId", courseId)
                    .mapTo(Integer.class)
                    .one() > 0;


        });
    }

    @Override
    public int updateWithReorder(Lesson lesson, int oldOrderIndex, int oldCourseId) {
        return getJdbi().withHandle(handle -> {
            return handle.inTransaction(h -> {
                int newCourseId = lesson.getCourseId();
                int currentId = lesson.getId();
                int finalOrder;

                if (newCourseId == oldCourseId) {
                    // --- KỊCH BẢN 1: CÙNG KHÓA HỌC (Giữ nguyên logic cũ) ---
                    finalOrder = lesson.getOrderIndex(); // Lấy số người dùng nhập
                    if (finalOrder < oldOrderIndex) {
                        h.createUpdate("UPDATE lessons SET order_index = order_index + 1 WHERE course_id = :courseId AND order_index >= :newOrder AND order_index < :oldOrder AND id != :id")
                                .bind("courseId", newCourseId).bind("newOrder", finalOrder).bind("oldOrder", oldOrderIndex).bind("id", currentId).execute();
                    } else if (finalOrder > oldOrderIndex) {
                        h.createUpdate("UPDATE lessons SET order_index = order_index - 1 WHERE course_id = :courseId AND order_index > :oldOrder AND order_index <= :newOrder AND id != :id")
                                .bind("courseId", newCourseId).bind("oldOrder", oldOrderIndex).bind("newOrder", finalOrder).bind("id", currentId).execute();
                    }
                } else {
                    // --- KỊCH BẢN 2: CHUYỂN KHÓA HỌC (Logic "Xếp cuối hàng") ---

                    // Bước 1: Tại khóa cũ (A) - Lấp lỗ hổng
                    h.createUpdate("UPDATE lessons SET order_index = order_index - 1 WHERE course_id = :oldCourseId AND order_index > :oldOrder")
                            .bind("oldCourseId", oldCourseId)
                            .bind("oldOrder", oldOrderIndex)
                            .execute();

                    // Bước 2: Tại khóa mới (B) - Tìm số thứ tự lớn nhất hiện tại
                    Integer maxOrder = h.createQuery("SELECT MAX(order_index) FROM lessons WHERE course_id = :newCourseId")
                            .bind("newCourseId", newCourseId)
                            .mapTo(Integer.class)
                            .findOne()
                            .orElse(0); // Nếu khóa mới chưa có bài nào thì bắt đầu từ 0

                    finalOrder = maxOrder + 1; // Bài học mới sẽ có số thứ tự là MAX + 1
                }

                // Bước 3: Cập nhật thông tin bài học với finalOrder đã tính toán
                return h.createUpdate("UPDATE lessons SET course_id = :courseId, title = :title, video_url = :videoUrl, duration_minutes = :durationMinutes, order_index = :orderIndex WHERE id = :id")
                        .bind("courseId", newCourseId)
                        .bind("title", lesson.getTitle())
                        .bind("videoUrl", lesson.getVideoUrl())
                        .bind("durationMinutes", lesson.getDurationMinutes())
                        .bind("orderIndex", finalOrder) // Sử dụng số thứ tự cuối cùng nếu đổi khóa
                        .bind("id", currentId)
                        .execute();
            });
        });
    }

    @Override
    public List<Lesson> findByCourseId(int courseId) {
        return getJdbi().withHandle(handle -> {
            return handle.createQuery("SELECT l.id, l.course_id, l.title, l.video_url, l.duration_minutes, l.order_index\n" +
                    "FROM lessons l\n" +
                    "WHERE l.course_id = :courseId\n" +
                    "ORDER BY l.order_index ASC").bind("courseId", courseId).mapToBean(Lesson.class).list();
        });
    }

    @Override
    public List<Lesson> findLessonsByFilter(LessonFilter filter) {
        Map<String, Object> params = new HashMap<>();
        String whereClause = buildLessonWhereClause(filter, params);

        String sql = "SELECT l.id, l.title, l.order_index, l.duration_minutes, l.created_at FROM lessons l "
                + whereClause
                + " ORDER BY l.created_at DESC LIMIT :limit OFFSET :offset";

        return getJdbi().withHandle(handle -> {
            var query = handle.createQuery(sql);
            params.forEach(query::bind);
            query.bind("limit", filter.getSize());
            query.bind("offset", (filter.getPage() - 1) * filter.getSize());
            return query.mapToBean(Lesson.class).list();
        });
    }

    @Override
    public int countLessonsByFilter(LessonFilter filter) {
        Map<String, Object> params = new HashMap<>();
        String where = buildLessonWhereClause(filter, params);

        String sql = "SELECT COUNT(*) FROM lessons l" + where;

        return getJdbi().withHandle(handle -> {
            var query = handle.createQuery(sql);
            params.forEach(query::bind);
            return query.mapTo(Integer.class).one();
        });
    }

    private String buildLessonWhereClause(LessonFilter filter, Map<String, Object> params) {
        StringBuilder where = new StringBuilder(" WHERE 1=1");

        if (filter.getTitle() != null && !filter.getTitle().trim().isEmpty()) {
            where.append(" AND l.title LIKE :nameSearch");
            params.put("nameSearch", "%" + filter.getTitle().trim() + "%");
        }

        if (filter.getCourseId() > 0) {
            where.append(" AND l.course_id = :courseIdSearch");
            params.put("courseIdSearch", filter.getCourseId());
        }

        return where.toString();
    }

}

