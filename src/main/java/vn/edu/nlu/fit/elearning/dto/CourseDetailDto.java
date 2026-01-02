package vn.edu.nlu.fit.elearning.dto;

import vn.edu.nlu.fit.elearning.model.Category;
import vn.edu.nlu.fit.elearning.model.Lesson;
import vn.edu.nlu.fit.elearning.model.Tag;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;

public class CourseDetailDto implements Serializable {
    private int id;
    private String title;
    private String subtitle;
    private String description;
    private String goals;
    private String level;
    private int price;
    private int discountPrice;
    private int studentCount;
    private boolean isFeatured;
    private double rating;
    private String thumbnailUrl;
    private boolean isPublic;
    private int categoryId;
    private String authorName;
    private double durationHours;
    private Timestamp createdAt;
    private Timestamp updatedAt;

    // này thêm để làm đủ cho trang course-detail
    private List<Tag> tags;          // danh sách tag
    private List<Lesson> lessons;       // danh sách bài học
    private int lessonCount;            // số lượng bài học
    private List<ReviewDto> reviews;       // danh sách review
    private Category category;        // tên category
    private Category parentCategory;  // category cha

    // này làm riêng cho wishlist
    private int wishlistId;

    public CourseDetailDto() {
    }

    public CourseDetailDto(int id, String title, String subtitle, String description, String goals, String level, int price, int discountPrice, int studentCount, boolean isFeatured, double rating, String thumbnailUrl, boolean isPublic, int categoryId, String authorName, double durationHours, Timestamp createdAt, Timestamp updatedAt, List<Tag> tags, List<Lesson> lessons, int lessonCount, List<ReviewDto> reviews, Category category, Category parentCategory, int wishlistId) {
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
        this.tags = tags;
        this.lessons = lessons;
        this.lessonCount = lessonCount;
        this.reviews = reviews;
        this.category = category;
        this.parentCategory = parentCategory;
        this.wishlistId = wishlistId;
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

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
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

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }

    public Timestamp getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Timestamp updatedAt) {
        this.updatedAt = updatedAt;
    }

    public List<Tag> getTags() {
        return tags;
    }

    public void setTags(List<Tag> tags) {
        this.tags = tags;
    }

    public List<Lesson> getLessons() {
        return lessons;
    }

    public void setLessons(List<Lesson> lessons) {
        this.lessons = lessons;
    }

    public int getLessonCount() {
        return lessonCount;
    }

    public void setLessonCount(int lessonCount) {
        this.lessonCount = lessonCount;
    }

    public List<ReviewDto> getReviews() {
        return reviews;
    }

    public void setReviews(List<ReviewDto> reviews) {
        this.reviews = reviews;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Category getParentCategory() {
        return parentCategory;
    }

    public void setParentCategory(Category parentCategory) {
        this.parentCategory = parentCategory;
    }

    public int getWishlistId() {
        return wishlistId;
    }

    public void setWishlistId(int wishlistId) {
        this.wishlistId = wishlistId;
    }

    @Override
    public String toString() {
        return "CourseDetailDto{" +
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
                ", durationHours=" + durationHours +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                ", tags=" + tags +
                ", lessons=" + lessons +
                ", lessonCount=" + lessonCount +
                ", reviews=" + reviews +
                ", category=" + category +
                ", parentCategory=" + parentCategory +
                ", wishlistId=" + wishlistId +
                '}';
    }
}
