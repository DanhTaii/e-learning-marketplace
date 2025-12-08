package vn.edu.nlu.fit.elearning.dao;

import org.jdbi.v3.core.Jdbi;
import vn.edu.nlu.fit.elearning.model.CartItem;

import java.util.List;

public class CartItemDao extends BaseDao implements BaseCrudDao<CartItem, Integer> {


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
    public void create(CartItem entity) {
        String sql = "INSERT INTO Cart_Items (user_id, course_id, added_at )" +
                "VALUES (?,?, CURRENT_TIMESTAMP) " +
                "ON DUPLICATE KEY UPDATE added_at = CURRENT_TIMESTAMP";
        getJdbi().useHandle(handle -> {
            handle.createUpdate(sql)
                    .bind(0, entity.getUserId())
                    .bind(1, entity.getCourseId())
                    .execute();

        });

    }


    @Override
    public CartItem findById(Integer integer) {
        return null;
    }

    @Override
    public List<CartItem> findAll() {
        return null;
    }

    @Override
    public int update(CartItem entity) {
        return 0;
    }

        @Override
        public int delete(Integer id) {
            String sql= "DELETE FROM Cart_Items "+
                  "WHERE id = ? ";
            getJdbi().useHandle(handle -> {
                handle.createUpdate(sql)
                        .bind(0,id)
                        .execute();

            });
            return 0;
        }
}