package vn.edu.nlu.fit.elearning.common.helper.pagination.filter.course;

import vn.edu.nlu.fit.elearning.common.helper.pagination.base.BaseSearchFilter;

public class AllCourseFilter extends BaseSearchFilter {
    private Integer categoryId;
    private String sortPrice;
    private boolean popular;
    private boolean newest;

    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
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

}
