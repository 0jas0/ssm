package com.jas.web.bean.model;

import com.jas.web.bean.domain.AdminDO;
import com.jas.web.bean.domain.BaseDO;

public class AdminModel extends BaseDO{

    private String name;
    private String password;

    public AdminModel(){}

    public AdminModel(AdminDO adminDO){
        if (adminDO != null){
            this.name = adminDO.getName();
            this.password = adminDO.getPassword();
        }
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
