package com.jas.web.bean;

import com.jas.web.model.StudentModel;

public class StudentDO extends BaseDO {
    private String studentId;
    private String password;
    private String realName;
    private String major;
    private String email;
    private String mobile;
    private String identityCardNumber;
    private Integer evaluate;

    public StudentDO() {
    }

    public StudentDO(StudentModel studentModel) {
        if (studentModel != null){
            this.studentId = studentModel.getStudentId();
            this.password = studentModel.getPassword();
            this.realName = studentModel.getRealName();
            this.major = studentModel.getMajor();
            this.email = studentModel.getEmail();
            this.mobile = studentModel.getMobile();
            this.identityCardNumber = studentModel.getIdentityCardNumber();
            this.evaluate = studentModel.getEvaluate();
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
}
