package com.jas.web.bean.model;

public class ScoreDetailModel {

    private String id;

    private Integer courseId;

    private String studentId;

    private String studentName;

    private String className;

    private String grade;

    private String accessCredits;

    private String failReason;

    private String reworkSituation;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public void setAccessCredits(String accessCredits) {
        this.accessCredits = accessCredits;
    }

    public String getGrade() {
        return grade;
    }

    public String getAccessCredits() {
        return accessCredits;
    }

    public Integer getCourseId() {
        return courseId;
    }

    public void setCourseId(Integer courseId) {
        this.courseId = courseId;
    }

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getFailReason() {
        return failReason;
    }

    public void setFailReason(String failReason) {
        this.failReason = failReason;
    }

    public String getReworkSituation() {
        return reworkSituation;
    }

    public void setReworkSituation(String reworkSituation) {
        this.reworkSituation = reworkSituation;
    }
}
