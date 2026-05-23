package vn.edu.nlu.fit.elearning.feature.certificate.student.service;

import vn.edu.nlu.fit.elearning.feature.certificate.student.dto.CertificateDetailDto;
import vn.edu.nlu.fit.elearning.feature.certificate.model.Certificate;

public interface CertificateService {
    int createCertificate(Certificate cert);

    boolean hasCertificate(int userId, int courseId);

    String generateUniqueCertificateCode(int userId, int courseId);

    CertificateDetailDto getCertificateByUserIdAndCourseId(int userId, int courseId);

    public int processAndGenerateCertificate(Certificate cert, String realPath);
}
