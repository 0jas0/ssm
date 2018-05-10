package com.jas.web.bean.domain;

import com.jas.web.bean.model.AdminModel;
import com.jas.web.bean.model.TeacherModel;
import com.jas.web.enums.ESex;
import com.jas.web.utils.DateUtil;

import java.util.Date;

public class AdminDO extends BaseDO{

    private String name;
    private String password;

    public AdminDO(){}

    public AdminDO(AdminModel adminModel){
        this.name = adminModel.getName();
        this.password = adminModel.getPassword();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
