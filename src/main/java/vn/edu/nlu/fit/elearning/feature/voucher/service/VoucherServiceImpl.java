package vn.edu.nlu.fit.elearning.feature.voucher.service;

import vn.edu.nlu.fit.elearning.common.helper.enums.VoucherStatus;
import vn.edu.nlu.fit.elearning.feature.voucher.dao.VoucherDao;
import vn.edu.nlu.fit.elearning.feature.voucher.dto.VoucherResultDTO;
import vn.edu.nlu.fit.elearning.feature.voucher.model.Voucher;

import java.util.List;

public class VoucherServiceImpl implements VoucherService {

    private VoucherDao voucherDao;

    public VoucherServiceImpl(VoucherDao voucherDao) {
        this.voucherDao = voucherDao;
    }


    @Override
    public List<Voucher> findAll() {
        return List.of();
    }

    @Override
    public List<Voucher> findValidVouchers() {
        return voucherDao.findValidVouchers();
    }

    @Override
    public Voucher findByCode(String code) {
        return voucherDao.findByCode(code);
    }
    @Override
    public VoucherResultDTO applyVoucher(String code, double cartTotal) throws Exception {
        Voucher voucher = voucherDao.findByCode(code);

        if (voucher == null || voucher.getActive() == VoucherStatus.INACTIVE) {
            throw new Exception("Mã giảm giá không tồn tại hoặc đã hết hạn!");
        }

        if (cartTotal < voucher.getMinOrderValue().doubleValue()) {
            throw new Exception("Đơn hàng chưa đạt giá trị tối thiểu " + voucher.getMinOrderValue() + "đ");
        }

        double discountAmount = 0;
        if ("FIXED".equals(voucher.getDiscountType())) {
            discountAmount = voucher.getDiscountValue().doubleValue();
        } else if ("PERCENT".equals(voucher.getDiscountType())) {
            discountAmount = cartTotal * (voucher.getDiscountValue().doubleValue() / 100);

            if (voucher.getMaxDiscountValue() != null && discountAmount > voucher.getMaxDiscountValue().doubleValue()) {
                discountAmount = voucher.getMaxDiscountValue().doubleValue();
            }
        }
        double finalTotal = Math.max(0, cartTotal - discountAmount);

        return new VoucherResultDTO(voucher, discountAmount, finalTotal);
    }
}
