package vn.edu.nlu.fit.elearning.feature.certificate.student.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import vn.edu.nlu.fit.elearning.common.helper.PdfHelper;
import vn.edu.nlu.fit.elearning.feature.certificate.student.dao.CertificateDao;
import vn.edu.nlu.fit.elearning.feature.certificate.student.dto.CertificateDetailDto;
import vn.edu.nlu.fit.elearning.feature.certificate.model.Certificate;
import vn.edu.nlu.fit.elearning.feature.course.admin.dao.CourseAdminDao;
import vn.edu.nlu.fit.elearning.feature.course.admin.dao.CourseAdminDaoImpl;
import vn.edu.nlu.fit.elearning.feature.course.common.model.Course;
import vn.edu.nlu.fit.elearning.feature.user.common.model.User;
import vn.edu.nlu.fit.elearning.feature.user.student.dao.UserDao;
import vn.edu.nlu.fit.elearning.feature.user.student.dao.UserDaoImpl;

import java.sql.Timestamp;
import java.time.Year;
import java.util.UUID;

public class CertificateServiceImpl implements CertificateService {

    private final CertificateDao certificateDao;
    private UserDao userDao = new UserDaoImpl();
    private CourseAdminDao courseDao = new CourseAdminDaoImpl();
    private static Logger logger = LoggerFactory.getLogger(CertificateServiceImpl.class);

    public CertificateServiceImpl(CertificateDao certificateDao) {
        this.certificateDao = certificateDao;
    }

    @Override
    public int createCertificate(Certificate cert) {
//        String code = generateUniqueCertificateCode(cert.getUserId(), cert.getCourseId());
//        cert.setCertificateCode(code);
        return certificateDao.create(cert);
    }

    @Override
    public boolean hasCertificate(int userId, int courseId) {
        return certificateDao.hasCertificate(userId, courseId);
    }

    @Override
    public String generateUniqueCertificateCode(int userId, int courseId) {
        //Lấy ngẫu nhiên 5 kí tự
        String randomPart = UUID.randomUUID().toString().substring(0, 5).toUpperCase();
        int currentYear = Year.now().getValue();

        return String.format("CERT-C%d-U%d-%s-%d", courseId, userId, randomPart, currentYear);
    }

    @Override
    public CertificateDetailDto getCertificateByUserIdAndCourseId(int userId, int courseId) {
        return certificateDao.findByUserIdAndCourseId(userId, courseId);
    }

    @Override
    public int processAndGenerateCertificate(Certificate cert, String realPath) {
        try {
            int userId = cert.getUserId();
            int courseId = cert.getCourseId();

            User user = userDao.findById(userId);
            Course course = courseDao.findById(courseId);

            String username = user.getFirstName() + " " + user.getLastName();
            String courseName = course.getTitle();
            String certCode = this.generateUniqueCertificateCode(userId, courseId);

            PdfHelper.PdfResult result = PdfHelper.generateCertificate(realPath, username, courseName, new Timestamp(System.currentTimeMillis()), certCode);

            if (result.success) {
                cert.setPdfUrl(result.filePath);
                cert.setCertificateCode(certCode);

                return this.createCertificate(cert);

            } else {
                logger.error("Lỗi tạo PDF " + result.errorMessage);
                System.out.println("Lỗi tạo PDF: " + result.errorMessage);
                return 0;
            }

        } catch (Exception e) {
            logger.error("Lỗi tạo PDF " + String.valueOf(e.getMessage()));
            e.printStackTrace();
            return 0;
        }
    }
}
