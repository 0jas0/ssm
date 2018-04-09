package com.jas.web.bean.domain;

public class BaseDO {
    private Integer id;
    private Integer isDel;
    private Integer addtime;
    private Integer modtime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getIsDel() {
        return isDel;
    }

    public void setIsDel(Integer isDel) {
        this.isDel = isDel;
    }

    public Integer getAddtime() {
        return addtime;
    }

    public void setAddtime(Integer addtime) {
        this.addtime = addtime;
    }

    public Integer getModtime() {
        return modtime;
    }

    public void setModtime(Integer modtime) {
        this.modtime = modtime;
    }
}
