package com.jas.web.service.impl;

import com.jas.web.bean.domain.TeacherDO;
import com.jas.web.bean.model.TeacherModel;
import com.jas.web.dao.ITeacherDAO;
import com.jas.web.service.ITeacherService;
import com.jas.web.utils.DateUtil;
import com.jas.web.utils.PaperUtil;
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


    @Transactional
    public void addTeacher(TeacherModel teacherModel) {
        Random random = new Random();
        teacherModel.setTeacherId(DateUtil.getStringDateByFormat(new Date(),"yyyyMMddHHmmss")+random.nextInt(10));
        teacherDAO.addTeacher(new TeacherDO(teacherModel));
    }

    @Transactional
    public void modifyTeacher(TeacherModel teacherModel) {
        teacherDAO.updateTeacher(new TeacherDO(teacherModel));
    }

    @Override
    public TeacherModel getByTeacherId(String teacherId) {
        TeacherDO teacher = teacherDAO.getTeacherByTeacherId(teacherId);
        TeacherModel teacherModel = new TeacherModel(teacher);
        return teacherModel;
    }

    @Override
    public PaperUtil<TeacherModel> getTeacherByPage(Integer currentPage, Integer pageSize) {
        PaperUtil<TeacherModel> paperUtil = new PaperUtil<>();
        int totalNum = teacherDAO.getTotalNum();
        int startRecord = (currentPage - 1) * pageSize > totalNum ? totalNum : (currentPage - 1) * pageSize;
        List<TeacherDO> teacherDOList = teacherDAO.listTeacherByPage(startRecord, pageSize, "id", "desc");
        List<TeacherModel> teacherModelList = new LinkedList<>();
        for (TeacherDO teacherDO : teacherDOList){
            teacherModelList.add(new TeacherModel(teacherDO));
        }
        paperUtil.setCurrentPage(currentPage);
        paperUtil.setData(teacherModelList);
        paperUtil.setPageSize(pageSize);
        paperUtil.setTotalRecord(totalNum);
        return paperUtil;
    }
}
