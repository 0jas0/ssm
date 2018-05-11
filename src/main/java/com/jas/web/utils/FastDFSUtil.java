package com.jas.web.utils;

import org.apache.commons.io.FilenameUtils;
import org.csource.fastdfs.ClientGlobal;
import org.csource.fastdfs.StorageClient1;
import org.csource.fastdfs.TrackerClient;
import org.csource.fastdfs.TrackerServer;
import org.springframework.core.io.ClassPathResource;

public class FastDFSUtil {

    public static String uploadPicToFDFS(byte[] file_buff,String filename) throws Exception{
        //1.创建classPathResouce对象，用于加载配置文件
        ClassPathResource resource = new ClassPathResource("fastdfs-client.properties");
        //2.初始化配置文件
        ClientGlobal.init(resource.getClassLoader().getResource("fastdfs-client.properties").getPath());
        //3.获取跟踪服务器的客户端 
        TrackerClient trackerClient = new TrackerClient();
        //4.通过跟踪服务器的客户端获取服务端 
        TrackerServer trackerServer = trackerClient.getConnection();
        //5通过跟踪服务器的服务端获取存储服务器的客户端 
        StorageClient1 storageClient1 = new StorageClient1(trackerServer,null);
        //6.将附件上传至FastDFS
        String  file_ext_name= FilenameUtils.getExtension(filename);
        String path=storageClient1.upload_file1(file_buff, file_ext_name, null);
        return path;
    }
}