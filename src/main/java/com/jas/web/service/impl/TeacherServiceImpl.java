package com.jas.web.service.impl;

import com.jas.web.bean.domain.TeacherDO;
import com.jas.web.bean.model.TeacherModel;
import com.jas.web.bean.param.TeacherParam;
import com.jas.web.dao.ITeacherDAO;
import com.jas.web.service.ITeacherService;
import com.jas.web.utils.DateUtil;
import com.jas.web.utils.StringUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

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

    @Transactional
    public void addTeacher(TeacherParam teacherParam) {
        TeacherModel model = teacherParam.getModel();
        Random random = new Random();
        model.setTeacherId(DateUtil.getStringDateByFormat(new Date(),"yyyyMMddHHmmss")+random.nextInt(10));
        teacherDAO.addTeacher(new TeacherDO(model));
    }

    @Transactional
    public void modifyTeacher(TeacherParam teacherParam) {
        TeacherModel model = teacherParam.getModel();
        teacherDAO.updateTeacher(new TeacherDO(model));
    }

    @Override
    public TeacherModel getByTeacherId(String teacherId) {
        TeacherDO teacher = teacherDAO.getTeacherByTeacherId(teacherId);
        TeacherModel teacherModel = new TeacherModel(teacher);
        return teacherModel;
    }
}
