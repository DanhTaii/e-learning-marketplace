package vn.edu.nlu.fit.elearning.feature.voucher.dto;

import vn.edu.nlu.fit.elearning.feature.voucher.model.Voucher;

public class VoucherResultDTO
{
    private Voucher voucher;
    private double discountAmount;
    private double finalTotal;

    public VoucherResultDTO() {
    }

    public VoucherResultDTO(Voucher voucher, double discountAmount, double finalTotal) {
        this.voucher = voucher;
        this.discountAmount = discountAmount;
        this.finalTotal = finalTotal;
    }

    public Voucher getVoucher() {
        return voucher;
    }

    public void setVoucher(Voucher voucher) {
        this.voucher = voucher;
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
}
