package com.jas.web.bean.domain;

public class TeachingEvaluationDO extends BaseDO{
    private Integer courseId;

    private Integer evaluationGrade;

    private Integer teacherId;

    public Integer getCourseId() {
        return courseId;
    }

    public void setCourseId(Integer courseId) {
        this.courseId = courseId;
    }

    public Integer getEvaluationGrade() {
        return evaluationGrade;
    }

    public void setEvaluationGrade(Integer evaluationGrade) {
        this.evaluationGrade = evaluationGrade;
    }

    public Integer getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(Integer teacherId) {
        this.teacherId = teacherId;
    }
}
