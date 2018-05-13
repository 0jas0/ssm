package com.jas.web.bean.model;

import com.jas.web.bean.domain.ScoreDO;

public class ScoreModel{

    private Integer courseId;

    private Integer studentId;

    private Double grade;

    private Double accessCredits;

    private Integer isRework;

    private String failReason;

    private String reworkSituation;

    public ScoreModel(){}

    public ScoreModel(ScoreDO scoreDO){
        if (scoreDO != null){
            this.courseId = scoreDO.getCourseId();
            this.studentId = scoreDO.getStudentId();
            this.grade = scoreDO.getGrade();
            this.accessCredits = scoreDO.getAccessCredits();
            this.isRework = scoreDO.getIsRework();
            this.failReason = scoreDO.getFailReason();
            this.reworkSituation = scoreDO.getReworkSituation();
        }
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

    public Integer getIsRework() {
        return isRework;
    }

    public void setIsRework(Integer isRework) {
        this.isRework = isRework;
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
