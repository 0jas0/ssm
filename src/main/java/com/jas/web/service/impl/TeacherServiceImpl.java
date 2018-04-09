package com.jas.web.service.impl;

import com.jas.web.bean.domain.TeacherDO;
import com.jas.web.bean.model.TeacherModel;
import com.jas.web.dao.ITeacherDAO;
import com.jas.web.service.ITeacherService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.LinkedList;
import java.util.List;

@Service
public class TeacherServiceImpl implements ITeacherService{

    @Resource
    private ITeacherDAO teacherDAO;

    public List<TeacherModel> listTeacherAll() {
        List<TeacherDO> listTeacherAll = teacherDAO.listTeacherAll();
        List<TeacherModel> teacherModelList = new LinkedList<TeacherModel>();
        for (TeacherDO teacherDO : listTeacherAll){
            teacherModelList.add(new TeacherModel(teacherDO));
        }
        return teacherModelList;
    }
}
