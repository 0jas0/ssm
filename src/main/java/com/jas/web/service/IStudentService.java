package com.jas.web.service;

import com.jas.web.model.PaperModel;
import com.jas.web.model.StudentModel;

import java.awt.print.Paper;
import java.util.List;

public interface IStudentService {
    public void addStudent(StudentModel studentModel);

    public StudentModel getStudentByStudentId(String studentId);

    public PaperModel<StudentModel> getStudentByPage(Integer currentPage, Integer pageSize);

    public void updateStudent(StudentModel studentModel);

    public void evaluateStudent(String studentId,Integer rank);
}
