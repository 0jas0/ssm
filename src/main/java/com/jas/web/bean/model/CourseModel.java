package com.jas.web.bean.model;

import com.jas.web.bean.domain.BaseDO;
import com.jas.web.bean.domain.CourseDO;
import com.jas.web.enums.ECourseType;
import com.jas.web.utils.DateUtil;

public class CourseModel{
    private String teacherId;

    private String name;

    private String courseStart;

    private String courseEnd;

    private String semester;

    private String college;

    private Integer period;

    private Integer credit;

    private Integer type;

    private String typeName;

    public CourseModel() {
    }

    public CourseModel(CourseDO courseDO) {
        if (courseDO != null){
            this.teacherId = courseDO.getTeacherId();
            this.name = courseDO.getName();
            this.courseStart = DateUtil.getStringFromLongByFormat(courseDO.getCourseStart(), "yyyy-MM-dd");
            this.courseEnd = DateUtil.getStringFromLongByFormat(courseDO.getCourseEnd(), "yyyy-MM-dd");
            this.semester = courseDO.getSemester();
            this.college = courseDO.getCollege();
            this.period = courseDO.getPeriod();
            this.credit = courseDO.getCredit();
            this.type = courseDO.getType();
            this.typeName = ECourseType.getDescByValue(type);
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

    public String getCourseStart() {
        return courseStart;
    }

    public void setCourseStart(String courseStart) {
        this.courseStart = courseStart;
    }

    public String getCourseEnd() {
        return courseEnd;
    }

    public void setCourseEnd(String courseEnd) {
        this.courseEnd = courseEnd;
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

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }
}
