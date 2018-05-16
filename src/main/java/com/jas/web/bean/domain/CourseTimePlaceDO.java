package com.jas.web.bean.domain;

import com.jas.web.bean.model.CourseTimePlaceModel;

public class CourseTimePlaceDO extends BaseDO{
    private Integer courseId;

    private String name;

    private Integer courseWeek;

    private Integer courseTime;

    private Integer classId;

    private String coursePlace;

    public CourseTimePlaceDO() {
    }

    public CourseTimePlaceDO(CourseTimePlaceModel courseTimePlaceModel) {
        if (courseTimePlaceModel != null){
            this.setId(courseTimePlaceModel.getId());
            courseId = courseTimePlaceModel.getCourseId();
            name = courseTimePlaceModel.getName();
            courseWeek = courseTimePlaceModel.getCourseWeek();
            courseTime = courseTimePlaceModel.getCourseTime();
            classId = courseTimePlaceModel.getClassId();
            coursePlace = courseTimePlaceModel.getCoursePlace();
        }
    }

    public Integer getClassId() {
        return classId;
    }

    public void setClassId(Integer classId) {
        this.classId = classId;
    }

    public Integer getCourseId() {
        return courseId;
    }

    public void setCourseId(Integer courseId) {
        this.courseId = courseId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getCourseWeek() {
        return courseWeek;
    }

    public void setCourseWeek(Integer courseWeek) {
        this.courseWeek = courseWeek;
    }

    public Integer getCourseTime() {
        return courseTime;
    }

    public void setCourseTime(Integer courseTime) {
        this.courseTime = courseTime;
    }

    public String getCoursePlace() {
        return coursePlace;
    }

    public void setCoursePlace(String coursePlace) {
        this.coursePlace = coursePlace;
    }
}
