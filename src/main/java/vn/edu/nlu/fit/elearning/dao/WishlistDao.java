package vn.edu.nlu.fit.elearning.dao;

import vn.edu.nlu.fit.elearning.model.Wishlist;

import java.util.List;

public class WishlistDao extends BaseDao implements BaseCrudDao<Wishlist, Integer> {

    @Override
    public void create(Wishlist entity) {
        // TODO: Implement create logic
    }

    @Override
    public Wishlist findById(Integer id) {
        // TODO: Implement findById logic
        return null;
    }

    @Override
    public List<Wishlist> findAll() {
        return getJdbi().withHandle(handle -> {
            return handle.createQuery("SELECT c.id,c.title, c.is_featured ,c.thumbnail_url,c.level,c.student_count,SUM(l.duration_minutes) / 60.0 AS duration_hours,c.author_name,(c.price - c.discount_price) AS price_new,c.price AS price_old,c.rating\n" +
                    "FROM users u JOIN wishlist w ON u.id = w.user_id\n" +
                    "  JOIN courses c ON w.course_id = c.id\n" +
                    "  LEFT JOIN lessons l ON l.course_id = c.id\n" +
                    "WHERE u.id = 3 AND c.is_public = TRUE").mapToBean(Wishlist.class).list();
        });
    }

    @Override
    public int update(Wishlist entity) {
        // TODO: Implement update logic
        return 0;
    }

    @Override
    public int delete(Integer id) {
        // TODO: Implement delete logic
        return 0;
    }
}