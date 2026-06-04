package vn.edu.nlu.fit.elearning.feature.certificate.student.dto;

import java.io.Serializable;
import java.sql.Timestamp;

public class CertificateInfo implements Serializable {
    private String certificateCode;
    private String firstName;
    private String lastName;
    private String courseName;
    private Timestamp issueDate;
    private String pdfUrl;

    public CertificateInfo() {
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

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public Timestamp getIssueDate() {
        return issueDate;
    }

    public void setIssueDate(Timestamp issueDate) {
        this.issueDate = issueDate;
    }

    public String getPdfUrl() {
        return pdfUrl;
    }

    public void setPdfUrl(String pdfUrl) {
        this.pdfUrl = pdfUrl;
    }

    @Override
    public String toString() {
        return "CertificateInfo{" +
                "certificateCode='" + certificateCode + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", courseName='" + courseName + '\'' +
                ", issueDate=" + issueDate +
                ", pdfUrl='" + pdfUrl + '\'' +
                '}';
    }
}
