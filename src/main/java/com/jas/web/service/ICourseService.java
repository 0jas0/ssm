package com.jas.web.service;


import com.jas.web.bean.model.CourseModel;
import com.jas.web.bean.model.CourseTimePlaceModel;
import com.jas.web.utils.PaperUtil;

import java.util.List;
import java.util.Map;

public interface ICourseService {
    void addCourse(CourseModel courseModel);

    void addCourseTimePlace(CourseTimePlaceModel courseTimePlaceModel);

    void deleteCourse(Integer id);

    void deleteCourseTimePlace(Integer id);

    void modifyCourse(CourseModel courseModel);

    void modifyCourseTimePlace(CourseTimePlaceModel courseTimePlaceModel);

    CourseModel getCourseById(Integer courseId);

    PaperUtil<CourseModel> getCourseByPage(Integer currentPage, Integer pageSize);

    List<CourseTimePlaceModel> getCOurseTimePlaceByCourseId(Integer courseId);

    CourseTimePlaceModel getCourseTimePlaceById(Integer courseId);

    List<CourseTimePlaceModel> getCourseTimePlaceByClassId(Integer classId);

    Map<Integer,Map<Integer,String>> getCourseScheduleByStudentId(Integer studentId);
}