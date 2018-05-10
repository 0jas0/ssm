package com.jas.web.bean.param;

import com.jas.web.bean.model.TeacherModel;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class TeacherParam {
    private String teacherId;
    @NotNull(message = "密码不能为空")
    @Size(min = 6,message = "密码至少6位")
    private String password;
    @NotNull(message = "名称不能为空")
    private String name;
    private String bornDate;
    private String sex;
    @NotNull(message = "学历不能为空")
    private String education;
    @NotNull(message = "职称不能为空")
    private String position;
    private String nation;
    private String address;
    private String postalcode;
    @NotNull
    private String mobile;
    @NotNull(message = "身份证号不能为空")
    private String identityCardNumber;

    public TeacherModel getModel(){
        TeacherModel teacherModel = new TeacherModel();
        teacherModel.setTeacherId(teacherId);
        teacherModel.setPassword(password);
        teacherModel.setName(name);
        teacherModel.setBornDate(bornDate);
        teacherModel.setSex(sex);
        teacherModel.setEducation(education);
        teacherModel.setPosition(position);
        teacherModel.setNation(nation);
        teacherModel.setAddress(address);
        teacherModel.setPostalcode(postalcode);
        teacherModel.setMobile(mobile);
        teacherModel.setIdentityCardNumber(identityCardNumber);
        return teacherModel;
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
