package com.jas.web.service.impl;

import com.jas.web.bean.domain.StudentUploadDO;
import com.jas.web.bean.domain.TopicPhaseTimeDO;
import com.jas.web.bean.domain.UsernameDO;
import com.jas.web.bean.model.StudentUploadModel;
import com.jas.web.dao.ITopicDAO;
import com.jas.web.dao.ITopicPhaseTimeDAO;
import com.jas.web.dao.StudentUploadDAO;
import com.jas.web.service.IStudentUploadService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.LinkedList;
import java.util.List;

@Service
public class StudentUploadServiceImpl implements IStudentUploadService{

    @Resource
    StudentUploadDAO studentUploadDAO;
    @Resource
    ITopicDAO topicDAO;
    @Resource
    ITopicPhaseTimeDAO topicPhaseTimeDAO;
    @Override
    public List<StudentUploadModel> getUploadFileByStudentId(String s) {
        List<StudentUploadDO> list = studentUploadDAO.getByStudentId(s);
        List<StudentUploadModel> studentUploadModels = new LinkedList<>();
        String topicId = topicDAO.getTopicIdByStudentId(s);
        TopicPhaseTimeDO topicPhaseTimeDO = topicPhaseTimeDAO.getById(topicId);

        for (StudentUploadDO studentUploadDO : list){

            StudentUploadModel studentUploadModel = new StudentUploadModel();
            studentUploadModel.setPhase(studentUploadDO.getStatus());
            studentUploadModel.setFileName(studentUploadDO.getName());
            studentUploadModel.setPath(studentUploadDO.getPath());
            studentUploadModel.setStatus(1);
            if (studentUploadDO.getScore() == null || studentUploadDO.getScore().compareTo(60.0D) < 0){
                studentUploadModel.setStatus(0);
            }
            if (studentUploadDO.getUploadTime().compareTo(topicPhaseTimeDO.getStartTime()) < 0 || studentUploadDO.getUploadTime().compareTo(topicPhaseTimeDO.getEndTime()) > 0){
                studentUploadModel.setStatus(0);
            }
            studentUploadModels.add(studentUploadModel);
        }
        return studentUploadModels;
    }
}
