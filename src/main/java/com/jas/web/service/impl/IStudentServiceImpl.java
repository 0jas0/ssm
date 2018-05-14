package com.jas.web.service.impl;

import com.jas.web.bean.StudentDO;
import com.jas.web.dao.IStudentDAO;
import com.jas.web.enums.EEvaluateType;
import com.jas.web.exception.ParamNotValidException;
import com.jas.web.model.PaperModel;
import com.jas.web.model.StudentModel;
import com.jas.web.service.IStudentService;
import com.jas.web.utils.Md5Util;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.LinkedList;
import java.util.List;

@Service
public class IStudentServiceImpl implements IStudentService {
    @Resource
    IStudentDAO studentDAO;

    @Override
    @Transactional
    public void addStudent(StudentModel studentModel) {
        //验证学号是否已经存在
        StudentDO student = studentDAO.getStudentByStudentId(studentModel.getStudentId());
        if (student != null){
            throw new ParamNotValidException("该学号已经存在，请重新输入学号！");
        }
        //对密码进行md5加密
        studentModel.setPassword(Md5Util.md5Password(studentModel.getPassword()));
        studentModel.setEvaluate(EEvaluateType.UNASSESS.getValue());
        StudentDO studentDO = new StudentDO(studentModel);

        studentDAO.addStudent(studentDO);
    }

    @Override
    public StudentModel getStudentByStudentId(String studentId) {
        StudentDO studentDO = studentDAO.getStudentByStudentId(studentId);
        StudentModel studentModel = new StudentModel(studentDO);
        return studentModel;
    }

    @Override
    public PaperModel<StudentModel> getStudentByPage(Integer currentPage, Integer pageSize) {
        PaperModel<StudentModel> paperModel = new PaperModel<>();
        Integer totalNum = studentDAO.totalNum();
        Integer fromRecord = (currentPage - 1)*pageSize > totalNum? totalNum : (currentPage - 1)*pageSize;
        List<StudentDO> studentDOList = studentDAO.listStudentByPage(fromRecord, pageSize, "id", "desc");
        List<StudentModel> studentModelList = new LinkedList<>();
        for (StudentDO studentDO : studentDOList){
            studentModelList.add(new StudentModel(studentDO));
        }
        paperModel.setCurrentPage(currentPage);
        paperModel.setData(studentModelList);
        paperModel.setPageSize(pageSize);
        paperModel.setRecordTotal(totalNum);
        return paperModel;
    }

    @Override
    @Transactional
    public void updateStudent(StudentModel studentModel) {
        StudentDO studentDO = new StudentDO(studentModel);
        studentDAO.updateStudent(studentDO);
    }

    @Override
    @Transactional
    public void evaluateStudent(String studentId, Integer rank) {
        StudentDO studentDO = studentDAO.getStudentByStudentId(studentId);
        studentDO.setEvaluate(rank);
        studentDAO.updateStudent(studentDO);
    }

    @Override
    public void deleteSutdentById(String studentId) {
        studentDAO.deleteStudent(studentId);
    }
}
