package vn.edu.nlu.fit.elearning.feature.cart.dao;

import vn.edu.nlu.fit.elearning.common.database.BaseDao;
import vn.edu.nlu.fit.elearning.feature.cart.model.Cart;
import vn.edu.nlu.fit.elearning.feature.cart.model.CartItem;
import vn.edu.nlu.fit.elearning.feature.cart.model.CartItemEntity;

import java.sql.Timestamp;
import java.util.List;

public class CartDaoImpl extends BaseDao implements CartDao {
    @Override
    public Cart getCartByUserId(int userId) {
        return getJdbi().withHandle(handle -> {
            // 1. Lấy thông tin Cart
            Cart cart = handle.createQuery("""
                    SELECT id, user_id AS userId, cart_hash AS cartHash, updated_at AS updatedAt 
                    FROM carts 
                    WHERE user_id = :userId
                """)
                    .bind("userId", userId)
                    .mapToBean(Cart.class)
                    .findFirst()
                    .orElse(null);


            if (cart != null) {
                List<CartItemEntity> items = getCartItemsByCartId(cart.getId());
                cart.setItems(items);
            }
            return cart;
        });
    }

    @Override
    public List<CartItemEntity> getCartItemsByCartId(int cartId) {
        return getJdbi().withHandle(handle ->
                handle.createQuery("""
                    SELECT id, cart_id AS cartId, course_id AS courseId, created_at AS createdAt 
                    FROM cart_items 
                    WHERE cart_id = :cartId
                """)
                        .bind("cartId", cartId)
                        .mapToBean(CartItemEntity.class)
                        .list()
        );
    }

    @Override
    public int createCart(int userId, String cartHash, Timestamp updatedAt) {
        return getJdbi().withHandle(handle ->
                handle.createUpdate("""
                    INSERT INTO carts (user_id, cart_hash, updated_at) 
                    VALUES (:userId, :cartHash, :updatedAt)
                """)
                        .bind("userId", userId)
                        .bind("cartHash", cartHash)
                        .bind("updatedAt", updatedAt)
                        .executeAndReturnGeneratedKeys()
                        .mapTo(Integer.class)
                        .one() // Trả về ID của Cart vừa tạo
        );
    }

    @Override
    public boolean updateCartMetadata(int cartId, String cartHash, Timestamp updatedAt) {
        return getJdbi().withHandle(handle -> {
            int rows = handle.createUpdate("""
                    UPDATE carts 
                    SET cart_hash = :cartHash, updated_at = :updatedAt 
                    WHERE id = :cartId
                """)
                    .bind("cartHash", cartHash)
                    .bind("updatedAt", updatedAt)
                    .bind("cartId", cartId)
                    .execute();
            return rows > 0;
        });
    }

    @Override
    public boolean insertCartItem(int cartId, int courseId) {
        return getJdbi().withHandle(handle -> {
            int rows = handle.createUpdate("""
                    INSERT INTO cart_items (cart_id, course_id, created_at) 
                    VALUES (:cartId, :courseId, NOW())
                """)
                    .bind("cartId", cartId)
                    .bind("courseId", courseId)
                    .execute();
            return rows > 0;
        });
    }

    @Override
    public boolean clearCartItems(int cartId) {
        return getJdbi().withHandle(handle -> {
            handle.createUpdate("DELETE FROM cart_items WHERE cart_id = :cartId")
                    .bind("cartId", cartId)
                    .execute();
            return true;
        });
    }
}
