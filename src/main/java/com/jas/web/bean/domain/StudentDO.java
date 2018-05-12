package com.jas.web.bean.domain;

import com.jas.web.bean.model.StudentModel;
import com.jas.web.bean.model.TeacherModel;
import com.jas.web.enums.ESex;
import com.jas.web.utils.DateUtil;

import java.util.Date;

public class StudentDO extends BaseDO{

    private String studentId;
    private String password;
    private String photo;
    private String name;
    private Date bornDate;
    private Integer sex;
    private String politicalOutlook;
    private Integer classId;
    private Integer college;
    private Integer major;
    private String nativePlace;
    private String nation;
    private String address;
    private String postalcode;
    private String mobile;
    private String identityCardNumber;

    public StudentDO(){}

    public StudentDO(StudentModel studentModel){
        if (studentModel != null){
            this.studentId = studentModel.getStudentId();
            this.photo = studentModel.getPhoto();
            this.password = studentModel.getPassword();
            this.name = studentModel.getName();
            this.bornDate = DateUtil.getDateByFormatFromString(studentModel.getBornDate(), "yyyy-MM-dd");
            this.sex = ESex.getValueByDesc(studentModel.getSex());
            this.politicalOutlook = studentModel.getPoliticalOutlook();
            this.classId = studentModel.getClassId();
            this.college = studentModel.getCollege();
            this.major = studentModel.getMajor();
            this.nativePlace = studentModel.getNativePlace();
            this.nation = studentModel.getNation();
            this.address = studentModel.getAddress();
            this.postalcode = studentModel.getPostalcode();
            this.mobile = studentModel.getMobile();
            this.identityCardNumber = studentModel.getIdentityCardNumber();
        }
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
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

    public Integer getCollege() {
        return college;
    }

    public void setCollege(Integer college) {
        this.college = college;
    }

    public Integer getMajor() {
        return major;
    }

    public void setMajor(Integer major) {
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
