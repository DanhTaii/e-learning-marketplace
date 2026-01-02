package vn.edu.nlu.fit.elearning.dto;

import java.sql.Timestamp;

public class CourseDetailDto {
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
}
