package vn.edu.nlu.fit.elearning.feature.voucher.service;

import vn.edu.nlu.fit.elearning.common.helper.enums.VoucherStatus;
import vn.edu.nlu.fit.elearning.common.helper.pagination.filter.voucher.VoucherArchiveFilter;
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
    public Voucher findById(Integer id) {
        return voucherDao.findById(id);
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

        if (cartTotal < voucher.getMinOrderValue()) {
            throw new Exception("Đơn hàng chưa đạt giá trị tối thiểu " + voucher.getMinOrderValue() + "đ");
        }

        double discountAmount = 0;
        if ("FIXED".equals(voucher.getDiscountType())) {
            discountAmount = voucher.getDiscountValue();
        } else if ("PERCENT".equals(voucher.getDiscountType())) {
            discountAmount = cartTotal * ((double) voucher.getDiscountValue() / 100);

            if (voucher.getMaxDiscountValue() != null && discountAmount > voucher.getMaxDiscountValue()) {
                discountAmount = voucher.getMaxDiscountValue();
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
    @Override
    public int changeVouchersStatusByIds(List<Integer> ids) {
        try {
            return voucherDao.changeVouchersStatusByIds(ids);
        } catch (Exception e) {
            e.printStackTrace(); // Log lỗi ra console nếu có để dễ debug
            return 0;
        }
    }

    @Override
    public int archiveVouchersByIds(List<Integer> ids, String deleteReason) {
        try {
            return voucherDao.archiveVouchersByIds(ids, deleteReason);
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    @Override
    public int deleteVouchersByIds(List<Integer> ids) {
        try {
            return voucherDao.deleteVouchersByIds(ids);
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }
    @Override
    public int bulkDuplicateVouchers(List<Integer> ids) {
        int count = 0;
        for (Integer id : ids) {
            Voucher original = voucherDao.findById(id);
            if (original != null) {
                Voucher clone = new Voucher();


                String suffix = "_CP" + (System.currentTimeMillis() % 1000);
                clone.setCode(original.getCode() + suffix);

                // Sao chép các thông tin khác
                clone.setTitle(original.getTitle() + " (Bản sao)");
                clone.setDescription(original.getDescription());
                clone.setDiscountType(original.getDiscountType());
                clone.setDiscountValue(original.getDiscountValue());
                clone.setMinOrderValue(original.getMinOrderValue());
                clone.setMaxDiscountValue(original.getMaxDiscountValue());
                clone.setUsageLimit(original.getUsageLimit());

                clone.setUsedCount(0);       // Reset số lượt đã dùng về 0
                clone.setStatus(VoucherStatus.valueOf("INACTIVE")); // Để trạng thái ẩn/tạm dừng để admin kiểm tra lại trước khi mở
                clone.setStartDate(original.getStartDate());
                clone.setEndDate(original.getEndDate());

                // Tiến hành lưu vào DB thông qua DAO
                if (voucherDao.create(clone) > 0) {
                    count++;
                }
            }
        }
        return count;
    }

    @Override
    public int getTotalVouchersArchive() {
        return voucherDao.countAllVouchersArchive();
    }

    @Override
    public List<Voucher> getArchivedVouchersByFilter(VoucherArchiveFilter filter) {
        return voucherDao.findArchivedVouchersByFilter(filter);
    }

    @Override
    public int getCountVouchersArchiveByFilter(VoucherArchiveFilter filter) {
        return voucherDao.countVouchersArchiveByFilter(filter);
    }

    @Override
    public int restoreVouchersByIds(List<Integer> ids) {
        return voucherDao.restoreVouchersByIds(ids);
    }

    @Override
    public int createVoucher(Voucher voucher) {
        try {
            return voucherDao.create(voucher);
        } catch (Exception e) {
            return 0;
        }
    }
    @Override
    public boolean updateVoucher(Voucher voucher) {
        try {
            return voucherDao.update(voucher);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
    @Override
    public boolean checkVoucherCode(String code) {

        return voucherDao.findByCode(code) != null;
    }
}

