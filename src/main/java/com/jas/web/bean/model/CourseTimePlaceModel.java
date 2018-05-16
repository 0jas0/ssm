package com.jas.web.bean.model;

import com.jas.web.bean.domain.CourseTimePlaceDO;
import com.jas.web.enums.ECourse;
import com.jas.web.enums.ECourseWeek;

public class CourseTimePlaceModel {
    private Integer id;

    private Integer courseId;

    private String name;

    private Integer courseWeek;

    private String courseWeekName;
    
    private Integer courseTime;
    
    private String courseName;

    private Integer classId;

    private String coursePlace;

    private String courseTimeName;

    private String className;

    public CourseTimePlaceModel() {
    }

    public CourseTimePlaceModel(CourseTimePlaceDO courseTimePlaceDO) {
        if (courseTimePlaceDO != null){
            id = courseTimePlaceDO.getId();
            courseId = courseTimePlaceDO.getCourseId();
            name = courseTimePlaceDO.getName();
            courseWeek = courseTimePlaceDO.getCourseWeek();
            courseTime = courseTimePlaceDO.getCourseTime();
            classId = courseTimePlaceDO.getClassId();
            coursePlace = courseTimePlaceDO.getCoursePlace();
            courseWeekName = ECourseWeek.getDescByValue(courseWeek);
            courseName = ECourse.getDescByValue(courseTime);
        }
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public Integer getCourseTime() {
        return courseTime;
    }

    public void setCourseTime(Integer courseTime) {
        this.courseTime = courseTime;
    }

    public String getCourseTimeName() {
        return courseTimeName;
    }

    public void setCourseTimeName(String courseTimeName) {
        this.courseTimeName = courseTimeName;
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

    public String getCoursePlace() {
        return coursePlace;
    }

    public void setCoursePlace(String coursePlace) {
        this.coursePlace = coursePlace;
    }

    public String getCourseWeekName() {
        return courseWeekName;
    }

    public void setCourseWeekName(String courseWeekName) {
        this.courseWeekName = courseWeekName;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }
}
