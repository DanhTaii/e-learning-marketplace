package vn.edu.nlu.fit.elearning.feature.enrollment.service;

import vn.edu.nlu.fit.elearning.feature.enrollment.dao.EnrollmentDao;
import vn.edu.nlu.fit.elearning.feature.enrollment.dto.EnrollmentCardDTO;
import vn.edu.nlu.fit.elearning.feature.enrollment.dto.EnrollmentDetailDto;
import vn.edu.nlu.fit.elearning.feature.enrollment.model.Enrollment;

import java.util.List;

public class EnrollmentService {

    private EnrollmentDao ed;

    public EnrollmentService() {
        this.ed = new EnrollmentDao();
    }

    public int createEnrollment(Enrollment enrollment) {
        // TODO: Implement creation logic
        return ed.createEnrollment(enrollment);
    }

    public List<EnrollmentCardDTO> getAllEnrollments(int userId) {
        return ed.findAllCoursesCard(userId);
    }

    public Enrollment getEnrollmentById(int id) {
        // TODO: Implement getById logic
        return null;
    }

    public void updateEnrollment(Enrollment enrollment) {
        // TODO: Implement update logic
    }

    public void deleteEnrollment(int id) {
        // TODO: Implement delete logic
    }


    public EnrollmentDetailDto getEnrollmentDetail(int userId,int courseId) {
        return ed.getEnrollmentDetail(userId, courseId);
    }
}