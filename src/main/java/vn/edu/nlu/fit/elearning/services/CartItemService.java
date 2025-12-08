package vn.edu.nlu.fit.elearning.services;

import vn.edu.nlu.fit.elearning.dao.CartItemDao;
import vn.edu.nlu.fit.elearning.model.CartItem;

import java.util.List;

public class CartItemService {
    private CartItemDao cartDao;

    public CartItemService() {
        this.cartDao = new CartItemDao();
    }

    public List<CartItem> getCartItems(int userId) {
        List<CartItem> cartItems = cartDao.getCartItemsByUserId(userId);
        return cartItems;
    }
    public void addItem(CartItem cartItem){
         cartDao.create(cartItem);
    }
}
