package com.jas.web.service;

import com.jas.web.bean.model.TeacherModel;
import com.jas.web.utils.PaperUtil;

import java.util.List;

public interface ITeacherService {
    public void addTeacher(TeacherModel teacherModel);

    public void modifyTeacher(TeacherModel teacherModel);

    TeacherModel getByTeacherId(String teacherId);

    PaperUtil<TeacherModel> getTeacherByPage(Integer currentPage, Integer pageSize);

    void deleteTeacher(String teacherId);

    List<TeacherModel> getAllTeacher();
}
