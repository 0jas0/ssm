package com.jas.web.bean.model;

import com.jas.web.bean.domain.UsernameDO;
import com.jas.web.enums.ESex;

public class UsernameModel {

    private Integer id;
    private String serialNumber;
    private String password;
    private String name;
    private String sex;
    private String email;
    private String major;
    private String mobile;
    private Integer status;

    public UsernameModel() {
    }

    public UsernameModel(UsernameDO usernameDO) {
        if (usernameDO != null) {
            this.id = usernameDO.getId();
            this.serialNumber = usernameDO.getSerialNumber();
            this.password = usernameDO.getPassword();
            this.name = usernameDO.getName();
            this.sex = ESex.getDescByValue(usernameDO.getSex());
            this.email = usernameDO.getEmail();
            this.major = usernameDO.getMajor();
            this.status = usernameDO.getStatus();
            this.mobile = usernameDO.getMobile();
        }
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
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

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}
