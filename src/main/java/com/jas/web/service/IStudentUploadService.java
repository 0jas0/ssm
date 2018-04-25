package com.jas.web.service;

import com.jas.web.bean.model.StudentUploadModel;

import java.util.List;

public interface IStudentUploadService {
    List<StudentUploadModel> getUploadFileByStudentId(String s);

}
