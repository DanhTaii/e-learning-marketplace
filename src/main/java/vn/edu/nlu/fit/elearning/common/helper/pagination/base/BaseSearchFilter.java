package vn.edu.nlu.fit.elearning.common.helper.pagination.base;

public abstract class BaseSearchFilter {
    protected int page = 1;
    protected int size = 16;
    private int userId;

    public int getOffSet() {
        return (page - 1) * size;
    }

    public int getPage() {
        return page;
    }


    public void setPage(int page) {
        this.page = page > 0 ? page : 1;
    }

    public int getSize() {
        return size;
    }

    public int getLimit() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

}
