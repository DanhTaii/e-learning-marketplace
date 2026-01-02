package vn.edu.nlu.fit.elearning.model;

public class CartItem {
    private Course course;
    private int price;
    private boolean selected;

    public CartItem(Course course, int price, boolean selected) {
        this.course = course;
        this.price = course.getPrice() - course.getDiscountPrice();
        this.selected = selected;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
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
}
