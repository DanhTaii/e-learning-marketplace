package vn.edu.nlu.fit.elearning.model;

import java.io.Serializable;

public class CourseTag implements Serializable {
    private int id;
    private int courseId;
    private int tagId;


    public CourseTag() {
    }


    public CourseTag(int id, int courseId, int tagId) {
        this.id = id;
        this.courseId = courseId;
        this.tagId = tagId;
    }


    public CourseTag(int courseId, int tagId) {
        this.courseId = courseId;
        this.tagId = tagId;
    }

    // --- GETTER & SETTER ---

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCourseId() {
        return courseId;
    }

    public void setCourseId(int courseId) {
        this.courseId = courseId;
    }

    public int getTagId() {
        return tagId;
    }

    public void setTagId(int tagId) {
        this.tagId = tagId;
    }


}