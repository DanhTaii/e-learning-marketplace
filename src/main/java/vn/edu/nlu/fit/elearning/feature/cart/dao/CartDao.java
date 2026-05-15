package vn.edu.nlu.fit.elearning.feature.cart.dao;


import vn.edu.nlu.fit.elearning.feature.cart.model.Cart;
import vn.edu.nlu.fit.elearning.feature.cart.model.CartItem;

import java.sql.Timestamp;
import java.util.List;

public interface CartDao {

    Cart getCartByUserId(int userId);

    List<CartItem> getCartItemsByCartId(int cartId);

    int createCart(int userId, String cartHash, Timestamp updatedAt);

    boolean updateCartMetadata(int cartId, String cartHash, Timestamp updatedAt);

    boolean insertCartItem(int cartId, int courseId);

    boolean clearCartItems(int cartId);
}
