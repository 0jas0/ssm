package com.jas.web.service;

import com.jas.web.bean.model.TeacherModel;
import com.jas.web.bean.param.TeacherParam;

import java.util.List;

public interface ITeacherService {
    public List<TeacherModel> listTeacherAll();

    public void addTeacher(TeacherParam teacherParam);

    public void modifyTeacher(TeacherParam teacherParam);

    TeacherModel getByTeacherId(String teacherId);
}
