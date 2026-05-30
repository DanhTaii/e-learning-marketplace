package vn.edu.nlu.fit.elearning.common.helper.pagination.filter.payment;

import vn.edu.nlu.fit.elearning.common.helper.enums.BaseStatus;
import vn.edu.nlu.fit.elearning.common.helper.pagination.base.BaseSearchFilter;

public class PaymentMethodFilter extends BaseSearchFilter {
    private String name;
    private BaseStatus status;

    public PaymentMethodFilter() {
    }

    public PaymentMethodFilter(String name, BaseStatus status) {
        this.name = name;
        this.status = status;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BaseStatus getStatus() {
        return status;
    }

    public void setStatus(BaseStatus status) {
        this.status = status;
    }
}
