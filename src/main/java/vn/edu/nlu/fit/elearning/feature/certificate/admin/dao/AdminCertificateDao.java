package vn.edu.nlu.fit.elearning.feature.certificate.admin.dao;

import vn.edu.nlu.fit.elearning.common.helper.pagination.filter.certificate.CertificateFilter;
import vn.edu.nlu.fit.elearning.feature.certificate.admin.dto.CertificateAdminDto;
import vn.edu.nlu.fit.elearning.feature.certificate.admin.dto.CertificateDetailAdminDto;

import java.util.List;
import java.util.Optional;

public interface AdminCertificateDao {
    List<CertificateAdminDto> findByFilter(CertificateFilter filter);

    int countByFilter(CertificateFilter filter);

    int countTotal();

    Optional<CertificateDetailAdminDto> findById(int id);

    boolean updateStatus(List<Integer> id);

}
