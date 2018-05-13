package com.jas.web.service.impl;

import com.jas.web.bean.domain.AdministrativeClassDO;
import com.jas.web.bean.model.AdministrativeClassModel;
import com.jas.web.dao.IAdministrativeClassDAO;
import com.jas.web.service.IAdministrativeClassService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service
public class AdministrativeClassServiceImpl implements IAdministrativeClassService {
    @Resource
    IAdministrativeClassDAO administrativeClassDAO;

    @Override
    @Transactional
    public void addAdministrativeClass(AdministrativeClassModel administrativeClassModel) {
        administrativeClassDAO.addAdministrativeClass(new AdministrativeClassDO(administrativeClassModel));
    }

    @Override
    @Transactional
    public void modifyAdministrativeClass(AdministrativeClassModel administrativeClassModel) {
        administrativeClassDAO.updateAdministrativeClass(new AdministrativeClassDO(administrativeClassModel));
    }

    @Override
    public AdministrativeClassModel getByClassId(String classId) {
        AdministrativeClassDO administrativeClassDO = administrativeClassDAO.getAdminstrativeClassByClassId(classId);
        if (administrativeClassDO != null){
            return new AdministrativeClassModel(administrativeClassDO);
        }
        return null;
    }

    @Override
    public List<AdministrativeClassModel> getByCollegeMajor(Integer college, Integer major) {
        List<AdministrativeClassDO> administrativeClassDOList =  administrativeClassDAO.getAdminstrativeClasssByCollegeMajor(college, major);
        List<AdministrativeClassModel> administrativeClassModels = new ArrayList<>();
        for (AdministrativeClassDO administrativeClassDO : administrativeClassDOList){
            administrativeClassModels.add(new AdministrativeClassModel(administrativeClassDO));
        }
        return administrativeClassModels;
    }


}
