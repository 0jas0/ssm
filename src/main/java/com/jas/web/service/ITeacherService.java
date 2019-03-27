package com.jas.web.service;

import com.jas.web.bean.model.EvalutionModel;
import com.jas.web.bean.model.EvalutionTeacherModel;
import com.jas.web.bean.model.TeacherModel;
import com.jas.web.utils.PaperUtil;

import java.util.List;

public interface ITeacherService {
    public void addTeacher(TeacherModel teacherModel);

    public void modifyTeacher(TeacherModel teacherModel);

    TeacherModel getByTeacherId(String teacherId);

    TeacherModel getById(Integer teacherId);

    PaperUtil<TeacherModel> getTeacherByPage(String keyword, Integer currentPage, Integer pageSize);

    void deleteTeacher(String teacherId);

    List<TeacherModel> getAllTeacher();

    List<EvalutionModel> studentEvaluationTeacherList(Integer studentId);

    PaperUtil<EvalutionTeacherModel> getEvaluationByTeacherIdAndPage(String teacherId, Integer currentPage, Integer pageSize);
}
