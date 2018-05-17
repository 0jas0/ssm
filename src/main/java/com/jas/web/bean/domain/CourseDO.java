package com.jas.web.bean.domain;

import com.jas.web.bean.model.CourseModel;
import com.jas.web.enums.ECourseType;
import com.jas.web.utils.DateUtil;

public class CourseDO extends BaseDO{
    private String teacherId;

    private String name;

    private Long courseStart;

    private Long courseEnd;

    private Integer semester;

    private String college;

    private Integer period;

    private Integer credit;

    private Integer type;

    public CourseDO() {
    }

    public CourseDO(CourseModel courseModel) {
        if (courseModel != null){
            this.setId(courseModel.getId());
            this.teacherId = courseModel.getTeacherId();
            this.name = courseModel.getName();
            this.courseStart = DateUtil.getMillisFromStringByFormat(courseModel.getCourseStart(), "yyyy-MM-dd")/1000;
            this.courseEnd = DateUtil.getMillisFromStringByFormat(courseModel.getCourseEnd(), "yyyy-MM-dd")/1000;
            this.semester = courseModel.getSemester();
            this.college = courseModel.getCollege();
            this.period = courseModel.getPeriod();
            this.credit = courseModel.getCredit();
            this.type = courseModel.getType();
        }
    }

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

    public Long getCourseStart() {
        return courseStart;
    }

    public void setCourseStart(Long courseStart) {
        this.courseStart = courseStart;
    }

    public Long getCourseEnd() {
        return courseEnd;
    }

    public void setCourseEnd(Long courseEnd) {
        this.courseEnd = courseEnd;
    }

    public Integer getSemester() {
        return semester;
    }

    public void setSemester(Integer semester) {
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
