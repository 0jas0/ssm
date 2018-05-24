package com.jas.web.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.jas.web.bean.domain.*;
import com.jas.web.bean.model.ChoiceCourseModel;
import com.jas.web.bean.model.CourseModel;
import com.jas.web.bean.model.CourseTimePlaceModel;
import com.jas.web.bean.model.StudentModel;
import com.jas.web.dao.*;
import com.jas.web.enums.ECourse;
import com.jas.web.enums.ECourseWeek;
import com.jas.web.helper.NettyClient;
import com.jas.web.service.ICourseService;
import com.jas.web.service.IStudentService;
import com.jas.web.utils.PaperUtil;
import com.jas.web.utils.StringUtil;
import io.netty.buffer.Unpooled;
import io.netty.channel.Channel;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonObject;
import java.util.*;

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
            courseTimePlaceModel.setCourseName(courseDOMap.get(courseTimePlaceDO.getCourseId()).getName());
            if (courseTimePlaceDO.getClassId() == null){
                courseTimePlaceModel.setClassName("");
            }else {
                courseTimePlaceModel.setClassName(administrativeClassDOMap.get(courseTimePlaceDO.getClassId()).getName());
            }
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
    public Map<String, Map<String, String>> getCourseScheduleByStudentId(Integer studentId) {
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
        List<String> ecourseList = ECourse.getDescs();
        //获取课程的星期
        List<String> ecourseWeekList = ECourseWeek.getDescs();
        Map<String,Map<String,String>> mapMap = new LinkedHashMap<>();
        for (String course : ecourseList){
            Map<String,String> map = new LinkedHashMap<>();
            for (String week : ecourseWeekList){
                map.put(week,"");
            }
            mapMap.put(course,map);
        }

        //获取最新一学期
        //所有的课程的时间和地点
        Integer newSemester = getNewSemester(studentId, studentModel.getClassId());
        for (CourseTimePlaceDO courseTimePlaceDO : timePlaceModelList){
            CourseDO courseDO = courseDOMap.get(courseTimePlaceDO.getCourseId());
            if (courseDO.getSemester() != newSemester){
                continue;
            }
            Integer courseWeek = courseTimePlaceDO.getCourseWeek();
            Integer courseTime = courseTimePlaceDO.getCourseTime();
            Map<String, String> map = mapMap.get(ECourse.getDescByValue(courseTime));
            map.put(ECourseWeek.getDescByValue(courseWeek),courseDO.getName());
        }
        return mapMap;
    }

    /**
     * 获取最新一个学期
     * @return
     */
    public Integer getNewSemester(Integer studentId, Integer classId){
        Integer newSemester1 = choiceCoursesDAO.getNewSemesterByStudentId(studentId);
        Integer newSemester2 = courseTimePlaceDAO.getNewSemesterByClassId(classId);
        Integer res = null;
        if (newSemester1 == null){
            res = newSemester2;
        }else if (newSemester2 == null){
            res = newSemester1;
        }else if (newSemester1 != null && newSemester2 != null){
            res = newSemester1 > newSemester2 ? newSemester1 : newSemester2;
        }
        return res;
    }

    @Override
    public List<CourseModel> getCourseByTeacherIdAndPage(String teacherId, Integer currentPage, Integer pageSize) {
        int totalNum = courseDAO.getCourseNumByTeacherId(teacherId);
        int startRecord = (currentPage - 1) * pageSize > totalNum ? totalNum : (currentPage - 1) * pageSize;
        List<CourseDO> courseDOList = courseDAO.getCourseByTeacherIdAndPage(teacherId,startRecord,pageSize);
        List<CourseModel> courseModels = new LinkedList<>();
        for (CourseDO courseDO : courseDOList){
            courseModels.add(new CourseModel(courseDO));
        }
        return courseModels;
    }

    @Override
    public int getCourseNumByTeacherId(String teacherId) {
        int totalNum = courseDAO.getCourseNumByTeacherId(teacherId);
        return totalNum;
    }

    @Override
    public List<CourseModel> getCourseByCollegeAndType(Integer college, Integer value) {
        List<CourseDO> courseDOList = courseDAO.getCourseByCollegeAndType(college,value);
        List<TeacherDO> teacherDOS = teacherDAO.listTeacherAll();
        Map<String,TeacherDO> map = new HashMap<>();
        for (TeacherDO teacherDO : teacherDOS){
            map.put(teacherDO.getTeacherId(), teacherDO);
        }
        List<CourseModel> courseModelList = new LinkedList<>();
        for (CourseDO courseDO : courseDOList){
            CourseModel courseModel = new CourseModel(courseDO);
            courseModel.setTeacherName(map.get(courseDO.getTeacherId()).getName());
            courseModelList.add(courseModel);
        }
        return courseModelList;
    }

    @Override
    public List<ChoiceCoursesDO> getChoiceCourseByStudentId(Integer studentId) {
        List<ChoiceCoursesDO> coursesDOS = choiceCoursesDAO.getCourseByStudentId(studentId);
        return coursesDOS;
    }

    @Override
    public void choiceCourse(Integer studentId, Integer courseId) {
        Channel channel = NettyClient.channel;
        ChoiceCourseModel choiceCourseModel = new ChoiceCourseModel();
        choiceCourseModel.setStudentId(studentId);
        choiceCourseModel.setCourseId(courseId);
        choiceCourseModel.setType(0);
        String jsonString = JSONObject.toJSONString(choiceCourseModel);
        channel.writeAndFlush(Unpooled.copiedBuffer(StringUtil.nettyBytes(jsonString)));
    }

    @Override
    public void concelCourse(Integer studentId, Integer courseId) {
        Channel channel = NettyClient.channel;
        ChoiceCourseModel choiceCourseModel = new ChoiceCourseModel();
        choiceCourseModel.setStudentId(studentId);
        choiceCourseModel.setCourseId(courseId);
        choiceCourseModel.setType(1);
        String jsonString = JSONObject.toJSONString(choiceCourseModel);
        channel.writeAndFlush(Unpooled.copiedBuffer(StringUtil.nettyBytes(jsonString)));
    }
}
