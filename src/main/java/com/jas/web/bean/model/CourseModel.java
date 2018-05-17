package com.jas.web.bean.model;

import com.jas.web.bean.domain.BaseDO;
import com.jas.web.bean.domain.CourseDO;
import com.jas.web.enums.ECourseSemester;
import com.jas.web.enums.ECourseType;
import com.jas.web.utils.DateUtil;

public class CourseModel{
    private Integer id;

    private String teacherId;

    private String name;

    private String courseStart;

    private String courseEnd;

    private Integer semester;

    private String college;

    private Integer period;

    private Integer credit;

    private String teacherName;

    private Integer type;

    private String typeName;

    private Double grade;

    private String semesterName;

    private Integer isSelected;

    public CourseModel() {
    }

    public CourseModel(CourseDO courseDO) {
        if (courseDO != null){
            this.id = courseDO.getId();
            this.teacherId = courseDO.getTeacherId();
            this.name = courseDO.getName();
            this.courseStart = DateUtil.formatSeconds(courseDO.getCourseStart(), "yyyy-MM-dd");
            this.courseEnd = DateUtil.formatSeconds(courseDO.getCourseEnd(), "yyyy-MM-dd");
            this.semester = courseDO.getSemester();
            this.college = courseDO.getCollege();
            this.period = courseDO.getPeriod();
            this.credit = courseDO.getCredit();
            this.type = courseDO.getType();
            this.typeName = ECourseType.getDescByValue(type);
            this.semesterName = ECourseSemester.getDescByValue(semester);
        }
    }

    public Integer getIsSelected() {
        return isSelected;
    }

    public void setIsSelected(Integer isSelected) {
        this.isSelected = isSelected;
    }

    public String getSemesterName() {
        return semesterName;
    }

    public void setSemesterName(String semesterName) {
        this.semesterName = semesterName;
    }

    public Double getGrade() {
        return grade;
    }

    public void setGrade(Double grade) {
        this.grade = grade;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTeacherName() {
        return teacherName;
    }

    public void setTeacherName(String teacherName) {
        this.teacherName = teacherName;
    }

    public String getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(String teacherId) {
        this.teacherId = teacherId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCourseStart() {
        return courseStart;
    }

    public void setCourseStart(String courseStart) {
        this.courseStart = courseStart;
    }

    public String getCourseEnd() {
        return courseEnd;
    }

    public void setCourseEnd(String courseEnd) {
        this.courseEnd = courseEnd;
    }

    public Integer getSemester() {
        return semester;
    }

    public void setSemester(Integer semester) {
        this.semester = semester;
    }

    public String getCollege() {
        return college;
    }

    public void setCollege(String college) {
        this.college = college;
    }

    public Integer getPeriod() {
        return period;
    }

    public void setPeriod(Integer period) {
        this.period = period;
    }

    public Integer getCredit() {
        return credit;
    }

    public void setCredit(Integer credit) {
        this.credit = credit;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }
}
