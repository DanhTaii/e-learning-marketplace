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
import java.util.Set;

public class CartSyncService {
    private CartDao cartDao;
    private CourseService courseService;
    public CartSyncService() {
        this.cartDao = BeanContainer.getBean(CartDao.class);
        this.courseService = BeanContainer.getBean(CourseService.class);
    }
    public CartServiceImpl syncCartOnLogin(int userId, CartServiceImpl sessionCart) {
        Cart dbCart = cartDao.getCartByUserId(userId);
        Set<Integer> ownedIds = cartDao.getEnrolledCourseIdsByUserId(userId);

        if (dbCart == null) {
            if (sessionCart != null && sessionCart.getTotalQuantity() > 0) {
                CartServiceImpl filtered = filterOwned(sessionCart, ownedIds);
                saveSessionToDatabase(userId, filtered);
                return filtered;
            }
            return sessionCart;
        }

        if (sessionCart == null || sessionCart.getTotalQuantity() == 0) {
            return loadDatabaseToSession(dbCart, userId, ownedIds);
        }


        if (sessionCart.getCartHash().equals(dbCart.getCartHash())) {
            return filterOwned(sessionCart, ownedIds);
        }

        Timestamp timeSession = sessionCart.getUpdatedAt();
        Timestamp timeDB = dbCart.getUpdatedAt();

        if (timeSession != null && timeDB != null && timeSession.after(timeDB)) {
            return mergeAndSave(userId, dbCart, sessionCart, ownedIds);
        } else {
            return loadDatabaseToSession(dbCart, userId, ownedIds);
        }
    }
    private CartServiceImpl mergeAndSave(int userId, Cart dbCart, CartServiceImpl sessionCart,Set<Integer> ownedIds) {
        CartServiceImpl mergedCart = new CartServiceImpl();

        for (CartItemEntity dbItem : dbCart.getItems()) {
            if (!ownedIds.contains(dbItem.getCourseId())) {
                CourseCardDto course = courseService.getCourseCardById(dbItem.getCourseId(), userId);
                if (course != null) mergedCart.addCourse(course);
            }
        }

        for (CartItem sessionItem : sessionCart.getList()) {
            int sId = sessionItem.getCourse().getId();
            if (!ownedIds.contains(sId)) {
                mergedCart.addCourse(sessionItem.getCourse());
            }
        }
        saveSessionToDatabase(userId, mergedCart);

        return mergedCart;
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


    private CartServiceImpl loadDatabaseToSession(Cart dbCart, int userId,Set<Integer> ownedIds) {
        CartServiceImpl newSessionCart = new CartServiceImpl();

        List<CartItemEntity> dbItems = dbCart.getItems();
        for (CartItemEntity item : dbItems) {
            if (!ownedIds.contains(item.getCourseId())) {
                CourseCardDto courseCard = courseService.getCourseCardById(item.getCourseId(), userId);

                if (courseCard != null) {
                    newSessionCart.addCourse(courseCard);
                }
            }
        }
        newSessionCart.setCartHash(dbCart.getCartHash());
        newSessionCart.setUpdatedAt(dbCart.getUpdatedAt());

        return newSessionCart;
    }
    private CartServiceImpl filterOwned(CartServiceImpl cart, Set<Integer> ownedIds) {
        CartServiceImpl result = new CartServiceImpl();
        for (CartItem item : cart.getList()) {
            if (!ownedIds.contains(item.getCourse().getId())) {
                result.addCourse(item.getCourse());
            }
        }
        return result;
    }
}
