package vn.edu.nlu.fit.elearning.feature.certificate.admin.service;

import vn.edu.nlu.fit.elearning.common.helper.pagination.filter.certificate.CertificateFilter;
import vn.edu.nlu.fit.elearning.feature.certificate.admin.dao.AdminCertificateDao;
import vn.edu.nlu.fit.elearning.feature.certificate.admin.dao.AdminCertificateDaoImp;
import vn.edu.nlu.fit.elearning.feature.certificate.admin.dto.CertificateAdminDto;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class AdminCertificateServiceImp implements AdminCertificateService {

    private final AdminCertificateDao adminCertificateDao;

    public AdminCertificateServiceImp(AdminCertificateDao adminCertificateDao) {
        this.adminCertificateDao = adminCertificateDao;
    }

    @Override
    public List<CertificateAdminDto> getCertificates(CertificateFilter filter) {
        // Đảm bảo dữ liệu hợp lệ trước khi đẩy xuống DAO
        if (filter.getPage() < 1) {
            filter.setPage(1);
        }
        if (filter.getLimit() < 1) {
            filter.setSize(10); // Giá trị mặc định là 10 record/trang
        }

        // Không cần tự tính offset nữa vì CertificateFilter đã tự tính rồi
        return adminCertificateDao.findByFilter(filter);
    }

    @Override
    public int getTotalPages(CertificateFilter filter) {
        if (filter.getLimit() < 1) {
            filter.setSize(10);
        }

        // Lấy tổng số record từ DAO
        int totalCertificates = adminCertificateDao.countByFilter(filter);

        // Tính tổng số trang
        return (int) Math.ceil((double) totalCertificates / filter.getLimit());
    }

}
