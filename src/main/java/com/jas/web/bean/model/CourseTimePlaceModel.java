package com.jas.web.bean.model;

import com.jas.web.bean.domain.CourseTimePlaceDO;
import com.jas.web.enums.ECourse;
import com.jas.web.enums.ECourseWeek;

public class CourseTimePlaceModel {
    private Integer courseId;

    private String name;

    private Integer courseWeek;

    private String courseWeekName;
    
    private Integer course;
    
    private String courseName;

    private Integer classId;

    private String coursePlace;

    public CourseTimePlaceModel() {
    }

    public CourseTimePlaceModel(CourseTimePlaceDO courseTimePlaceDO) {
        if (courseTimePlaceDO != null){
            courseId = courseTimePlaceDO.getCourseId();
            name = courseTimePlaceDO.getName();
            courseWeek = courseTimePlaceDO.getCourseWeek();
            course = courseTimePlaceDO.getCourse();
            classId = courseTimePlaceDO.getClassId();
            coursePlace = courseTimePlaceDO.getCoursePlace();
            courseWeekName = ECourseWeek.getDescByValue(courseWeek);
            courseName = ECourse.getDescByValue(course);
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
