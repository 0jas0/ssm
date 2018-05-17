package com.jas.web.bean.domain;

import com.jas.web.bean.model.AdminModel;
import com.jas.web.bean.model.ScoreModel;

public class ScoreDO extends BaseDO{

    private Integer courseId;

    private Integer studentId;

    private Double grade;

    private Double accessCredits;

    private Integer isRework;

    private String failReason;

    private String reworkSituation;

    public ScoreDO(){}

    public ScoreDO(ScoreModel scoreModel){
        if (scoreModel != null){
            this.setId(scoreModel.getId());
            this.courseId = scoreModel.getCourseId();
            this.studentId = scoreModel.getStudentId();
            this.grade= scoreModel.getGrade();
            this.accessCredits = scoreModel.getAccessCredits();
            this.isRework = scoreModel.getIsRework();
            this.failReason = scoreModel.getFailReason();
            this.reworkSituation = scoreModel.getReworkSituation();
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
