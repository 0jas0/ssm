package com.jas.web.service.impl;

import com.jas.web.bean.StudentDO;
import com.jas.web.dao.IStudentDAO;
import com.jas.web.exception.ParamNotValidException;
import com.jas.web.model.StudentModel;
import com.jas.web.service.IStudentService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class IStudentServiceImpl implements IStudentService {
    @Resource
    IStudentDAO studentDAO;

    public void addStudent(StudentModel studentModel) {
        //验证学号是否已经存在
        StudentDO student = studentDAO.getStudentByStudentId(studentModel.getStudentId());
        if (student != null){
            throw new ParamNotValidException("该学号已经存在，请重新输入学号！");
        }
        StudentDO studentDO = new StudentDO(studentModel);
        studentDAO.addStudent(studentDO);
    }

    public StudentModel getStudentByStudentId(String studentId) {
        StudentDO studentDO = studentDAO.getStudentByStudentId(studentId);
        StudentModel studentModel = new StudentModel(studentDO);
        return studentModel;
    }
}
