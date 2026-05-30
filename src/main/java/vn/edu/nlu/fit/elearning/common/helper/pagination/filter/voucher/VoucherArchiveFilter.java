package vn.edu.nlu.fit.elearning.common.helper.pagination.filter.voucher;

import vn.edu.nlu.fit.elearning.common.helper.pagination.base.BaseSearchFilter;

import java.sql.Timestamp;

public class VoucherArchiveFilter extends BaseSearchFilter {
    private String name;

    private Timestamp deletedFromDate;

    private Timestamp deletedToDate;

    public VoucherArchiveFilter() {
    }

    public VoucherArchiveFilter(String name, Timestamp deletedFromDate, Timestamp deletedToDate) {
        this.name = name;
        this.deletedFromDate = deletedFromDate;
        this.deletedToDate = deletedToDate;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Timestamp getDeletedFromDate() {
        return deletedFromDate;
    }

    public void setDeletedFromDate(Timestamp deletedFromDate) {
        this.deletedFromDate = deletedFromDate;
    }

    public Timestamp getDeletedToDate() {
        return deletedToDate;
    }

    public void setDeletedToDate(Timestamp deletedToDate) {
        this.deletedToDate = deletedToDate;
    }
}