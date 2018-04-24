package com.jas.web.bean.model;

import com.jas.web.bean.domain.BaseDO;
import com.jas.web.bean.domain.TopicDO;

public class TopicModel{

    private String topicId;
    private String title;
    private String major;
    private Integer limitStudent;
    private Integer teacherId;
    private Integer status;
    private String remark;

    public TopicModel() {
    }

    public TopicModel(TopicDO topicDO) {
        if (topicDO != null){
            topicId = topicDO.getTopicId();
            title = topicDO.getTitle();
            major = topicDO.getMajor();
            limitStudent = topicDO.getLimitStudent();
            teacherId = topicDO.getTeacherId();
            status = topicDO.getStatus();
            remark = topicDO.getRemark();
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
