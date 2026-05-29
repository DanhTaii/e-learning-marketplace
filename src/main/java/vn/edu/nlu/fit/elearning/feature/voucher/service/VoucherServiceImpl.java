package vn.edu.nlu.fit.elearning.feature.voucher.service;

import vn.edu.nlu.fit.elearning.common.helper.enums.VoucherStatus;
import vn.edu.nlu.fit.elearning.common.helper.pagination.filter.voucher.VoucherFilter;
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
        return voucherDao.findAll();
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
    public VoucherResultDTO applyVoucher(Integer userId, String code, double cartTotal) throws Exception {
        Voucher voucher = voucherDao.findByCode(code);

        if (voucher == null || voucher.getStatus() == VoucherStatus.INACTIVE) {
            throw new Exception("Mã giảm giá không tồn tại hoặc không hoạt động!");
        }
        long currentTime = System.currentTimeMillis();
        if (voucher.getEndDate() != null && voucher.getEndDate().getTime() < currentTime) {
            throw new Exception("Mã giảm giá đã hết hạn sử dụng!");
        }
        if (voucher.getStartDate() != null && voucher.getStartDate().getTime() > currentTime) {
            throw new Exception("Mã giảm giá chưa đến thời gian áp dụng!");
        }
        if (voucher.getUsageLimit() != null && voucher.getUsedCount() >= voucher.getUsageLimit()) {
            throw new Exception("Mã giảm giá đã hết lượt sử dụng!");
        }
        if (userId != null && voucherDao.hasUserUsedVoucher(userId, voucher.getId())) {
            throw new Exception("Bạn đã sử dụng mã giảm giá này rồi!");
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

    @Override
    public void increaseUsedCount(int voucherId) {
        voucherDao.increaseUsedCount(voucherId);
    }

    @Override
    public boolean hasUserUsedVoucher(Integer userId, Integer id) {
      return  voucherDao.hasUserUsedVoucher(userId,id);
    }
    @Override
    public List<Voucher> searchVouchers(VoucherFilter filter) {
        return voucherDao.getVoucherBySearch(filter);
    }
    @Override
    public int getCountVouchersByFilter(VoucherFilter filter) {
        return voucherDao.countVouchersByFilter(filter);
    }
}
