package com.jas.web.bean.model;

import com.jas.web.bean.domain.AdministrativeClassDO;
import com.jas.web.bean.domain.BaseDO;

public class AdministrativeClassModel extends BaseDO{

    private String classId;

    private String name;

    private Integer classNumber;

    private String instructor;

    private String major;

    private String college;

    public AdministrativeClassModel() {
    }

    public AdministrativeClassModel(AdministrativeClassDO administrativeClassDO) {
        if (administrativeClassDO != null){
            this.classId = administrativeClassDO.getClassId();
            this.name = administrativeClassDO.getName();
            this.classNumber = administrativeClassDO.getClassNumber();
            this.instructor = administrativeClassDO.getInstructor();
            this.major = administrativeClassDO.getMajor();
            this.college = administrativeClassDO.getCollege();
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
