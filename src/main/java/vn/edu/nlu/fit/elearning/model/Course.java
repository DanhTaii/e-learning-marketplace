package vn.edu.nlu.fit.elearning.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

public class Course implements Serializable {
    // id INT AUTO_INCREMENT PRIMARY KEY
    private int id;

    // title VARCHAR(255) NOT NULL
    private String title;

    // subtitle TEXT
    private String subtitle;

    // description LONGTEXT
    private String description;

    // goals LONGTEXT
    private String goals;

    // level ENUM('beginner','intermediate','advanced')
    private String level;

    // price DECIMAL(12,2) NOT NULL
    private double price;

    // discount_price DECIMAL(12,2) DEFAULT 0.00
    private double discountPrice; // ĐÃ SỬA: từ discount_price thành discountPrice (camelCase)

    // student_count INT DEFAULT 0
    private int studentCount;

    // is_featured BOOLEAN DEFAULT FALSE
    private boolean isFeatured;

    // rating DECIMAL(3,2) DEFAULT 0.00
    private double rating;

    // thumbnail_url VARCHAR(500)
    private String thumbnailUrl; // ĐÃ SỬA: từ thumbnailURL thành thumbnailUrl (camelCase chuẩn hơn)

    // is_public BOOLEAN DEFAULT FALSE
    private boolean isPublic;

    // category_id INT NULL (Foreign Key)
    private int categoryId; // Vẫn là Integer vì NULL

    // author_name VARCHAR(100) DEFAULT 'Admin'
    private String authorName; // ĐÃ SỬA: từ author_name thành authorName (camelCase)

    private double durationHours;

    // created_at DATETIME DEFAULT CURRENT_TIMESTAMP
    private LocalDateTime createdAt; // ĐÃ SỬA: từ created_at thành createdAt (camelCase)

    // updated_at DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
    private LocalDateTime updatedAt; // ĐÃ SỬA: từ updated_at thành updatedAt (camelCase)

    public Course(int id, String title, String subtitle, String description, String goals, String level, double price, double discountPrice, int studentCount, boolean isFeatured, double rating, String thumbnailUrl, boolean isPublic, int categoryId, String authorName, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.id = id;
        this.title = title;
        this.subtitle = subtitle;
        this.description = description;
        this.goals = goals;
        this.level = level;
        this.price = price;
        this.discountPrice = discountPrice;
        this.studentCount = studentCount;
        this.isFeatured = isFeatured;
        this.rating = rating;
        this.thumbnailUrl = thumbnailUrl;
        this.isPublic = isPublic;
        this.categoryId = categoryId;
        this.authorName = authorName;
        this.durationHours = durationHours;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public Course() {
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

    public String getSubtitle() {
        return subtitle;
    }

    public void setSubtitle(String subtitle) {
        this.subtitle = subtitle;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getGoals() {
        return goals;
    }

    public void setGoals(String goals) {
        this.goals = goals;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getDiscountPrice() {
        return discountPrice;
    }

    public void setDiscountPrice(double discountPrice) {
        this.discountPrice = discountPrice;
    }

    public int getStudentCount() {
        return studentCount;
    }

    public void setStudentCount(int studentCount) {
        this.studentCount = studentCount;
    }

    public boolean isFeatured() {
        return isFeatured;
    }

    public void setFeatured(boolean featured) {
        isFeatured = featured;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public String getThumbnailUrl() {
        return thumbnailUrl;
    }

    public void setThumbnailUrl(String thumbnailUrl) {
        this.thumbnailUrl = thumbnailUrl;
    }

    public boolean isPublic() {
        return isPublic;
    }

    public void setPublic(boolean aPublic) {
        isPublic = aPublic;
    }

    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    public String getAuthorName() {
        return authorName;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }

    public double getDurationHours() {
        return durationHours;
    }

    public void setDurationHours(double durationHours) {
        this.durationHours = durationHours;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    @Override
    public String toString() {
        return "Course{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", subtitle='" + subtitle + '\'' +
                ", description='" + description + '\'' +
                ", goals='" + goals + '\'' +
                ", level='" + level + '\'' +
                ", price=" + price +
                ", discountPrice=" + discountPrice +
                ", studentCount=" + studentCount +
                ", isFeatured=" + isFeatured +
                ", rating=" + rating +
                ", thumbnailUrl='" + thumbnailUrl + '\'' +
                ", isPublic=" + isPublic +
                ", categoryId=" + categoryId +
                ", authorName='" + authorName + '\'' +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                '}';
    }
}
