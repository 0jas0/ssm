package com.jas.web.bean.param;

import com.jas.web.bean.domain.BaseDO;
import com.jas.web.bean.model.AdminModel;

public class AdminParam{

    private String name;
    private String password;

    public AdminParam(){}

    public AdminModel getModel(){
        AdminModel adminModel = new AdminModel();
        adminModel.setName(name);
        adminModel.setPassword(password);
        return adminModel;
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
