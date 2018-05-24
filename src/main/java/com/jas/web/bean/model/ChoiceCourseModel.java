package com.jas.web.bean.model;

import java.io.Serializable;

public class ChoiceCourseModel implements Serializable{

    private static final long serialVersionUID = 1L;

    private Integer studentId;

    private Integer courseId;

    private Integer type;

    public Integer getStudentId() {
        return studentId;
    }

    public void setStudentId(Integer studentId) {
        this.studentId = studentId;
    }

    public Integer getCourseId() {
        return courseId;
    }

    public void setCourseId(Integer courseId) {
        this.courseId = courseId;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }
}
