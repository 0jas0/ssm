package com.jas.web.service.impl;

import com.jas.web.bean.domain.*;
import com.jas.web.bean.model.CourseModel;
import com.jas.web.bean.model.CourseTimePlaceModel;
import com.jas.web.bean.model.StudentModel;
import com.jas.web.bean.model.TeacherModel;
import com.jas.web.dao.*;
import com.jas.web.enums.ECourse;
import com.jas.web.enums.ECourseWeek;
import com.jas.web.service.ICourseService;
import com.jas.web.service.IStudentService;
import com.jas.web.utils.PaperUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

@Service
public class CourseServiceImpl implements ICourseService{
    @Resource
    ICourseDAO courseDAO;

    @Resource
    ICourseTimePlaceDAO courseTimePlaceDAO;

    @Resource
    ICollegeMajorDAO collegeMajorDAO;

    @Resource
    ITeacherDAO teacherDAO;

    @Resource
    IAdministrativeClassDAO administrativeClassDAO;

    @Resource
    IChoiceCoursesDAO choiceCoursesDAO;

    @Resource
    IStudentService studentService;

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

    @Override
    public CourseModel getCourseById(Integer courseId) {
        CourseDO courseDO = courseDAO.getCourseById(courseId);
        return new CourseModel(courseDO);
    }

    @Override
    public PaperUtil<CourseModel> getCourseByPage(Integer currentPage, Integer pageSize) {
        PaperUtil<CourseModel> paperUtil = new PaperUtil<>();
        int totalNum = courseDAO.getTotalNum();
        int startRecord = (currentPage - 1) * pageSize > totalNum ? totalNum : (currentPage - 1) * pageSize;
        List<CourseDO> courseDOList = courseDAO.listCourseByPage(startRecord, pageSize, "id", "desc");
        List<TeacherDO> teacherDOS = teacherDAO.listTeacherAll();
        List<CollegeMajorDO> collegeMajorDOS = collegeMajorDAO.getByParentId(0);
        Map<String,CollegeMajorDO> collegeMajorDOMap = new HashMap<>();
        for (CollegeMajorDO collegeMajorDO : collegeMajorDOS){
            collegeMajorDOMap.put(collegeMajorDO.getId()+"",collegeMajorDO);
        }

        Map<String,TeacherDO> map = new HashMap<>();
        for (TeacherDO teacherDO : teacherDOS){
            map.put(teacherDO.getTeacherId(),teacherDO);
        }
        List<CourseModel> courseModels = new LinkedList<>();
        for (CourseDO courseDO : courseDOList){
            CourseModel courseModel = new CourseModel(courseDO);
            courseModel.setTeacherName(map.get(courseModel.getTeacherId()).getName());
            courseModel.setCollege(collegeMajorDOMap.get(courseDO.getCollege()).getName());
            courseModels.add(courseModel);
        }
        paperUtil.setCurrentPage(currentPage);
        paperUtil.setData(courseModels);
        paperUtil.setPageSize(pageSize);
        paperUtil.setTotalRecord(totalNum);
        return paperUtil;
    }

    @Override
    public List<CourseTimePlaceModel> getCOurseTimePlaceByCourseId(Integer courseId) {
        List<CourseTimePlaceDO> courseTimePlaceDOS = courseTimePlaceDAO.getCourseTimePlaceByCourseId(courseId);
        List<CourseTimePlaceModel> list = new LinkedList<>();
        List<AdministrativeClassDO> administrativeClassDOS = administrativeClassDAO.getAllClass();
        Map<Integer,AdministrativeClassDO> administrativeClassDOMap = new HashMap<>();
        for (AdministrativeClassDO administrativeClassDO : administrativeClassDOS){
            administrativeClassDOMap.put(administrativeClassDO.getId(),administrativeClassDO);
        }

        List<CourseDO> courseDOS = courseDAO.getCourseAll();
        Map<Integer,CourseDO> courseDOMap = new HashMap<>();
        for (CourseDO courseDO : courseDOS){
            courseDOMap.put(courseDO.getId(), courseDO);
        }
        for (CourseTimePlaceDO courseTimePlaceDO : courseTimePlaceDOS){
            CourseTimePlaceModel courseTimePlaceModel = new CourseTimePlaceModel(courseTimePlaceDO);
            courseTimePlaceModel.setCourseTimeName(courseDOMap.get(courseTimePlaceDO.getCourseId()).getName());
            courseTimePlaceModel.setClassName(administrativeClassDOMap.get(courseTimePlaceDO.getClassId()).getName());
            list.add(courseTimePlaceModel);
        }
        return list;
    }

    @Override
    public CourseTimePlaceModel getCourseTimePlaceById(Integer courseId) {
        CourseTimePlaceDO courseTimePlaceDO = courseTimePlaceDAO.getCourseTimePlaceById(courseId);
        return new CourseTimePlaceModel(courseTimePlaceDO);
    }

    @Override
    public List<CourseTimePlaceModel> getCourseTimePlaceByClassId(Integer classId) {
        List<CourseTimePlaceDO> courseTimePlaceDOS = courseTimePlaceDAO.getCourseTimePlaceByClassId(classId);
        List<CourseTimePlaceModel> courseTimePlaceModels = new LinkedList<>();
        for (CourseTimePlaceDO courseTimePlaceDO : courseTimePlaceDOS){
            courseTimePlaceModels.add(new CourseTimePlaceModel(courseTimePlaceDO));
        }
        return courseTimePlaceModels;
    }

    @Override
    public Map<Integer, Map<Integer, String>> getCourseScheduleByStudentId(Integer studentId) {
        StudentModel studentModel =  studentService.getStudentById(studentId);
        List<CourseTimePlaceDO> timePlaceModelList = courseTimePlaceDAO.getCourseTimePlaceByClassId(studentModel.getClassId());
        List<ChoiceCoursesDO> choiceCoursesDOS = choiceCoursesDAO.getCourseByStudentId(studentId);
        List<CourseDO> courseAll = courseDAO.getCourseAll();
        Map<Integer, CourseDO> courseDOMap = new HashMap<>();
        for (CourseDO courseDO : courseAll){
            courseDOMap.put(courseDO.getId(), courseDO);
        }
        for (ChoiceCoursesDO choiceCoursesDO : choiceCoursesDOS){
            List<CourseTimePlaceDO> courseTimePlace = courseTimePlaceDAO.getCourseTimePlaceByCourseId(choiceCoursesDO.getCourseId());
            timePlaceModelList.addAll(courseTimePlace);
        }
        //获取课程时间
        List<Integer> ecourseList = ECourse.getValues();
        //获取课程的星期
        List<Integer> ecourseWeekList = ECourseWeek.getValues();
        Map<Integer,Map<Integer,String>> mapMap = new HashMap<>();
        for (Integer week : ecourseWeekList){
            Map<Integer,String> map = new HashMap<>();
            for (Integer course : ecourseList){
                map.put(course,"");
            }
            mapMap.put(week,map);
        }
        for (CourseTimePlaceDO courseTimePlaceDO : timePlaceModelList){
            CourseDO courseDO = courseDOMap.get(courseTimePlaceDO.getCourseId());
            Integer courseWeek = courseTimePlaceDO.getCourseWeek();
            Integer courseTime = courseTimePlaceDO.getCourseTime();
            Map<Integer, String> map = mapMap.get(courseWeek);
            map.put(courseTime,courseDO.getName());
        }
        return mapMap;
    }
}
