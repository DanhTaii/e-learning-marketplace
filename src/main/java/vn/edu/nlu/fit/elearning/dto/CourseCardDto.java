package vn.edu.nlu.fit.elearning.dto;

import java.io.Serializable;

public class CourseCardDto implements Serializable {

    private int id;
    private String title;
    private String authorName;
    private int userId;
    private int price;
    private int discountPrice;
    private String thumbnailUrl;
    private String level;
    private double avgRating;
    private double durationHours;
    private int studentCount;
    private boolean inWishlist;

    public CourseCardDto() {
    }

    public CourseCardDto(int id, String title, String authorName, int userId, int price, int discountPrice, String thumbnailUrl, String level, double avgRating, double durationHours, int studentCount, int wishlistId) {
        this.id = id;
        this.title = title;
        this.authorName = authorName;
        this.userId = userId;
        this.price = price;
        this.discountPrice = discountPrice;
        this.thumbnailUrl = thumbnailUrl;
        this.level = level;
        this.avgRating = avgRating;
        this.durationHours = durationHours;
        this.studentCount = studentCount;
    }

    public boolean isInWishlist() {
        return inWishlist;
    }

    public void setInWishlist(boolean inWishlist) {
        this.inWishlist = inWishlist;
    }

    public int getStudentCount() {
        return studentCount;
    }

    public void setStudentCount(int studentCount) {
        this.studentCount = studentCount;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthorName() {
        return authorName;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getDiscountPrice() {
        return discountPrice;
    }

    public void setDiscountPrice(int discountPrice) {
        this.discountPrice = discountPrice;
    }

    public String getThumbnailUrl() {
        return thumbnailUrl;
    }

    public void setThumbnailUrl(String thumbnailUrl) {
        this.thumbnailUrl = thumbnailUrl;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public double getAvgRating() {
        return avgRating;
    }

    public void setAvgRating(double avgRating) {
        this.avgRating = avgRating;
    }

    public double getDurationHours() {
        return durationHours;
    }

    public void setDurationHours(double durationHours) {
        this.durationHours = durationHours;
    }

    @Override
    public String toString() {
        return "CourseCardDto{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", authorName='" + authorName + '\'' +
                ", userId=" + userId +
                ", price=" + price +
                ", discountPrice=" + discountPrice +
                ", thumbnailUrl='" + thumbnailUrl + '\'' +
                ", level='" + level + '\'' +
                ", avgRating=" + avgRating +
                ", durationHours=" + durationHours +
                ", studentCount=" + studentCount +
                ", inWishlist=" + inWishlist +
                '}' + "\n";
    }
}
