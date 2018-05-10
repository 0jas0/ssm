package com.jas.web.bean.model;

import com.jas.web.bean.domain.StudentDO;
import com.jas.web.bean.domain.TeacherDO;
import com.jas.web.enums.ESex;
import com.jas.web.utils.DateUtil;

public class StudentModel {
    private String studentId;
    private String password;
    private String name;
    private String bornDate;
    private String sex;
    private String politicalOutlook;
    private Integer classId;
    private String college;
    private String major;
    private String nativePlace;
    private String nation;
    private String address;
    private String postalcode;
    private String mobile;
    private String identityCardNumber;
    private String className;

    public StudentModel(){
    }

    public StudentModel(StudentDO studentDO){
        if (studentDO != null){
            this.studentId = studentDO.getStudentId();
            this.password = studentDO.getPassword();
            this.name = studentDO.getName();
            this.bornDate = DateUtil.getStringDateByFormat(studentDO.getBornDate(), "yyyy-MM-dd");
            this.sex = ESex.getDescByValue(studentDO.getSex());
            this.politicalOutlook = studentDO.getPoliticalOutlook();
            this.classId = studentDO.getClassId();
            this.college = studentDO.getCollege();
            this.major = studentDO.getMajor();
            this.postalcode = studentDO.getPostalcode();
            this.mobile = studentDO.getMobile();
            this.identityCardNumber = studentDO.getIdentityCardNumber();
        }
    }

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBornDate() {
        return bornDate;
    }

    public void setBornDate(String bornDate) {
        this.bornDate = bornDate;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getPoliticalOutlook() {
        return politicalOutlook;
    }

    public void setPoliticalOutlook(String politicalOutlook) {
        this.politicalOutlook = politicalOutlook;
    }

    public Integer getClassId() {
        return classId;
    }

    public void setClassId(Integer classId) {
        this.classId = classId;
    }

    public String getCollege() {
        return college;
    }

    public void setCollege(String college) {
        this.college = college;
    }

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major;
    }

    public String getNativePlace() {
        return nativePlace;
    }

    public void setNativePlace(String nativePlace) {
        this.nativePlace = nativePlace;
    }

    public String getNation() {
        return nation;
    }

    public void setNation(String nation) {
        this.nation = nation;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPostalcode() {
        return postalcode;
    }

    public void setPostalcode(String postalcode) {
        this.postalcode = postalcode;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getIdentityCardNumber() {
        return identityCardNumber;
    }

    public void setIdentityCardNumber(String identityCardNumber) {
        this.identityCardNumber = identityCardNumber;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }
}
