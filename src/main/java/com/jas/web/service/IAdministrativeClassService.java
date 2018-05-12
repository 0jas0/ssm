package com.jas.web.service;

import com.jas.web.bean.model.AdministrativeClassModel;

public interface IAdministrativeClassService {
    void addAdministrativeClass(AdministrativeClassModel administrativeClassModel);

    void modifyAdministrativeClass(AdministrativeClassModel administrativeClassModel);

    AdministrativeClassModel getByClassId(String classId);
}
