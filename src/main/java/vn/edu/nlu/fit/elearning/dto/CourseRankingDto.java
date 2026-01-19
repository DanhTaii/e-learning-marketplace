package vn.edu.nlu.fit.elearning.dto;

import java.io.Serializable;

public class CourseRankingDto implements Serializable {
    private int id;
    private String title;
    private int studentCount;

    public CourseRankingDto(int id, String title, int studentCount) {
        this.id = id;
        this.title = title;
        this.studentCount = studentCount;
    }

    public CourseRankingDto() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getStudentCount() {
        return studentCount;
    }

    public void setStudentCount(int studentCount) {
        this.studentCount = studentCount;
    }

    @Override
    public String toString() {
        return "CourseRankingDto{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", studentCount=" + studentCount +
                '}' + '\n';
    }
}
