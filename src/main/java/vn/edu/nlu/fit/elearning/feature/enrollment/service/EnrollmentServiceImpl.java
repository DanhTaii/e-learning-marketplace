package vn.edu.nlu.fit.elearning.feature.enrollment.service;

import vn.edu.nlu.fit.elearning.feature.enrollment.dao.EnrollmentDao;
import vn.edu.nlu.fit.elearning.feature.enrollment.dao.EnrollmentDaoImpl;
import vn.edu.nlu.fit.elearning.feature.enrollment.dto.EnrollmentCardDTO;
import vn.edu.nlu.fit.elearning.feature.enrollment.dto.EnrollmentDetailDto;
import vn.edu.nlu.fit.elearning.feature.enrollment.model.Enrollment;

import java.util.List;

public class EnrollmentServiceImpl implements EnrollmentService {

    private EnrollmentDao ed;

    public EnrollmentServiceImpl(EnrollmentDao enrollmentDao) {
        this.ed = enrollmentDao;
    }

    @Override
    public int createEnrollment(Enrollment enrollment) {
        return ed.createEnrollment(enrollment);
    }

    @Override
    public List<EnrollmentCardDTO> getAllEnrollments(int userId) {
        return ed.findAllCoursesCard(userId);
    }

    @Override
    public EnrollmentDetailDto getEnrollmentDetail(int userId, int courseId) {
        return ed.findEnrollmentDetail(userId, courseId);
    }

    public int getNewPercentComplete(int enrollmentId) {
        return ed.findNewPercentComplete(enrollmentId);
    }

    @Override
    public int getCourseIdById(int enrollmentId) {
        return ed.findCourseIdById(enrollmentId);
    }

    public static void main(String[] args) {
//        EnrollmentDao edtest = new EnrollmentDaoImpl();
//        int id = edtest.findCourseIdById(41);
//
//        System.out.println(id);

    }
}