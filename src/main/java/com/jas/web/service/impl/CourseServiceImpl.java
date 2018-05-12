package com.jas.web.service.impl;

import com.jas.web.bean.domain.CourseDO;
import com.jas.web.bean.domain.CourseTimePlaceDO;
import com.jas.web.bean.model.CourseModel;
import com.jas.web.bean.model.CourseTimePlaceModel;
import com.jas.web.dao.ICourseDAO;
import com.jas.web.dao.ICourseTimePlaceDAO;
import com.jas.web.service.ICourseService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Service
public class CourseServiceImpl implements ICourseService{
    @Resource
    ICourseDAO courseDAO;

    @Resource
    ICourseTimePlaceDAO courseTimePlaceDAO;

    @Override
    @Transactional
    public void addCourse(CourseModel courseModel) {
        courseDAO.addCourse(new CourseDO(courseModel));
    }

    @Override
    @Transactional
    public void addCourseTimePlace(CourseTimePlaceModel courseTimePlaceModel) {
        courseTimePlaceDAO.addCourseTimePlace(new CourseTimePlaceDO(courseTimePlaceModel));
    }

    @Override
    @Transactional
    public void deleteCourse(Integer id) {
        List<CourseTimePlaceDO> courseTimePlaceDOList = courseTimePlaceDAO.getCourseTimePlaceByCourseId(id);
        //删除课程的时间和地点
        for (CourseTimePlaceDO courseTimePlaceDO : courseTimePlaceDOList){
            courseTimePlaceDAO.deleteCourseTimePlace(courseTimePlaceDO.getId());
        }
        //删除课程
        courseDAO.deleteCourse(id);
    }

    @Override
    @Transactional
    public void deleteCourseTimePlace(Integer id) {
        courseTimePlaceDAO.deleteCourseTimePlace(id);
    }

    @Override
    @Transactional
    public void modifyCourse(CourseModel courseModel) {
        courseDAO.updateCourse(new CourseDO(courseModel));
    }

    @Override
    @Transactional
    public void modifyCourseTimePlace(CourseTimePlaceModel courseTimePlaceModel) {
        courseTimePlaceDAO.updateCourseTimePlace(new CourseTimePlaceDO(courseTimePlaceModel));
    }
}
