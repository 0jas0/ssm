package com.jas.web.service.impl;

import com.github.tobato.fastdfs.domain.StorePath;
import com.github.tobato.fastdfs.service.FastFileStorageClient;
import com.jas.web.bean.FileDO;
import com.jas.web.dao.IFileDAO;
import com.jas.web.exception.ParamNotValidException;
import com.jas.web.service.IFileService;
import com.jas.web.utils.FileUtil;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class IFileServiceImpl implements IFileService{
    @Resource
    IFileDAO fileDAO;

    @Resource
    FastFileStorageClient fastFileStorageClient;

    private final static List<String> extList = new ArrayList<>();

    static {
        extList.add("jpg");
        extList.add("jpeg");
        extList.add("png");
        extList.add("gif");
        extList.add("bmp");
        extList.add("wbmp");
    }

    @Override
    public void uploadFile(MultipartFile image, Integer type, String studentId) throws IOException {
        FileDO fileDO = new FileDO();
        String filename = image.getOriginalFilename();
        String ext = FileUtil.getExt(filename);
        if(!extList.contains(ext)){
            throw new ParamNotValidException("文件格式不正确");
        }
        if (image.getInputStream().available() > 2*1024*1024){
            throw new ParamNotValidException("文件过大，最大允许2M");
        }
        fileDO.setFileType(type);
        fileDO.setStudentId(studentId);
        fileDO.setFileName(filename);
        //todo 把图片保存到文件服务器上
        StorePath storePath = fastFileStorageClient.uploadFile(null, image.getInputStream(), image.getInputStream().available(), ext);
        //设置图片的路径
        fileDO.setPath(storePath.getFullPath());
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
        fastFileStorageClient.deleteFile(path);
        //删除数据中的记录
        fileDAO.deleteFile(id);
    }

    @Override
    public String onlyUploadFile(MultipartFile image) throws IOException {
        String filename = image.getOriginalFilename();
        String ext = FileUtil.getExt(filename);
        if(!extList.contains(ext)){
            throw new ParamNotValidException("文件格式不正确");
        }
        if (image.getInputStream().available() > 2*1024*1024){
            throw new ParamNotValidException("文件过大，最大允许2M");
        }
         StorePath storePath = fastFileStorageClient.uploadFile(null, image.getInputStream(), image.getInputStream().available(), ext);;
         return storePath.getFullPath();
    }
}
