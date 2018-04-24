package com.jas.web.bean.param;

import com.jas.web.bean.model.TopicModel;
import com.jas.web.utils.StringUtil;

public class TopicParam {
    private String topicId;
    private String title;
    private String major;
    private Integer limitStudent;
    private Integer userId;
    private String remark;

    public TopicModel getTopicModel(){
        TopicModel topicModel = new TopicModel();
        topicModel.setLimitStudent(limitStudent);
        topicModel.setMajor(major);
        topicModel.setTitle(title);
        topicModel.setTeacherId(userId);
        if (StringUtil.isEmpty(topicId)){
            topicModel.setTopicId(StringUtil.getPrimaryKeyId());
        }else {
            topicModel.setTopicId(topicId);
        }
        topicModel.setRemark(remark);
        return topicModel;
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

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }
}
