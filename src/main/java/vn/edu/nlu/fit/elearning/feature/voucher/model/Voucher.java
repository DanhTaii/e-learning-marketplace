package vn.edu.nlu.fit.elearning.feature.voucher.model;

import vn.edu.nlu.fit.elearning.common.helper.enums.VoucherStatus;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.LocalDateTime;

import static vn.edu.nlu.fit.elearning.common.utils.format.DataFormatting.formatAndConvert;

public class Voucher implements Serializable {
    private Integer id;
    private String code;
    private String title;
    private String description;

    // Loại giảm giá: "FIXED" hoặc "PERCENT"
    private String discountType;

    private int discountValue;
    private int minOrderValue;
    private Integer maxDiscountValue;

    private Timestamp startDate;
    private Timestamp endDate;

    private Integer usageLimit;
    private Integer usedCount;
    private VoucherStatus status;

    private Timestamp createdAt;
    private Timestamp updatedAt;
    private boolean usedByCurrentUser;


    private boolean isDeleted;

    private Timestamp deletedAt;

    private String deleteReason;
    public Voucher() {
    }

    public boolean isDeleted() {
        return isDeleted;
    }

    public void setDeleted(boolean deleted) {
        isDeleted = deleted;
    }

    public Timestamp getDeletedAt() {
        return deletedAt;
    }

    public void setDeletedAt(Timestamp deletedAt) {
        this.deletedAt = deletedAt;
    }

    public String getDeleteReason() {
        return deleteReason;
    }

    public void setDeleteReason(String deleteReason) {
        this.deleteReason = deleteReason;
    }

    public VoucherStatus getStatus() {
        return status;
    }

    public void setStatus(VoucherStatus status) {
        this.status = status;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDiscountType() {
        return discountType;
    }

    public void setDiscountType(String discountType) {
        this.discountType = discountType;
    }

    public int getDiscountValue() {
        return discountValue;
    }

    public void setDiscountValue(int discountValue) {
        this.discountValue = discountValue;
    }

    public int getMinOrderValue() {
        return minOrderValue;
    }

    public void setMinOrderValue(int minOrderValue) {
        this.minOrderValue = minOrderValue;
    }

    public Integer getMaxDiscountValue() {
        return maxDiscountValue;
    }

    public void setMaxDiscountValue(Integer maxDiscountValue) {
        this.maxDiscountValue = maxDiscountValue;
    }

    public Timestamp getStartDate() {
        return startDate;
    }

    public void setStartDate(Timestamp startDate) {
        this.startDate = startDate;
    }

    public Timestamp getEndDate() {
        return endDate;
    }

    public void setEndDate(Timestamp endDate) {
        this.endDate = endDate;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }

    public Timestamp getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Timestamp updatedAt) {
        this.updatedAt = updatedAt;
    }

    public Integer getUsageLimit() {
        return usageLimit;
    }

    public void setUsageLimit(Integer usageLimit) {
        this.usageLimit = usageLimit;
    }

    public Integer getUsedCount() {
        return usedCount;
    }

    public void setUsedCount(Integer usedCount) {
        this.usedCount = usedCount;
    }

    public boolean isUsedByCurrentUser() {
        return usedByCurrentUser;
    }

    public void setUsedByCurrentUser(boolean usedByCurrentUser) {
        this.usedByCurrentUser = usedByCurrentUser;
    }
    public String getFormatDiscountValue(){
        return formatAndConvert(this.discountValue);
    }
}
