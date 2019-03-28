package com.jas.web.bean.model;

import com.jas.web.bean.domain.CourseDO;
import com.jas.web.enums.ECourseSemester;
import com.jas.web.enums.ECourseType;
import com.jas.web.utils.DateUtil;

public class ClassScoreModel {
    private Integer id;

    private String name;

    private String studentName;

    private String college;

    private Integer period;

    private Integer credit;

    private Double grade;

    private String typeName;

    public ClassScoreModel() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
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

    public Double getGrade() {
        return grade;
    }

    public void setGrade(Double grade) {
        this.grade = grade;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }
}
