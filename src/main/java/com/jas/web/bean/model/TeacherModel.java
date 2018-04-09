package com.jas.web.bean.model;

import com.jas.web.bean.domain.TeacherDO;
import com.jas.web.enums.ESex;
import com.jas.web.utils.DateUtil;

public class TeacherModel {
    private String teacherId;
    private String password;
    private String name;
    private String bornDate;
    private String sex;
    private String education;
    private String position;
    private String nation;
    private String address;
    private String postalcode;
    private String mobile;
    private String identityCardNumber;

    public TeacherModel(){
    }

    public TeacherModel(TeacherDO teacherDO){
        if (teacherDO != null){
            this.teacherId = teacherDO.getTeacherId();
            this.password = teacherDO.getPassword();
            this.name = teacherDO.getName();
            this.bornDate = DateUtil.getStringDateByFormat(teacherDO.getBornDate(), "yyyy-MM-dd");
            this.sex = ESex.getDescByValue(teacherDO.getSex());
            this.education = teacherDO.getEducation();
            this.position = teacherDO.getPosition();
            this.nation = teacherDO.getNation();
            this.address = teacherDO.getAddress();
            this.postalcode = teacherDO.getPostalcode();
            this.mobile = teacherDO.getMobile();
            this.identityCardNumber = teacherDO.getIdentityCardNumber();
        }
    }

    public String getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(String teacherId) {
        this.teacherId = teacherId;
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

    public String getEducation() {
        return education;
    }

    public void setEducation(String education) {
        this.education = education;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
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
}
