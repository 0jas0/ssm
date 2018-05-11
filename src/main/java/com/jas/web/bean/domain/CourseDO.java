package com.jas.web.bean.domain;

public class CourseDO extends BaseDO{
    private String teacherId;

    private String name;

    private Integer courseStart;

    private Integer courseEnd;

    private Integer classId;

    private String semester;

    private String college;

    private Integer period;

    private Integer credit;

    private Integer type;

    public String getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(String teacherId) {
        this.teacherId = teacherId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getCourseStart() {
        return courseStart;
    }

    public void setCourseStart(Integer courseStart) {
        this.courseStart = courseStart;
    }

    public Integer getCourseEnd() {
        return courseEnd;
    }

    public void setCourseEnd(Integer courseEnd) {
        this.courseEnd = courseEnd;
    }

    public Integer getClassId() {
        return classId;
    }

    public void setClassId(Integer classId) {
        this.classId = classId;
    }

    public String getSemester() {
        return semester;
    }

    public void setSemester(String semester) {
        this.semester = semester;
    }

    public String getCollege() {
        return college;
    }

    public void setCollege(String college) {
        this.college = college;
    }

    public Integer getPeriod() {
        return period;
    }

    public void setPeriod(Integer period) {
        this.period = period;
    }

    public Integer getCredit() {
        return credit;
    }

    public void setCredit(Integer credit) {
        this.credit = credit;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }
}
