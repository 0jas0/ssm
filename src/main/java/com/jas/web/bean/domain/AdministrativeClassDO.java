package com.jas.web.bean.domain;

import com.jas.web.bean.model.AdministrativeClassModel;

public class AdministrativeClassDO extends BaseDO{

    private String classId;

    private String name;

    private Integer classNumber;

    private String instructor;

    private String major;

    private String college;

    public AdministrativeClassDO() {
    }

    public AdministrativeClassDO(AdministrativeClassModel administrativeClassModel) {
        if (administrativeClassModel != null){
            this.classId = administrativeClassModel.getClassId();
            this.name = administrativeClassModel.getName();
            this.classNumber = administrativeClassModel.getClassNumber();
            this.instructor = administrativeClassModel.getInstructor();
            this.major = administrativeClassModel.getMajor();
            this.college = administrativeClassModel.getCollege();
        }
    }

    public String getClassId() {
        return classId;
    }

    public void setClassId(String classId) {
        this.classId = classId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getClassNumber() {
        return classNumber;
    }

    public void setClassNumber(Integer classNumber) {
        this.classNumber = classNumber;
    }

    public String getInstructor() {
        return instructor;
    }

    public void setInstructor(String instructor) {
        this.instructor = instructor;
    }

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major;
    }

    public String getCollege() {
        return college;
    }

    public void setCollege(String college) {
        this.college = college;
    }
}
