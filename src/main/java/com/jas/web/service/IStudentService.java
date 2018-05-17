package com.jas.web.service;

import com.jas.web.bean.model.StudentModel;
import com.jas.web.bean.model.TeacherModel;
import com.jas.web.utils.PaperUtil;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface IStudentService {
    StudentModel getStudentByStudentId(String studentId);

    void addStudent(StudentModel studentModel) throws IOException;

    void modifyStudent(StudentModel studentModel) throws IOException;

    PaperUtil<StudentModel> getStudentByPage(Integer currentPage, Integer pageSize);

    String uploadFile(MultipartFile image) throws IOException;

    String reUploadFile(MultipartFile image, String student) throws IOException;

    void deleteStudent(String studentId);

    StudentModel getStudentById(Integer studentId);

    List<StudentModel> getStudentByCourseId(Integer courseId);
}
