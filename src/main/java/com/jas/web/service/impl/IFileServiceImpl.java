package com.jas.web.service.impl;

import com.jas.web.bean.FileDO;
import com.jas.web.dao.IFileDAO;
import com.jas.web.service.IFileService;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.util.List;

@Service
public class IFileServiceImpl implements IFileService{
    @Resource
    IFileDAO fileDAO;

    @Override
    public void uploadFile(MultipartFile image, Integer type, String studentId) {
        FileDO fileDO = new FileDO();
        String filename = image.getOriginalFilename();
        fileDO.setFileType(type);
        fileDO.setStudentId(studentId);
        fileDO.setFileName(filename);
        //设置图片的路径
        fileDO.setPath("");
        //todo 把图片保存到文件服务器上

        fileDAO.addFile(fileDO);
    }

    @Override
    public List<FileDO> getFilesByStudentId(String studentId) {
        List<FileDO> fileDOList = fileDAO.getFileByStudentId(studentId);
        return fileDOList;
    }

    @Override
    public void deleteImage(Integer id) {
        FileDO fileDO = fileDAO.getFileById(id);
        //todo 获取文件在文件服务上的位置，删除在文件服务器上的文件
        String path = fileDO.getPath();


        //删除数据中的记录
        fileDAO.deleteFile(id);
    }
}
