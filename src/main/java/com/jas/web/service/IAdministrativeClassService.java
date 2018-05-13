package com.jas.web.service;

import com.jas.web.bean.model.AdministrativeClassModel;

import java.util.List;

public interface IAdministrativeClassService {
    void addAdministrativeClass(AdministrativeClassModel administrativeClassModel);

    void modifyAdministrativeClass(AdministrativeClassModel administrativeClassModel);

    AdministrativeClassModel getByClassId(String classId);

    List<AdministrativeClassModel> getByCollegeMajor(Integer college, Integer major);
}
