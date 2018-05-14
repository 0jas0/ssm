package com.jas.web.bean.model;

import com.jas.web.bean.domain.AdministrativeClassDO;
import com.jas.web.bean.domain.BaseDO;

public class AdministrativeClassModel{

    private Integer id;

    private String classId;

    private String name;

    private Integer classNumber;

    private String instructor;

    private Integer major;

    private Integer college;

    private String majorName;

    private String collegeName;

    public AdministrativeClassModel() {
    }

    public AdministrativeClassModel(AdministrativeClassDO administrativeClassDO) {
        if (administrativeClassDO != null){
            this.id = administrativeClassDO.getId();
            this.classId = administrativeClassDO.getClassId();
            this.name = administrativeClassDO.getName();
            this.classNumber = administrativeClassDO.getClassNumber();
            this.instructor = administrativeClassDO.getInstructor();
            this.major = administrativeClassDO.getMajor();
            this.college = administrativeClassDO.getCollege();
        }
    }

    public String getMajorName() {
        return majorName;
    }

    public void setMajorName(String majorName) {
        this.majorName = majorName;
    }

    public String getCollegeName() {
        return collegeName;
    }

    public void setCollegeName(String collegeName) {
        this.collegeName = collegeName;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public Integer getMajor() {
        return major;
    }

    public void setMajor(Integer major) {
        this.major = major;
    }

    public Integer getCollege() {
        return college;
    }

    public void setCollege(Integer college) {
        this.college = college;
    }
}
