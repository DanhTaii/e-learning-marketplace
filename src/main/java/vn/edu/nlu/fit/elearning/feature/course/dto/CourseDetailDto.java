package vn.edu.nlu.fit.elearning.feature.course.dto;

import vn.edu.nlu.fit.elearning.feature.review.dto.ReviewDto;
import vn.edu.nlu.fit.elearning.feature.category.model.Category;
import vn.edu.nlu.fit.elearning.feature.lesson.model.Lesson;
import vn.edu.nlu.fit.elearning.feature.tag.model.Tag;

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
    private double avgRating;
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
    private boolean inWishlist;
    private boolean enrolled;
    public CourseDetailDto() {
    }

    public boolean isInWishlist() {
        return inWishlist;
    }

    public void setInWishlist(boolean inWishlist) {
        this.inWishlist = inWishlist;
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

    public double getAvgRating() {
        return avgRating;
    }

    public void setAvgRating(double avgRating) {
        this.avgRating = avgRating;
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

    public boolean isEnrolled() {
        return enrolled;
    }

    public void setEnrolled(boolean enrolled) {
        this.enrolled = enrolled;
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
                ", rating=" + avgRating +
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
                '}';
    }
    public String getDurationText() {
        int hours = (int) this.durationHours;
        int minutes = (int) ((this.durationHours - hours) * 60);
        if(hours == 0){
            return minutes + "p";
        }else if(minutes == 0) {
            return hours + "h ";
        }else{
            return hours + "h " + minutes + "p";
        }

    }
}
