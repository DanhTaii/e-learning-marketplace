package vn.edu.nlu.fit.elearning.common.helper.pagination.filter.voucher;

import vn.edu.nlu.fit.elearning.common.helper.enums.VoucherStatus;
import vn.edu.nlu.fit.elearning.common.helper.pagination.base.BaseSearchFilter;

import java.sql.Timestamp;

public class VoucherFilter  extends BaseSearchFilter {
    private String name;
    private String discountType;
    private VoucherStatus status; // Lưu ý: Thay đổi kiểu dữ liệu thành Enum của dự án nếu cần (ví dụ: VoucherStatus hoặc Status)

    private Timestamp fromDate;
    private Timestamp toDate;
    private Boolean expiredSoon; // Phục vụ cho checkbox lọc các mã sắp hết hạn

    public VoucherFilter() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDiscountType() {
        return discountType;
    }

    public void setDiscountType(String discountType) {
        this.discountType = discountType;
    }

    public VoucherStatus getStatus() {
        return status;
    }

    public void setStatus(VoucherStatus status) {
        this.status = status;
    }

    public Timestamp getFromDate() {
        return fromDate;
    }

    public void setFromDate(Timestamp fromDate) {
        this.fromDate = fromDate;
    }

    public Timestamp getToDate() {
        return toDate;
    }

    public void setToDate(Timestamp toDate) {
        this.toDate = toDate;
    }

    public Boolean getExpiredSoon() {
        return expiredSoon;
    }

    public void setExpiredSoon(Boolean expiredSoon) {
        this.expiredSoon = expiredSoon;
    }

}
