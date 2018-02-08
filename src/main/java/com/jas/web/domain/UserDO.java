package com.jas.web.domain;

public class UserDO {
    public Integer id;
    public String username;
    public Integer isDel;
    public Integer modtime;
    public Integer addtime;

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

    public Integer getIsDel() {
        return isDel;
    }

    public void setIsDel(Integer isDel) {
        this.isDel = isDel;
    }

    public Integer getModtime() {
        return modtime;
    }

    public void setModtime(Integer modtime) {
        this.modtime = modtime;
    }

    public Integer getAddtime() {
        return addtime;
    }

    public void setAddtime(Integer addtime) {
        this.addtime = addtime;
    }
}
