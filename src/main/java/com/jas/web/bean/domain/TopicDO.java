package com.jas.web.bean.domain;

import com.jas.web.bean.model.TopicModel;

import java.util.Date;

public class TopicDO extends BaseDO{

    private String topicId;
    private String title;
    private String major;
    private Integer limitStudent;
    private Integer teacherId;
    private Integer status;
    private String remark;

    public TopicDO() {
    }

    public TopicDO(TopicModel topicModel) {
        if (topicModel != null){
            topicId = topicModel.getTopicId();
            title = topicModel.getTitle();
            major = topicModel.getMajor();
            limitStudent = topicModel.getLimitStudent();
            teacherId = topicModel.getTeacherId();
            status = topicModel.getStatus();
            remark = topicModel.getRemark();
        }
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getTopicId() {
        return topicId;
    }

    public void setTopicId(String topicId) {
        this.topicId = topicId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major;
    }

    public Integer getLimitStudent() {
        return limitStudent;
    }

    public void setLimitStudent(Integer limitStudent) {
        this.limitStudent = limitStudent;
    }

    public Integer getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(Integer teacherId) {
        this.teacherId = teacherId;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}