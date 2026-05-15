package vn.edu.nlu.fit.elearning.common.helper.pagination.filter.course;

import vn.edu.nlu.fit.elearning.common.helper.pagination.base.BaseSearchFilter;

public class AllCourseFilter extends BaseSearchFilter {
    private Integer categoryId;
    private Integer tagId;
    private String keyword;
    private String sortPrice;
    private boolean popular;
    private boolean newest;
    private String level;
    private String rating;
    private String duration;
    private String priceRange;
    private int userId;

    public AllCourseFilter() {
    }

    public AllCourseFilter(Integer categoryId, Integer tagId, String keyword, String sortPrice, boolean popular, boolean newest, String level, String rating, String duration, String priceRange, int userId) {
        this.categoryId = categoryId;
        this.tagId = tagId;
        this.keyword = keyword;
        this.sortPrice = sortPrice;
        this.popular = popular;
        this.newest = newest;
        this.level = level;
        this.rating = rating;
        this.duration = duration;
        this.priceRange = priceRange;
        this.userId = userId;
    }

    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    public Integer getTagId() {
        return tagId;
    }

    public void setTagId(Integer tagId) {
        this.tagId = tagId;
    }

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    public String getSortPrice() {
        return sortPrice;
    }

    public void setSortPrice(String sortPrice) {
        this.sortPrice = sortPrice;
    }

    public boolean isPopular() {
        return popular;
    }

    public void setPopular(boolean popular) {
        this.popular = popular;
    }

    public boolean isNewest() {
        return newest;
    }

    public void setNewest(boolean newest) {
        this.newest = newest;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public String getPriceRange() {
        return priceRange;
    }

    public void setPriceRange(String priceRange) {
        this.priceRange = priceRange;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }
}