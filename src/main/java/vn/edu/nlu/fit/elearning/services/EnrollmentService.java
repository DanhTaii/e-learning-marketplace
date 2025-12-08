package vn.edu.nlu.fit.elearning.services;

import vn.edu.nlu.fit.elearning.dao.EnrollmentDao;
import vn.edu.nlu.fit.elearning.model.Enrollment;

import java.util.List;

public class EnrollmentService {

    private EnrollmentDao ed;

    public EnrollmentService() {
        this.ed = new EnrollmentDao();
    }

    public int createEnrollment(Enrollment enrollment) {
        // TODO: Implement creation logic
        return 0;
    }

    public List<Enrollment> getAllEnrollments() {
        // TODO: Implement getAll logic
        return ed.findAll();
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
}