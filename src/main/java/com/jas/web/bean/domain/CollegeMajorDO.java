package com.jas.web.bean.domain;

import com.jas.web.bean.model.CollegeMajorModel;

public class CollegeMajorDO extends BaseDO{

    private String name;
    private Integer parentId;

    public CollegeMajorDO() {
    }

    public CollegeMajorDO(CollegeMajorModel collegeMajorModel) {
        if (collegeMajorModel != null){
            this.name = collegeMajorModel.getName();
            this.parentId = collegeMajorModel.getParentId();
        }
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
