package com.jas.web.service;


import com.jas.web.bean.model.CourseModel;
import com.jas.web.bean.model.CourseTimePlaceModel;

public interface ICourseService {
    void addCourse(CourseModel courseModel);

    void addCourseTimePlace(CourseTimePlaceModel courseTimePlaceModel);

    void deleteCourse(Integer id);

    void deleteCourseTimePlace(Integer id);

    void modifyCourse(CourseModel courseModel);

    void modifyCourseTimePlace(CourseTimePlaceModel courseTimePlaceModel);
}