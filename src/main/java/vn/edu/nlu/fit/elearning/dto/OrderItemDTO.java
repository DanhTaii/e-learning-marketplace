package vn.edu.nlu.fit.elearning.dto;

import java.text.NumberFormat;
import java.util.Locale;

public class OrderItemDTO {
    private int id;
    private int userId;
    private int courseId;
    private String title;
    private String thumbnailUrl;
    private boolean isSelected;
    private double rating;
    private long priceOld;
    private long priceNew;
    private String level;
    private double durationHours;
    private int totalLesson;
    private int studentCount;

    public OrderItemDTO(int id, int userId, int courseId, String title, String thumbnailUrl, boolean isSelected, double rating, long priceOld, long priceNew, String level, double durationHours, int totalLesson, int studentCount) {
        this.id = id;
        this.userId = userId;
        this.courseId = courseId;
        this.title = title;
        this.thumbnailUrl = thumbnailUrl;
        this.isSelected = isSelected;
        this.rating = rating;
        this.priceOld = priceOld;
        this.priceNew = priceNew;
        this.level = level;
        this.durationHours = durationHours;
        this.totalLesson = totalLesson;
        this.studentCount = studentCount;
    }

    public OrderItemDTO() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getThumbnailUrl() {
        return thumbnailUrl;
    }

    public void setThumbnailUrl(String thumbnailUrl) {
        this.thumbnailUrl = thumbnailUrl;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public long getPriceOld() {
        return priceOld;
    }

    public void setPriceOld(long priceOld) {
        this.priceOld = priceOld;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public long getPriceNew() {
        return priceNew;
    }

    public void setPriceNew(long priceNew) {
        this.priceNew = priceNew;
    }

    public double getDurationHours() {
        return durationHours;
    }

    public void setDurationHours(double durationHours) {
        this.durationHours = durationHours;
    }

    public int getCourseId() {
        return courseId;
    }

    public void setCourseId(int courseId) {
        this.courseId = courseId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getTotalLesson() {
        return totalLesson;
    }

    public void setTotalLesson(int totalLesson) {
        this.totalLesson = totalLesson;
    }

    public int getStudentCount() {
        return studentCount;
    }

    public void setStudentCount(int studentCount) {
        this.studentCount = studentCount;
    }

    public String getPriceNewFormatted() {
        Locale vietnam = new Locale("vi", "VN");
        NumberFormat f = NumberFormat.getCurrencyInstance(vietnam);
        String formatted = f.format(this.priceNew);
        // Mặc định nó ra "300.000 đ", nếu muốn bỏ chữ "đ" đi để tự thêm sau thì:
        return formatted.replace(" ₫", "").replace("₫", "").trim();
    }

    // Làm tương tự cho giá cũ
    public String getPriceOldFormatted() {
        Locale vietnam = new Locale("vi", "VN");
        NumberFormat f = NumberFormat.getCurrencyInstance(vietnam);
        String formatted = f.format(this.priceOld);
        return formatted.replace(" ₫", "").replace("₫", "").trim();
    }

    public String getTimeDuration() {
        int hours = (int) this.durationHours / 60;             // chỉ lay gio
        int minutes = (int) this.durationHours % 60;  // chi lay phut
        if (hours > 0) {
            if (minutes > 0) {
                return hours + " giờ " + minutes + " phút"; // VD: 2 giờ 15 phút
            } else {
                return hours + " giờ"; // VD: 2 giờ
            }
        } else {
            return minutes + " phút"; // VD: 45 phút (nếu chưa đến 1 tiếng)
        }
    }

}
