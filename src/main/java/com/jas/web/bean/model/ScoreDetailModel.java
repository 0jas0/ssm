package com.jas.web.bean.model;

public class ScoreDetailModel {

    private Integer id;

    private Integer courseId;

    private Integer studentId;

    private String studentName;

    private String className;

    private Double grade;

    private Double accessCredits;

    private String failReason;

    private String reworkSituation;

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

    public Integer getStudentId() {
        return studentId;
    }

    public void setStudentId(Integer studentId) {
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

    public Double getGrade() {
        return grade;
    }

    public void setGrade(Double grade) {
        this.grade = grade;
    }

    public Double getAccessCredits() {
        return accessCredits;
    }

    public void setAccessCredits(Double accessCredits) {
        this.accessCredits = accessCredits;
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
