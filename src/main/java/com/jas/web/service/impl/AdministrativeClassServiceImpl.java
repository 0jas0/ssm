package com.jas.web.service.impl;

import com.jas.web.bean.domain.AdministrativeClassDO;
import com.jas.web.bean.domain.CollegeMajorDO;
import com.jas.web.bean.domain.StudentDO;
import com.jas.web.bean.model.AdministrativeClassModel;
import com.jas.web.bean.model.StudentModel;
import com.jas.web.dao.IAdministrativeClassDAO;
import com.jas.web.dao.ICollegeMajorDAO;
import com.jas.web.service.IAdministrativeClassService;
import com.jas.web.utils.PaperUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.*;

@Service
public class AdministrativeClassServiceImpl implements IAdministrativeClassService {
    @Resource
    IAdministrativeClassDAO administrativeClassDAO;

    @Resource
    ICollegeMajorDAO collegeMajorDAO;

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
    public AdministrativeClassModel getById(Integer id) {
        AdministrativeClassDO administrativeClassDO = administrativeClassDAO.getAdminstrativeClassById(id);
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

    @Override
    public PaperUtil<AdministrativeClassModel> getClassByPage(Integer currentPage, Integer pageSize) {
        PaperUtil<AdministrativeClassModel> paperUtil = new PaperUtil<>();
        int totalNum = administrativeClassDAO.getTotalNum();
        int startRecord = (currentPage - 1) * pageSize > totalNum ? totalNum : (currentPage - 1) * pageSize;
        List<AdministrativeClassDO> administrativeClassDOS = administrativeClassDAO.listByPage(startRecord, pageSize, "id", "desc");
        List<CollegeMajorDO> collegeMajor = collegeMajorDAO.getAllCollegeMajor();
        Map<Integer,CollegeMajorDO> collegeMajorDOMap = new HashMap<>();
        for (CollegeMajorDO collegeMajorDO : collegeMajor){
            collegeMajorDOMap.put(collegeMajorDO.getId(), collegeMajorDO);
        }
        List<AdministrativeClassModel> administrativeClassModels = new LinkedList<>();
        for (AdministrativeClassDO administrativeClassDO : administrativeClassDOS){
            AdministrativeClassModel administrativeClassModel = new AdministrativeClassModel(administrativeClassDO);
            if (collegeMajorDOMap.get(administrativeClassDO.getCollege()) != null){
                administrativeClassModel.setCollegeName(collegeMajorDOMap.get(administrativeClassDO.getCollege()).getName());
            }
            if (collegeMajorDOMap.get(administrativeClassDO.getMajor()) != null){
                administrativeClassModel.setMajorName(collegeMajorDOMap.get(administrativeClassDO.getMajor()).getName());
            }
            administrativeClassModels.add(administrativeClassModel);
        }
        paperUtil.setCurrentPage(currentPage);
        paperUtil.setData(administrativeClassModels);
        paperUtil.setPageSize(pageSize);
        paperUtil.setTotalRecord(totalNum);
        return paperUtil;
    }

    @Override
    @Transactional
    public void deleteClass(Integer id) {
        administrativeClassDAO.deleteById(id);
    }

    @Override
    public List<AdministrativeClassModel> getByCollege(Integer college) {
        List<AdministrativeClassDO> administrativeClassDOList =  administrativeClassDAO.getAdminstrativeClasssByCollege(college);
        List<AdministrativeClassModel> administrativeClassModels = new ArrayList<>();
        for (AdministrativeClassDO administrativeClassDO : administrativeClassDOList){
            administrativeClassModels.add(new AdministrativeClassModel(administrativeClassDO));
        }
        return administrativeClassModels;
    }


}
