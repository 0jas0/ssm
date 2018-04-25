package com.jas.web.service.impl;

import com.github.pagehelper.PageHelper;
import com.jas.web.bean.domain.StudentTopicDO;
import com.jas.web.bean.domain.TopicDO;
import com.jas.web.bean.domain.UsernameDO;
import com.jas.web.bean.model.ElectiveCourseModle;
import com.jas.web.bean.model.TopicModel;
import com.jas.web.bean.model.UsernameModel;
import com.jas.web.bean.param.UsernameParam;
import com.jas.web.dao.ITopicDAO;
import com.jas.web.dao.IUsernameDAO;
import com.jas.web.enums.ERole;
import com.jas.web.exception.ParamNotValidException;
import com.jas.web.service.ITopicService;
import com.jas.web.utils.EUDataGridResult;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.LinkedList;
import java.util.List;

@Service
public class TopicServiceImpl implements ITopicService{
    @Resource
    ITopicDAO topicDAO;

    @Resource
    IUsernameDAO usernameDAO;

    @Transactional
    public int insert(TopicModel topicModel) {
        topicModel.setStatus(0);
        int id = topicDAO.insert(new TopicDO(topicModel));
        return id;
    }

    @Transactional
    public void update(TopicModel topicModel){
        TopicDO topicDO = topicDAO.getById(topicModel.getTopicId());
        topicDO.setLimitStudent(topicModel.getLimitStudent());
        topicDO.setMajor(topicModel.getMajor());
        topicDO.setRemark(topicModel.getRemark());
        topicDO.setTitle(topicModel.getTitle());
        topicDAO.upload(topicDO);
    }

    public TopicModel getByTopicId(String topicId){
        TopicDO topicDO = topicDAO.getById(topicId);
        TopicModel topicModel = new TopicModel(topicDO);
        return topicModel;
    }

    public EUDataGridResult getList(Integer page, Integer rows) {
        PageHelper.startPage(page, rows);
        List<TopicDO> topicDOS = topicDAO.getAll();


        List<TopicModel> list = new LinkedList<>();
        for (TopicDO topicDO : topicDOS){
            list.add(new TopicModel(topicDO));
        }
        EUDataGridResult result = new EUDataGridResult();
        int end = page*rows > list.size() ? list.size() : page*rows;
        int start = (page - 1) * rows < 0 ? 0 : ((page - 1) * rows > list.size() ? list.size() : (page-1) * rows);
        List<TopicModel> topicModels = list.subList(start, end);

        result.setRows(topicModels);

        result.setTotal(list.size());
        return result;
    }

    @Transactional
    public void delete(String ids) {
        String[] strIds = ids.split(",");
        for (String id : strIds){
            topicDAO.delete(id);
        }
    }

    @Override
    public List<ElectiveCourseModle> getByMajor(String major) {
        List<TopicDO> topicDOList = topicDAO.selectByMajor(major);
        List<ElectiveCourseModle> eletiveModles = new LinkedList<>();
        for (TopicDO topicDO : topicDOList){
            ElectiveCourseModle electiveCourseModle = new ElectiveCourseModle();
            electiveCourseModle.setTeacherId(topicDO.getTeacherId());
            UsernameDO usernameDO = usernameDAO.getByUserId(topicDO.getTeacherId());
            electiveCourseModle.setTeacherName(usernameDO.getName());
            electiveCourseModle.setRemark(topicDO.getRemark());
            electiveCourseModle.setTitle(topicDO.getTitle());
            electiveCourseModle.setTopicId(topicDO.getTopicId());
            //设置状态拦
            int status = 0;
            List<String> students = topicDAO.getStudentByTopicId(topicDO.getTopicId());
            if (!CollectionUtils.isEmpty(students) && topicDO.getLimitStudent() <= students.size()){
                status = 1;
            }
            electiveCourseModle.setStatus(status);
            eletiveModles.add(electiveCourseModle);
        }
        return eletiveModles;
    }

    @Override
    public void selectCourse(String id, String topicId) {
        StudentTopicDO studentTopicDO = new StudentTopicDO();
        studentTopicDO.setStudentId(id);
        studentTopicDO.setTopicId(topicId);
        topicDAO.insertStudentTopic(studentTopicDO);
    }

    @Override
    public List<TopicModel> getByTeacher(String id) {
        List<TopicDO> topicDOS = topicDAO.selectByTeacher(Integer.valueOf(id));
        List<TopicModel> topicModels = new LinkedList<>();
        for (TopicDO topicDO : topicDOS){
            topicModels.add(new TopicModel(topicDO));
        }
        return topicModels;
    }

    @Override
    public List<UsernameModel> getStudentByTopicId(String topicId) {
        List<String> studentByTopicId = topicDAO.getStudentByTopicId(topicId);
        List<UsernameModel> usernameModels = new LinkedList<>();
        for (String userId : studentByTopicId){
            UsernameDO usernameDO = usernameDAO.getByUserId(Integer.valueOf(userId));
            usernameModels.add(new UsernameModel(usernameDO));
        }
        return usernameModels;
    }
}

