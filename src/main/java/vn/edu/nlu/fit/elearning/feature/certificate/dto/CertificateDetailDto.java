package vn.edu.nlu.fit.elearning.feature.certificate.dto;

import vn.edu.nlu.fit.elearning.common.utils.format.DataFormatting;

import java.sql.Timestamp;

public class CertificateDetailDto {
    private Integer id;
    private Integer courseId;
    private String firstName;
    private String lastName;
    private String courseTitle;
    private Timestamp issueDate;
    private String certificateCode;
    private double durationHours;
    private String pdfUrl;

    public CertificateDetailDto() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCourseId() {
        return courseId;
    }

    public void setCourseId(Integer courseId) {
        this.courseId = courseId;
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

    public String getCertificateCode() {
        return certificateCode;
    }

    public void setCertificateCode(String certificateCode) {
        this.certificateCode = certificateCode;
    }

    public double getDurationHours() {
        return durationHours;
    }

    public void setDurationHours(double durationHours) {
        this.durationHours = durationHours;
    }

    public String getDurationText() {
        return DataFormatting.formatDuration(this.durationHours);
    }

    public String getPdfUrl() {
        return pdfUrl;
    }

    public void setPdfUrl(String pdfUrl) {
        this.pdfUrl = pdfUrl;
    }

    @Override
    public String toString() {
        return "CertificateDetailDto{" +
                "id=" + id +
                ", courseId=" + courseId +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", courseTitle='" + courseTitle + '\'' +
                ", issueDate=" + issueDate +
                ", certificateCode='" + certificateCode + '\'' +
                ", durationHours=" + durationHours +
                '}';
    }
}
