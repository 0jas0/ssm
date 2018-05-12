package com.jas.web.bean.domain;

import com.jas.web.bean.model.CourseTimePlaceModel;

public class CourseTimePlaceDO extends BaseDO{
    private Integer courseId;

    private String name;

    private Integer courseWeek;

    private Integer course;

    private Integer classId;

    private String coursePlace;

    public CourseTimePlaceDO() {
    }

    public CourseTimePlaceDO(CourseTimePlaceModel courseTimePlaceModel) {
        if (courseTimePlaceModel != null){
            courseId = courseTimePlaceModel.getCourseId();
            name = courseTimePlaceModel.getName();
            courseWeek = courseTimePlaceModel.getCourseWeek();
            course = courseTimePlaceModel.getCourse();
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

    public Integer getCourse() {
        return course;
    }

    public void setCourse(Integer course) {
        this.course = course;
    }

    public String getCoursePlace() {
        return coursePlace;
    }

    public void setCoursePlace(String coursePlace) {
        this.coursePlace = coursePlace;
    }
}
