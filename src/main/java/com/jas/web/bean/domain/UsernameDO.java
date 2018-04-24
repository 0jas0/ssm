package com.jas.web.bean.domain;

import com.jas.web.bean.model.UsernameModel;
import com.jas.web.enums.ESex;

import java.util.Date;

public class UsernameDO extends BaseDO{

    private String serialNumber;
    private String password;
    private String name;
    private Integer sex;
    private String email;
    private String major;
    private String mobile;
    private Integer status;

    public UsernameDO() {
    }


    public UsernameDO(UsernameModel usernameModel) {
        if (usernameModel != null){
            this.serialNumber = usernameModel.getSerialNumber();
            this.password = usernameModel.getPassword();
            this.name = usernameModel.getName();
            this.sex = ESex.getValueByDesc(usernameModel.getSex());
            this.email = usernameModel.getEmail();
            this.major = usernameModel.getMajor();
            this.mobile = usernameModel.getMobile();
            this.status = usernameModel.getStatus();
        }
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

    public Integer getSex() {
        return sex;
    }

    public void setSex(Integer sex) {
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
