package com.jas.web.model;

import com.jas.web.bean.StudentDO;

public class StudentModel {
    private Integer id;
    private String studentId;
    private String password;
    private String realName;
    private String major;
    private String email;
    private String mobile;
    private String identityCardNumber;
    private Integer evaluate;
    private String evaluateName;

    public StudentModel() {
    }

    public StudentModel(StudentDO studentDO) {
        if (studentDO != null){
            this.studentId = studentDO.getStudentId();
            this.password = studentDO.getPassword();
            this.realName = studentDO.getRealName();
            this.major = studentDO.getMajor();
            this.email = studentDO.getEmail();
            this.mobile = studentDO.getMobile();
            this.identityCardNumber = studentDO.getIdentityCardNumber();
            this.evaluate = studentDO.getEvaluate();
        }
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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

    public Integer getEvaluate() {
        return evaluate;
    }

    public void setEvaluate(Integer evaluate) {
        this.evaluate = evaluate;
    }

    public String getEvaluateName() {
        return evaluateName;
    }

    public void setEvaluateName(String evaluateName) {
        this.evaluateName = evaluateName;
    }
}
