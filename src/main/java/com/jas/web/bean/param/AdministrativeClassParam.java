package com.jas.web.bean.param;

import com.jas.web.bean.domain.AdministrativeClassDO;
import com.jas.web.bean.domain.BaseDO;
import com.jas.web.bean.model.AdministrativeClassModel;

public class AdministrativeClassParam extends BaseDO{

    private String classId;

    private String name;

    private Integer classNumber;

    private String instructor;

    private String major;

    private String college;

    public AdministrativeClassParam() {
    }

    public AdministrativeClassModel getModel(){
        AdministrativeClassModel administrativeClassModel = new AdministrativeClassModel();
        administrativeClassModel.setClassId(classId);
        administrativeClassModel.setName(name);
        administrativeClassModel.setClassNumber(classNumber);
        administrativeClassModel.setInstructor(instructor);
        administrativeClassModel.setMajor(major);
        administrativeClassModel.setCollege(college);
        return administrativeClassModel;
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
