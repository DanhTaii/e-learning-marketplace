package vn.edu.nlu.fit.elearning.dao;

import org.jdbi.v3.core.Jdbi;
import vn.edu.nlu.fit.elearning.model.CartItem;

import java.util.List;

public class CartDao  extends BaseDao implements BaseCrudDao{


    public List<CartItem> getCartItemsByUserId(int userId) {
        Jdbi jdbi = getJdbi();
        String sql = "SELECT c.id, c.title, ci.is_selected as selected, c.thumbnail_url, c.rating, c.level, c.price AS price_old, " +
                "(c.price - c.discount_price) AS price_new, SUM(l.duration_minutes) / 60.0 AS duration_hours " +
                "FROM Cart_Items ci " +
                "LEFT JOIN Courses c ON ci.course_id = c.id " +
                "LEFT JOIN Users u ON ci.user_id = u.id " +
                "LEFT JOIN Lessons l ON l.course_id = c.id " +
                "WHERE ci.user_id = ? " +
                "GROUP BY c.id, c.title, c.thumbnail_url, c.rating, c.level, c.discount_price, c.price";
        return jdbi.withHandle(handle -> {
            return handle.createQuery(sql)
                    .bind(0, userId)
                    .mapToBean(CartItem.class).list();
        });
    }

    @Override
    public void create(Object entity) {

    }

    @Override
    public Object findById(Object o) {
        return null;
    }

    @Override
    public List findAll() {
        return List.of();
    }

    @Override
    public int update(Object entity) {
        return 0;
    }

    @Override
    public int delete(Object o) {
        return 0;
    }
}
