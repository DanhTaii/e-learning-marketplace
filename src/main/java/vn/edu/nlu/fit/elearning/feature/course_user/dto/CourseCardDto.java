package vn.edu.nlu.fit.elearning.feature.course_user.dto;

import vn.edu.nlu.fit.elearning.common.helper.enums.Level;

import java.io.Serializable;

import static vn.edu.nlu.fit.elearning.common.utils.objects.DataFormatting.formatAndConvert;

public class CourseCardDto implements Serializable {

    private int id;
    private String title;
    private String authorName;
    private int userId;
    private int price;
    private int discountPrice;
    private String thumbnailUrl;
    private Level level;
    private double avgRating;
    private double durationHours;
    private int studentCount;
    private boolean inWishlist;
    private boolean enrolled;
    private int lessonCount;

    public CourseCardDto() {
    }

    public int getLessonCount() {
        return lessonCount;
    }

    public void setLessonCount(int lessonCount) {
        this.lessonCount = lessonCount;
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

    public Level getLevel() {
        return level;
    }

    public void setLevel(Level level) {
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
    public boolean isEnrolled() {
        return enrolled;
    }

    public void setEnrolled(boolean enrolled) {
        this.enrolled = enrolled;
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
    public String getOriginPrice() {
        return formatAndConvert(this.price);
    }
    public String getDiscountedPrice(){
        int finalPrice = this.price - this.discountPrice;
        return formatAndConvert(finalPrice);
    }
}
