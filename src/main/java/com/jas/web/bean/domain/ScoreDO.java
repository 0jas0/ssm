package com.jas.web.bean.domain;

import com.jas.web.bean.model.AdminModel;

public class ScoreDO extends BaseDO{

    private Integer courseId;

    private Integer studentId;

    private Double gradePoint;

    private Double accessCredits;

    private Integer isRework;

    private String failReason;

    private String reworkSituation;

    public ScoreDO(){}

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

    public Double getGradePoint() {
        return gradePoint;
    }

    public void setGradePoint(Double gradePoint) {
        this.gradePoint = gradePoint;
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
