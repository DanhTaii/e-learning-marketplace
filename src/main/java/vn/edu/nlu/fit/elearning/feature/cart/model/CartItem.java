package vn.edu.nlu.fit.elearning.feature.cart.model;

import vn.edu.nlu.fit.elearning.feature.course.student.dto.CourseCardDto;

import java.sql.Timestamp;

import static vn.edu.nlu.fit.elearning.common.utils.format.DataFormatting.formatAndConvert;

public class CartItem {
    private CourseCardDto course;
    private int price;
    private boolean selected;
    private int id;
    private int cartId;
    private int courseId;
    private Timestamp createdAt;

    public CartItem(CourseCardDto course, int price, boolean selected) {
        this.course = course;
        this.price = course.getPrice()-course.getDiscountPrice();
        this.selected = selected;
    }

    public CartItem(CourseCardDto course, int price, boolean selected, int id, int cartId, int courseId, Timestamp createdAt) {
        this.course = course;
        this.price = price;
        this.selected = selected;
        this.id = id;
        this.cartId = cartId;
        this.courseId = courseId;
        this.createdAt = createdAt;
    }

    public CourseCardDto getCourse() {
        return course;
    }

    public void setCourse(CourseCardDto course) {
        this.course = course;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public boolean isSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }

    public String getPriceFormat(){
        return formatAndConvert(this.price);
    }
}
