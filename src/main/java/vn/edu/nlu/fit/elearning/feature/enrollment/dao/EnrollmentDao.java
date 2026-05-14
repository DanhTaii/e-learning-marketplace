package vn.edu.nlu.fit.elearning.feature.enrollment.dao;

import vn.edu.nlu.fit.elearning.feature.enrollment.dto.EnrollmentCardDTO;
import vn.edu.nlu.fit.elearning.feature.enrollment.dto.EnrollmentDetailDto;
import vn.edu.nlu.fit.elearning.feature.enrollment.model.Enrollment;

import java.util.List;

public interface EnrollmentDao {
    List<EnrollmentCardDTO> findAllCoursesCard(int userId);

    int createEnrollment(Enrollment entity);

    EnrollmentDetailDto findEnrollmentDetail(int userId, int courseId);

    int findNewPercentComplete(int enrollmentId);

    int findCourseIdById(int enrollmentId);
}
