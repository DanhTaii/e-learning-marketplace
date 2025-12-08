package vn.edu.nlu.fit.elearning.services;

import vn.edu.nlu.fit.elearning.dao.CartDao;
import vn.edu.nlu.fit.elearning.model.CartItem;

import java.util.List;

public class CartService {
    private CartDao cartDao;

    public CartService() {
        this.cartDao = new CartDao();
    }

    public List<CartItem> getCartItems(int userId) {
        List<CartItem> cartItems = cartDao.getCartItemsByUserId(userId);
        return cartItems;
    }
    public void addItem(CartItem cartItem){
         cartDao.create(cartItem);
    }
}
