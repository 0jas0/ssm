package com.jas.web.service.impl;

import com.github.tobato.fastdfs.domain.FileInfo;
import com.github.tobato.fastdfs.domain.StorePath;
import com.github.tobato.fastdfs.exception.FdfsServerException;
import com.github.tobato.fastdfs.service.FastFileStorageClient;
import com.jas.web.bean.domain.*;
import com.jas.web.bean.model.StudentModel;
import com.jas.web.bean.model.TeacherModel;
import com.jas.web.dao.*;
import com.jas.web.exception.ParamNotValidException;
import com.jas.web.service.IStudentService;
import com.jas.web.utils.FileUtil;
import com.jas.web.utils.PaperUtil;
import com.jas.web.utils.StringUtil;
import com.sun.imageio.plugins.jpeg.JPEG;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.awt.*;
import java.io.IOException;
import java.io.InputStream;
import java.util.*;
import java.util.List;

@Service
public class StudentServiceImpl implements IStudentService{
    @Resource
    IStudentDAO studentDAO;

    @Resource
    ICollegeMajorDAO collegeMajorDAO;

    @Resource
    IAdministrativeClassDAO administrativeClassDAO;

    @Resource
    FastFileStorageClient fastFileStorageClient;

    @Resource
    ICourseTimePlaceDAO courseTimePlaceDAO;

    @Resource
    IChoiceCoursesDAO choiceCoursesDAO;

    @Resource
    IScoreDAO scoreDAO;
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
        if (studentDO != null){
            return new StudentModel(studentDO);
        }
        return null;
    }

    @Override
    @Transactional
    public void addStudent(StudentModel studentModel) throws IOException {
        //设置密码
        studentModel.setPassword(StringUtil.md5Password(studentModel.getPassword()));
        studentDAO.addStudent(new StudentDO(studentModel));
    }

    @Override
    @Transactional
    public void modifyStudent(StudentModel studentModel) throws IOException {
        StudentDO studentDO = studentDAO.getStudentByStudentId(studentModel.getStudentId());
        if (StringUtil.isEmpty(studentModel.getPhoto())){
            studentModel.setPhoto(studentDO.getPhoto());
        }
        if (StringUtil.isEmpty(studentModel.getPassword())){
            studentModel.setPassword(studentDO.getPassword());
        }
        studentDAO.updateStudent(new StudentDO(studentModel));
    }

    @Override
    public PaperUtil<StudentModel> getStudentByPage(Integer currentPage, Integer pageSize) {
        PaperUtil<StudentModel> paperUtil = new PaperUtil<>();
        int totalNum = studentDAO.getTotalNum();
        int startRecord = (currentPage - 1) * pageSize > totalNum ? totalNum : (currentPage - 1) * pageSize;
        List<StudentDO> studentDOList = studentDAO.listStudentByPage(startRecord, pageSize, "id", "desc");
        List<CollegeMajorDO> collegeMajor = collegeMajorDAO.getAllCollegeMajor();
        Map<Integer,CollegeMajorDO> collegeMajorDOMap = new HashMap<>();
        for (CollegeMajorDO collegeMajorDO : collegeMajor){
            collegeMajorDOMap.put(collegeMajorDO.getId(), collegeMajorDO);
        }
        List<AdministrativeClassDO> adminstrativeClassAll = administrativeClassDAO.getAdminstrativeClassAll();
        Map<Integer, AdministrativeClassDO> administrativeClassDOMap = new HashMap<>();
        for (AdministrativeClassDO administrativeClassDO : adminstrativeClassAll){
            administrativeClassDOMap.put(administrativeClassDO.getId(),administrativeClassDO);
        }

        List<StudentModel> studentModels = new LinkedList<>();
        for (StudentDO studentDO : studentDOList){
            StudentModel studentModel = new StudentModel(studentDO);
            if (collegeMajorDOMap.get(studentDO.getCollege()) != null){
                studentModel.setCollegeName(collegeMajorDOMap.get(studentDO.getCollege()).getName());
            }
            if (collegeMajorDOMap.get(studentDO.getMajor()) != null){
                studentModel.setMajorName(collegeMajor.get(studentDO.getMajor()).getName());
            }
            if (administrativeClassDOMap.get(studentDO.getClassId()) != null){
                studentModel.setClassName(administrativeClassDOMap.get(studentDO.getClassId()).getName());
            }
            studentModels.add(studentModel);
        }
        paperUtil.setCurrentPage(currentPage);
        paperUtil.setData(studentModels);
        paperUtil.setPageSize(pageSize);
        paperUtil.setTotalRecord(totalNum);
        return paperUtil;
    }

    public String uploadFile(MultipartFile image) throws IOException {
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

    @Override
    public String reUploadFile(MultipartFile image, String student) throws IOException {
        StudentDO studentDO = studentDAO.getStudentByStudentId(student);
        //删除以前的文件
        fastFileStorageClient.deleteFile(studentDO.getPhoto());
        String path = uploadFile(image);
        return path;
    }

    @Override
    @Transactional
    public void deleteStudent(String studentId) {
        studentDAO.deleteStudent(studentId);
    }

    @Override
    public StudentModel getStudentById(Integer studentId) {
        StudentDO studentDO = studentDAO.getStudentById(studentId);
        StudentModel studentModel = new StudentModel(studentDO);
        return studentModel;
    }

    @Override
    public List<StudentModel> getStudentByCourseId(Integer courseId) {
        List<Integer> classList = courseTimePlaceDAO.getClassIdByCourseId(courseId);
        List<StudentDO> studentDOList = studentDAO.getAllStudent();
        List<ScoreDO> scoreByCourseId = scoreDAO.getScoreByCourse(courseId);
        List<Integer> unContainStudentId = new LinkedList<>();
        for (ScoreDO scoreDO : scoreByCourseId){
            unContainStudentId.add(scoreDO.getStudentId());
        }
        Map<Integer,List<StudentDO>> studentMap = new LinkedHashMap<>();
        Map<Integer,StudentDO> studentDOMap = new LinkedHashMap<>();
        for (StudentDO studentDO : studentDOList){
            studentDOMap.put(studentDO.getId(),studentDO);
            List<StudentDO> studentDOS = studentMap.get(studentDO.getClassId());
            if (studentDOS == null){
                studentDOS = new LinkedList<>();
                studentMap.put(studentDO.getClassId(), studentDOS);
            }
            studentDOS.add(studentDO);
        }

        List<StudentDO> studentDOS = new LinkedList<>();
        for (Integer classId : classList){
            if (classId == null){
                continue;
            }
            List<StudentDO> studentDOS1 = studentMap.get(classId);
            studentDOS.addAll(studentDOS1);
        }
        //选修的人数
        List<ChoiceCoursesDO> choiceCoursesDOS = choiceCoursesDAO.getChoiceByCourseId(courseId);
        for (ChoiceCoursesDO choiceCoursesDO : choiceCoursesDOS){
            StudentDO studentDO = studentDOMap.get(choiceCoursesDO.getStudentId());
            studentDOS.add(studentDO);
        }
        List<StudentModel> studentModels = new LinkedList<>();
        for (StudentDO studentDO : studentDOS){
            if (unContainStudentId.contains(studentDO.getId())){
                continue;
            }
            studentModels.add(new StudentModel(studentDO));
        }
        return studentModels;
    }
}
