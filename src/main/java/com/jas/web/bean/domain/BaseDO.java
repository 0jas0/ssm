package com.jas.web.bean.domain;

public class BaseDO {
    private Integer id;
    private Integer isDel;
    private Long addtime;
    private Long modtime;

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

    public Long getAddtime() {
        return addtime;
    }

    public void setAddtime(Long addtime) {
        this.addtime = addtime;
    }

    public Long getModtime() {
        return modtime;
    }

    public void setModtime(Long modtime) {
        this.modtime = modtime;
    }
}
