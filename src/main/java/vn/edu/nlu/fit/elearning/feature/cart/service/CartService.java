package vn.edu.nlu.fit.elearning.feature.cart.service;

import vn.edu.nlu.fit.elearning.feature.cart.model.CartItem;
import vn.edu.nlu.fit.elearning.feature.course.student.dto.CourseCardDto;

import java.util.List;

public interface CartService {
    void addCourse(CourseCardDto c);

    CartItem deleteCourse(int id);

    void removeSelected();

    List<CartItem> getSelectedItems();

    void selectAll(boolean isSelected);

    void selectOnly(int courseId);

    int getTotalQuantity();

    List<CartItem> getList();

    double getTotal();

    double getFinalPriceTotal();

    double getDiscountPriceTotal();

    String getFormatedTotal();

    String getFormatedFinalPriceTotal();

    String getFormatedDiscountPriceTotal();

    int getSelectedQuantity();
}
