package com.jas.web.bean.model;

public class ScoreManagementModel {

    private Double openingReport;//开题报告
    private Double commitments;//任务书
    private Double midtermCheck;//中期检查
    private Double paper;//论文
    private Double total;//总成绩
    private String studentName;//学生姓名
    private String topicName;//课题名称
    private String topicId;//课题编号

    public String getTopicId() {
        return topicId;
    }

    public void setTopicId(String topicId) {
        this.topicId = topicId;
    }

    public Double getOpeningReport() {
        return openingReport;
    }

    public void setOpeningReport(Double openingReport) {
        this.openingReport = openingReport;
    }

    public Double getCommitments() {
        return commitments;
    }

    public void setCommitments(Double commitments) {
        this.commitments = commitments;
    }

    public Double getMidtermCheck() {
        return midtermCheck;
    }

    public void setMidtermCheck(Double midtermCheck) {
        this.midtermCheck = midtermCheck;
    }

    public Double getPaper() {
        return paper;
    }

    public void setPaper(Double paper) {
        this.paper = paper;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public String getTopicName() {
        return topicName;
    }

    public void setTopicName(String topicName) {
        this.topicName = topicName;
    }
}
