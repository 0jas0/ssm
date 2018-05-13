package com.jas.web.bean.model;

import com.jas.web.bean.domain.BaseDO;
import com.jas.web.bean.domain.CollegeMajorDO;

public class CollegeMajorModel{
    private Integer id;
    private String name;
    private Integer parentId;

    public CollegeMajorModel() {
    }


    public CollegeMajorModel(CollegeMajorDO collegeMajorDO) {
        if (collegeMajorDO != null){
            this.id = collegeMajorDO.getId();
            this.name = collegeMajorDO.getName();
            this.parentId = collegeMajorDO.getParentId();
        }
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getParentId() {
        return parentId;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }
}
