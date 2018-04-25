package com.jas.web.service;

import com.jas.web.bean.model.ElectiveCourseModle;
import com.jas.web.bean.model.TopicModel;
import com.jas.web.bean.model.UsernameModel;
import com.jas.web.bean.param.TopicParam;
import com.jas.web.bean.param.UsernameParam;
import com.jas.web.utils.EUDataGridResult;

import java.util.List;

public interface ITopicService {
    public int insert(TopicModel topicModel);

    public TopicModel getByTopicId(String topicId);

    EUDataGridResult getList(Integer page, Integer rows);

    void update(TopicModel topicModel);

    void delete(String ids);

    List<ElectiveCourseModle> getByMajor(String major);

    void selectCourse(String s, String topicId);

    List<TopicModel> getByTeacher(String s);

    List<UsernameModel> getStudentByTopicId(String topicId);

}