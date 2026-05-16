package vn.edu.nlu.fit.elearning.feature.payment.dto;

import vn.edu.nlu.fit.elearning.common.utils.format.DataFormatting;
import vn.edu.nlu.fit.elearning.feature.voucher.model.Voucher;

public class PaymentSummaryDTO {
    private double subTotal;
    private double discountAmount;
    private double finalTotal;
    private Voucher appliedVoucher;
    public PaymentSummaryDTO(double subTotal, double discountAmount, double finalTotal, Voucher appliedVoucher) {
        this.subTotal = subTotal;
        this.discountAmount = discountAmount;
        this.finalTotal = finalTotal;
        this.appliedVoucher = appliedVoucher;
    }

    public double getSubTotal() {
        return subTotal;
    }

    public void setSubTotal(double subTotal) {
        this.subTotal = subTotal;
    }

    public double getDiscountAmount() {
        return discountAmount;
    }

    public void setDiscountAmount(double discountAmount) {
        this.discountAmount = discountAmount;
    }

    public double getFinalTotal() {
        return finalTotal;
    }

    public void setFinalTotal(double finalTotal) {
        this.finalTotal = finalTotal;
    }

    public void setAppliedVoucher(Voucher appliedVoucher) {
        this.appliedVoucher = appliedVoucher;
    }

    public String getSubTotalStr() { return DataFormatting.formatAndConvert(subTotal); }
    public String getDiscountStr() { return DataFormatting.formatAndConvert(discountAmount); }
    public String getTotalToPayStr() { return DataFormatting.formatAndConvert(finalTotal); }
    public Voucher getAppliedVoucher() { return appliedVoucher; }

}
