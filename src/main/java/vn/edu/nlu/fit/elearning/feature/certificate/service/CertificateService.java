package vn.edu.nlu.fit.elearning.feature.certificate.service;

import vn.edu.nlu.fit.elearning.feature.certificate.dto.CertificateDetailDto;
import vn.edu.nlu.fit.elearning.feature.certificate.model.Certificate;

public interface CertificateService {
    int createCertificate(Certificate cert);

    boolean hasCertificate(int userId, int courseId);

    String generateUniqueCertificateCode(int userId, int courseId);

    CertificateDetailDto getCertificateByUserIdAndCourseId(int userId, int courseId);
}
