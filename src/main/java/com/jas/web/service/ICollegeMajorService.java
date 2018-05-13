package com.jas.web.service;

import com.jas.web.bean.model.CollegeMajorModel;

import java.util.List;

public interface ICollegeMajorService {
    List<CollegeMajorModel> getCollege();

    List<CollegeMajorModel> getMajorByParentId(Integer parentId);
}
