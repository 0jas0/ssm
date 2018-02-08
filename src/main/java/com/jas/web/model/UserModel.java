package com.jas.web.model;

import com.jas.web.domain.UserDO;

public class UserModel {
    public Integer id;
    public String username;

    public UserModel() {
    }
    public UserModel(UserDO userDO) {
        id = userDO.getId();
        username = userDO.getUsername();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
