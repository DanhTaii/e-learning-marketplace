package vn.edu.nlu.fit.elearning.common.helper.pagination;

import java.util.List;

public class PageResponse<T> {
    private List<T> data;
    private int currentPage;
    private int totalPage;
    private int totalElement;
    private int pageSize;

    public PageResponse(List<T> data, int currentPage, int totalPage, int totalElement, int pageSize) {
        this.data = data;
        this.currentPage = currentPage;
        this.totalPage = totalPage;
        this.totalElement = totalElement;
        this.pageSize = pageSize;
    }
    public boolean isHasNext() {
        return currentPage < totalPage;
    }

    public boolean isHasPrev() {
        return currentPage > 1;
    }

    public List<T> getData() {
        return data;
    }

    public void setData(List<T> data) {
        this.data = data;
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public int getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }

    public int getTotalElement() {
        return totalElement;
    }

    public void setTotalElement(int totalElement) {
        this.totalElement = totalElement;
    }
}
