package com.jas.web.service.impl;

import com.jas.web.bean.domain.CollegeMajorDO;
import com.jas.web.bean.model.CollegeMajorModel;
import com.jas.web.dao.ICollegeMajorDAO;
import com.jas.web.service.ICollegeMajorService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

@Service
public class CollegeMajorServiceImpl implements ICollegeMajorService{
    @Resource
    ICollegeMajorDAO collegeMajorDAO;

    @Override
    public List<CollegeMajorModel> getCollege() {
        List<CollegeMajorDO> majorDOS = collegeMajorDAO.getByParentId(0);
        List<CollegeMajorModel> majorModelList = new ArrayList<>();
        for (CollegeMajorDO collegeMajorDO : majorDOS){
            majorModelList.add(new CollegeMajorModel(collegeMajorDO));
        }
        return majorModelList;
    }

    @Override
    public List<CollegeMajorModel> getMajorByParentId(Integer parentId) {
        List<CollegeMajorDO> majorDOS = collegeMajorDAO.getByParentId(parentId);
        List<CollegeMajorModel> majorModelList = new ArrayList<>();
        for (CollegeMajorDO collegeMajorDO : majorDOS){
            majorModelList.add(new CollegeMajorModel(collegeMajorDO));
        }
        return majorModelList;
    }

}
