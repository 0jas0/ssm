package com.jas.web.bean.model;

import com.jas.web.bean.domain.BaseDO;
import com.jas.web.bean.domain.StudentDO;
import com.jas.web.bean.domain.TeacherDO;
import com.jas.web.enums.ESex;
import com.jas.web.utils.DateUtil;

public class StudentModel{
    private Integer id;
    private String studentId;
    private String password;
    private String photo;
    private String name;
    private String bornDate;
    private String sex;
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
    private String className;
    private String collegeName;
    private String majorName;

    public StudentModel(){
    }

    public StudentModel(StudentDO studentDO){
        if (studentDO != null){
            this.id = studentDO.getId();
            this.studentId = studentDO.getStudentId();
            this.password = studentDO.getPassword();
            this.photo = studentDO.getPhoto();
            this.name = studentDO.getName();
            this.bornDate = DateUtil.getStringDateByFormat(studentDO.getBornDate(), "yyyy-MM-dd");
            this.sex = ESex.getDescByValue(studentDO.getSex());
            this.politicalOutlook = studentDO.getPoliticalOutlook();
            this.nativePlace = studentDO.getNativePlace();
            this.nation = studentDO.getNation();
            this.address =studentDO.getAddress();
            this.classId = studentDO.getClassId();
            this.college = studentDO.getCollege();
            this.major = studentDO.getMajor();
            this.postalcode = studentDO.getPostalcode();
            this.mobile = studentDO.getMobile();
            this.identityCardNumber = studentDO.getIdentityCardNumber();
        }
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getCollegeName() {
        return collegeName;
    }

    public void setCollegeName(String collegeName) {
        this.collegeName = collegeName;
    }

    public String getMajorName() {
        return majorName;
    }

    public void setMajorName(String majorName) {
        this.majorName = majorName;
    }
}
