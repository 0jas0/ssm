package com.jas.web.service;

import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

public interface IFileService {
    /**
     * 上传文件
     * @param  uploadFile 上传的文件
     * @return 上传信息
     */
    Map<String,Object> uploadFile(MultipartFile uploadFile) throws Exception;

    /**
     * 删除文件
     * @param  fileName 文件名
     * @return boolean
     */
    boolean deleteFile(String fileName) throws Exception;
}
