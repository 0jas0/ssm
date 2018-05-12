package com.jas.web.service;

import com.jas.web.bean.model.StudentModel;
import com.jas.web.bean.model.TeacherModel;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface IStudentService {
    StudentModel getStudentByStudentId(String studentId);

    void addStudent(MultipartFile image, StudentModel studentModel) throws IOException;

    void modifyStudent(MultipartFile image, StudentModel studentModel) throws IOException;
}
