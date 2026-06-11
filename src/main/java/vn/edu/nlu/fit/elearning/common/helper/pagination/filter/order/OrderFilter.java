package vn.edu.nlu.fit.elearning.common.helper.pagination.filter.order;

import vn.edu.nlu.fit.elearning.common.helper.enums.BaseStatus;
import vn.edu.nlu.fit.elearning.common.helper.enums.OrderStatus;
import vn.edu.nlu.fit.elearning.common.helper.pagination.base.BaseSearchFilter;

import java.sql.Timestamp;

public class OrderFilter extends BaseSearchFilter {
    private String voucherCode;
    private String name;        // searchName
    private String code;        // code
    private int courseId;
    private int paymentMethodId;
    private Timestamp fromDate;
    private Timestamp toDate;
    private OrderStatus status;
    private String sortByPrice;
    public OrderFilter() {}

    public OrderFilter(String name, String code, int courseId, int paymentMethodId, Timestamp fromDate, Timestamp toDate, OrderStatus status) {
        this.name = name;
        this.code = code;
        this.courseId = courseId;
        this.paymentMethodId = paymentMethodId;
        this.fromDate = fromDate;
        this.toDate = toDate;
        this.status = status;
    }
    public String getSortByPrice() {
        return sortByPrice;
    }

    public void setSortByPrice(String sortByPrice) {
        this.sortByPrice = sortByPrice;
    }
    public String getVoucherCode() {
        return voucherCode;
    }

    public void setVoucherCode(String voucherCode) {
        this.voucherCode = voucherCode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public int getCourseId() {
        return courseId;
    }

    public void setCourseId(int courseId) {
        this.courseId = courseId;
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

    public OrderStatus getStatus() {
        return status;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }

    public int getPaymentMethodId() {
        return paymentMethodId;
    }

    public void setPaymentMethodId(int paymentMethodId) {
        this.paymentMethodId = paymentMethodId;
    }
}