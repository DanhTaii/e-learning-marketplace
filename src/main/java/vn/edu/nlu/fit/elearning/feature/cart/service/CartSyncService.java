package vn.edu.nlu.fit.elearning.feature.cart.service;

import vn.edu.nlu.fit.elearning.common.container.BeanContainer;
import vn.edu.nlu.fit.elearning.feature.cart.dao.CartDao;
import vn.edu.nlu.fit.elearning.feature.cart.model.Cart;
import vn.edu.nlu.fit.elearning.feature.cart.model.CartItem;
import vn.edu.nlu.fit.elearning.feature.cart.model.CartItemEntity;
import vn.edu.nlu.fit.elearning.feature.course.student.dto.CourseCardDto;
import vn.edu.nlu.fit.elearning.feature.course.student.service.CourseService;

import java.sql.Timestamp;
import java.util.List;

public class CartSyncService {
    private CartDao cartDao;
    private CourseService courseService;
    public CartSyncService() {
        this.cartDao = BeanContainer.getBean(CartDao.class);
        this.courseService = BeanContainer.getBean(CourseService.class);
    }
    public CartServiceImpl syncCartOnLogin(int userId, CartServiceImpl sessionCart) {
        Cart dbCart = cartDao.getCartByUserId(userId);

        if (dbCart == null) {
            if (sessionCart != null && sessionCart.getTotalQuantity() > 0) {
                saveSessionToDatabase(userId, sessionCart);
            }
            return sessionCart;
        }

        if (sessionCart == null || sessionCart.getTotalQuantity() == 0) {
            return loadDatabaseToSession(dbCart, userId);
        }


        String hashA = sessionCart.getCartHash();
        String hashB = dbCart.getCartHash();

        if (hashA != null && hashA.equals(hashB)) {
            return sessionCart;
        }

        Timestamp timeA = sessionCart.getUpdatedAt();
        Timestamp timeB = dbCart.getUpdatedAt();

        if (timeA != null && (timeB == null || timeA.after(timeB))) {
            saveSessionToDatabase(userId, sessionCart);
            return sessionCart;
        }
        else {
            return loadDatabaseToSession(dbCart, userId);
        }
    }

    public void saveSessionToDatabase(int userId, CartServiceImpl sessionCart) {
        Cart dbCart = cartDao.getCartByUserId(userId);
        int cartId;

        if (dbCart == null) {
            cartId = cartDao.createCart(userId, sessionCart.getCartHash(), sessionCart.getUpdatedAt());
        } else {
            cartId = dbCart.getId();
            cartDao.updateCartMetadata(cartId, sessionCart.getCartHash(), sessionCart.getUpdatedAt());
            cartDao.clearCartItems(cartId);
        }

        for (CartItem sessionItem : sessionCart.getList()) {
            int courseId = sessionItem.getCourse().getId();
            cartDao.insertCartItem(cartId, courseId);
        }
    }


    private CartServiceImpl loadDatabaseToSession(Cart dbCart, int userId) {
        CartServiceImpl newSessionCart = new CartServiceImpl();

        List<CartItemEntity> dbItems = dbCart.getItems();
        for (CartItemEntity item : dbItems) {

            CourseCardDto courseCard = courseService.getCourseCardById(item.getId(), userId);

            if (courseCard != null) {
                newSessionCart.addCourse(courseCard);
            }
        }

        newSessionCart.setCartHash(dbCart.getCartHash());
        newSessionCart.setUpdatedAt(dbCart.getUpdatedAt());

        return newSessionCart;
    }
}
