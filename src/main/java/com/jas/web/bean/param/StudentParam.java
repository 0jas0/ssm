package com.jas.web.bean.param;

import com.jas.web.bean.model.StudentModel;
import com.jas.web.bean.model.TeacherModel;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class StudentParam {
    private String studentId;
    @NotNull(message = "密码不能为空")
    @Size(min = 6,message = "密码至少6位")
    private String password;
    @NotNull(message = "名称不能为空")
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
    @NotNull
    private String mobile;
    @NotNull(message = "身份证号不能为空")
    private String identityCardNumber;

    public StudentModel getModel(){
        StudentModel studentModel = new StudentModel();
        studentModel.setStudentId(studentId);
        studentModel.setPassword(password);
        studentModel.setName(name);
        studentModel.setBornDate(bornDate);
        studentModel.setSex(sex);
        studentModel.setPoliticalOutlook(politicalOutlook);
        studentModel.setClassId(classId);
        studentModel.setCollege(college);
        studentModel.setMajor(major);
        studentModel.setNation(nation);
        studentModel.setAddress(address);
        studentModel.setPostalcode(postalcode);
        return studentModel;
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
}
