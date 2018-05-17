package com.jas.web.bean.model;

public class EvalutionTeacherModel {
    //课程号
    private Integer courseId;

    //课程名称
    private String courseName;

    //学院
    private Integer college;

    //学院名
    private String collegeName;

    //课程类型
    private Integer type;

    //类型名称
    private String typeName;

    //评价人数
    private Integer evalutionNum;

    //评价
    private String evalution;

    public Integer getCourseId() {
        return courseId;
    }

    public void setCourseId(Integer courseId) {
        this.courseId = courseId;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public Integer getCollege() {
        return college;
    }

    public void setCollege(Integer college) {
        this.college = college;
    }

    public String getCollegeName() {
        return collegeName;
    }

    public void setCollegeName(String collegeName) {
        this.collegeName = collegeName;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public Integer getEvalutionNum() {
        return evalutionNum;
    }

    public void setEvalutionNum(Integer evalutionNum) {
        this.evalutionNum = evalutionNum;
    }

    public String getEvalution() {
        return evalution;
    }

    public void setEvalution(String evalution) {
        this.evalution = evalution;
    }
}
