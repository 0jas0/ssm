package com.jas.web.bean.domain;

import com.jas.web.bean.model.TeacherModel;
import com.jas.web.enums.ESex;
import com.jas.web.utils.DateUtil;

import java.util.Date;

public class TeacherDO extends BaseDO{

    private String teacherId;
    private String password;
    private String name;
    private Date bornDate;
    private Integer sex;
    private String education;
    private String position;
    private String nation;
    private String address;
    private String postalcode;
    private String mobile;
    private String identityCardNumber;

    public TeacherDO(){}

    public TeacherDO(TeacherModel teacherModel){
        if (teacherModel != null){
            this.teacherId = teacherModel.getTeacherId();
            this.password = teacherModel.getPassword();
            this.name = teacherModel.getName();
            this.bornDate = DateUtil.getDateByFormatFromString(teacherModel.getBornDate(), "yyyy-MM-dd");
            this.sex = ESex.getValueByDesc(teacherModel.getSex());
            this.education = teacherModel.getEducation();
            this.position = teacherModel.getPosition();
            this.nation = teacherModel.getNation();
            this.address = teacherModel.getAddress();
            this.postalcode = teacherModel.getPostalcode();
            this.mobile = teacherModel.getMobile();
            this.identityCardNumber = teacherModel.getIdentityCardNumber();
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

    public Date getBornDate() {
        return bornDate;
    }

    public void setBornDate(Date bornDate) {
        this.bornDate = bornDate;
    }

    public Integer getSex() {
        return sex;
    }

    public void setSex(Integer sex) {
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
