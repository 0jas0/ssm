package com.jas.web.service;

import com.jas.web.bean.FileDO;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;

public interface IFileService {
    public void uploadFile(MultipartFile image, Integer type,String studentId) throws IOException;

    public List<FileDO> getFilesByStudentId(String studentId);

    public void deleteImage(Integer id);

    String onlyUploadFile(MultipartFile image) throws IOException;
}
