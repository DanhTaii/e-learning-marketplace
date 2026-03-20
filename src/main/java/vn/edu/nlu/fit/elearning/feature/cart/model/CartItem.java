package vn.edu.nlu.fit.elearning.feature.cart.model;

import vn.edu.nlu.fit.elearning.feature.course_user.dto.CourseCardDto;

import static vn.edu.nlu.fit.elearning.common.utils.objects.DataFormatting.formatAndConvert;

public class CartItem {
    private CourseCardDto course;
    private int price;
    private boolean selected;

    public CartItem(CourseCardDto course, int price, boolean selected) {
        this.course = course;
        this.price = course.getPrice()-course.getDiscountPrice();
        this.selected = selected;
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
