package com.jas.web.service;

import com.jas.web.model.StudentModel;

public interface IStudentService {
    public void addStudent(StudentModel studentModel);

    public StudentModel getStudentByStudentId(String studentId);
}
