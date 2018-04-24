package com.jas.web.bean.domain;

public class StudentUploadDO extends BaseDO{
    private Integer studentTopicId;
    private Long uploadTime;
    private String name;
    private String path;
    private Double score;
    private Integer status;

    public Integer getStudentTopicId() {
        return studentTopicId;
    }

    public void setStudentTopicId(Integer studentTopicId) {
        this.studentTopicId = studentTopicId;
    }

    public Long getUploadTime() {
        return uploadTime;
    }

    public void setUploadTime(Long uploadTime) {
        this.uploadTime = uploadTime;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public Double getScore() {
        return score;
    }

    public void setScore(Double score) {
        this.score = score;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}
