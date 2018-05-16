package com.jas.web.service;

import com.jas.web.bean.model.AdministrativeClassModel;
import com.jas.web.utils.PaperUtil;

import java.util.List;

public interface IAdministrativeClassService {
    void addAdministrativeClass(AdministrativeClassModel administrativeClassModel);

    void modifyAdministrativeClass(AdministrativeClassModel administrativeClassModel);

    AdministrativeClassModel getByClassId(String classId);

    AdministrativeClassModel getById(Integer id);

    List<AdministrativeClassModel> getByCollegeMajor(Integer college, Integer major);

    PaperUtil<AdministrativeClassModel> getClassByPage(Integer currentPage, Integer pageSize);

    void deleteClass(Integer id);

    List<AdministrativeClassModel> getByCollege(Integer college);
}
