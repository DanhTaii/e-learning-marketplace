package vn.edu.nlu.fit.elearning.feature.certificate.admin.dto;

import java.io.Serializable;
import java.sql.Timestamp;

public class CertificateDetailAdminDto implements Serializable {

    private Integer id;
    private String certificateCode;
    private String firstName;
    private String lastName;
    private String courseTitle;
    private Timestamp issueDate;
    private String status;
    private Timestamp enrollmentDate;
    private Timestamp completionDate;
    private Double durationHours;
    private String pdfUrl;

    public CertificateDetailAdminDto() {
    }

    // Hàm chuyển đổi tự động từ số giờ (Double) sang chuỗi hiển thị
    public String getDurationText() {
        if (durationHours == null || durationHours <= 0) {
            return "Chưa cập nhật";
        }
        // Hiển thị dạng "2.5 giờ"
        return String.format("%.1f giờ", durationHours).replace(".0", "");
    }

    // Getters and Setters
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCertificateCode() {
        return certificateCode;
    }

    public void setCertificateCode(String certificateCode) {
        this.certificateCode = certificateCode;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getCourseTitle() {
        return courseTitle;
    }

    public void setCourseTitle(String courseTitle) {
        this.courseTitle = courseTitle;
    }

    public Timestamp getIssueDate() {
        return issueDate;
    }

    public void setIssueDate(Timestamp issueDate) {
        this.issueDate = issueDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Timestamp getEnrollmentDate() {
        return enrollmentDate;
    }

    public void setEnrollmentDate(Timestamp enrollmentDate) {
        this.enrollmentDate = enrollmentDate;
    }

    public Timestamp getCompletionDate() {
        return completionDate;
    }

    public void setCompletionDate(Timestamp completionDate) {
        this.completionDate = completionDate;
    }

    public Double getDurationHours() {
        return durationHours;
    }

    public void setDurationHours(Double durationHours) {
        this.durationHours = durationHours;
    }

    public String getPdfUrl() {
        return pdfUrl;
    }

    public void setPdfUrl(String pdfUrl) {
        this.pdfUrl = pdfUrl;
    }
}
