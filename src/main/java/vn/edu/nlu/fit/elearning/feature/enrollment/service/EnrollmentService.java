package vn.edu.nlu.fit.elearning.feature.enrollment.service;

import vn.edu.nlu.fit.elearning.feature.enrollment.dto.EnrollmentCardDTO;
import vn.edu.nlu.fit.elearning.feature.enrollment.dto.EnrollmentDetailDto;
import vn.edu.nlu.fit.elearning.feature.enrollment.model.Enrollment;

import java.util.List;

public interface EnrollmentService {
    int createEnrollment(Enrollment enrollment);

    List<EnrollmentCardDTO> getAllEnrollments(int userId);

    EnrollmentDetailDto getEnrollmentDetail(int userId, int courseId);

    int getNewPercentComplete(int enrollment);
}
