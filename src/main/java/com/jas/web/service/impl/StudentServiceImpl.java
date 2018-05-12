package com.jas.web.service.impl;

import com.github.tobato.fastdfs.domain.StorePath;
import com.github.tobato.fastdfs.service.FastFileStorageClient;
import com.jas.web.bean.domain.StudentDO;
import com.jas.web.bean.model.StudentModel;
import com.jas.web.bean.model.TeacherModel;
import com.jas.web.dao.IStudentDAO;
import com.jas.web.exception.ParamNotValidException;
import com.jas.web.service.IStudentService;
import com.jas.web.utils.FileUtil;
import com.jas.web.utils.StringUtil;
import com.sun.imageio.plugins.jpeg.JPEG;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

@Service
public class StudentServiceImpl implements IStudentService{
    @Resource
    IStudentDAO studentDAO;

    @Resource
    FastFileStorageClient fastFileStorageClient;

    private static final List<String> allowExt = new ArrayList<>();

    static {
        allowExt.add("jpg");
        allowExt.add("jpeg");
        allowExt.add("png");
        allowExt.add("gif");
        allowExt.add("bmp");
    }

    @Override
    public StudentModel getStudentByStudentId(String studentId) {
        StudentDO studentDO = studentDAO.getStudentByStudentId(studentId);
        return new StudentModel(studentDO);
    }

    @Override
    @Transactional
    public void addStudent(MultipartFile image, StudentModel studentModel) throws IOException {
        String fullPath = this.uploadFile(image);
        studentModel.setPhoto(fullPath);
        //设置密码
        studentModel.setPassword(StringUtil.md5Password(studentModel.getPassword()));
        studentDAO.addStudent(new StudentDO(studentModel));
    }

    @Override
    @Transactional
    public void modifyStudent(MultipartFile image, StudentModel studentModel) throws IOException {
        StudentDO studentDO = studentDAO.getStudentByStudentId(studentModel.getStudentId());
        if (image == null || StringUtil.isEmpty(image.getOriginalFilename())){
            studentModel.setPhoto(studentDO.getPhoto());
        }else {
            //删除以前的文件
            fastFileStorageClient.deleteFile(studentDO.getPhoto());
            String path = uploadFile(image);
            studentModel.setPhoto(path);
        }
        if (StringUtil.isEmpty(studentModel.getPassword())){
            studentModel.setPassword(studentDO.getPassword());
        }
        studentDAO.updateStudent(new StudentDO(studentModel));
    }

    private String uploadFile(MultipartFile image) throws IOException {
        String fileName = image.getOriginalFilename();
        String ext = FileUtil.getExt(fileName);
        InputStream inputStream = image.getInputStream();
        int size = inputStream.available();
        if (!allowExt.contains(ext)){
            throw new ParamNotValidException("不支持该格式文件上传");
        }
        if (size > 2 * 1024 * 1024){
            throw new ParamNotValidException("上传的文件大小不能超过2M");
        }
        //文件上传
        StorePath storePath = fastFileStorageClient.uploadFile(null, inputStream, size, ext);
        return storePath.getFullPath();
    }

}
