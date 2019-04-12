package com.jas.web.service.impl;

import com.jas.web.annotation.MethodTime;
import com.jas.web.bean.domain.*;
import com.jas.web.bean.model.CourseModel;
import com.jas.web.bean.model.CourseTimePlaceModel;
import com.jas.web.bean.model.StudentModel;
import com.jas.web.dao.*;
import com.jas.web.enums.ECourse;
import com.jas.web.enums.ECourseWeek;
import com.jas.web.service.ICourseService;
import com.jas.web.service.IStudentService;
import com.jas.web.utils.PaperUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
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
            courseModel.setTeacherName(map.get(courseModel.getTeacherId()) != null ? map.get(courseModel.getTeacherId()).getName() : "");
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
                if (administrativeClassDOMap.get(courseTimePlaceDO.getClassId()) == null){
                    courseTimePlaceModel.setClassName("");
                }else {
                    courseTimePlaceModel.setClassName(administrativeClassDOMap.get(courseTimePlaceDO.getClassId()).getName());
                }
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
            // 选修的课程，先删除
            if (courseDO.getType() == 0){
                Iterator<CourseTimePlaceDO> iterator = timePlaceModelList.iterator();
                while (iterator.hasNext()){
                    CourseTimePlaceDO courseTimePlaceDO = iterator.next();
                    if (courseTimePlaceDO.getCourseId() == courseDO.getId()){
                        iterator.remove();
                    }
                }
            }
        }
        for (ChoiceCoursesDO choiceCoursesDO : choiceCoursesDOS){
            List<CourseTimePlaceDO> courseTimePlace = courseTimePlaceDAO.getCourseTimePlaceByClassAndCourseId(studentModel.getClassId(), choiceCoursesDO.getCourseId());
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
            String coursePlace = courseTimePlaceDO.getCoursePlace();
            Map<String, String> map = mapMap.get(ECourse.getDescByValue(courseTime));
            map.put(ECourseWeek.getDescByValue(courseWeek),"课程名称：" + courseDO.getName() + "<br/>　上课地点：" + coursePlace);
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
    public List<CourseModel> getCourseByClassAndType(Integer classId, Integer value) {
        List<CourseDO> courseDOList = courseDAO.getCourseByClassAndType(classId, value);
        List<TeacherDO> teacherDOS = teacherDAO.listTeacherAll();
        List<CollegeMajorDO> allCollegeMajor = collegeMajorDAO.getAllCollegeMajor();
        Map<String, CollegeMajorDO> collegeMajorDOMap = new HashMap<>();
        for (CollegeMajorDO collegeMajorDO : allCollegeMajor){
            collegeMajorDOMap.put(collegeMajorDO.getId()+"", collegeMajorDO);
        }
        Map<String,TeacherDO> map = new HashMap<>();
        for (TeacherDO teacherDO : teacherDOS){
            map.put(teacherDO.getTeacherId(), teacherDO);
        }
        List<CourseModel> courseModelList = new LinkedList<>();
        for (CourseDO courseDO : courseDOList){
            CourseModel courseModel = new CourseModel(courseDO);
            if (map.get(courseDO.getTeacherId()) != null){
                courseModel.setTeacherName(map.get(courseDO.getTeacherId()).getName());
            }else {
                courseModel.setTeacherName("");
            }
            CollegeMajorDO collegeMajorDO = collegeMajorDOMap.get(courseDO.getCollege());
            if (collegeMajorDO != null){
                courseModel.setCollege(collegeMajorDO.getName());
            }else {
                courseModel.setCollege("");
            }
            courseModelList.add(courseModel);
        }
        return courseModelList;
    }

    @Override
    @MethodTime
    public List<ChoiceCoursesDO> getChoiceCourseByStudentId(Integer studentId) {
        List<ChoiceCoursesDO> coursesDOS = choiceCoursesDAO.getCourseByStudentId(studentId);
        return coursesDOS;
    }

    @Override
    public void choiceCourse(Integer studentId, Integer courseId) {
        ChoiceCoursesDO choiceCoursesDO = new ChoiceCoursesDO();
        choiceCoursesDO.setStudentId(studentId);
        choiceCoursesDO.setCourseId(courseId);
        choiceCoursesDAO.addChoiceCourses(choiceCoursesDO);
    }

    @Override
    public void concelCourse(Integer studentId, Integer courseId) {
        choiceCoursesDAO.deleteByStudentIdAndCourseId(studentId, courseId);
    }

    @Override
    public Map<String, Map<String, String>> getCourseScheduleByTeacherId(Integer teacherId) {
        TeacherDO teacher = teacherDAO.getTeacherById(teacherId);
        List<CourseDO> courseDOList = courseDAO.getCourseByTeacherId(teacher.getTeacherId());
        Map<Integer, CourseDO> courseDOMap = new HashMap<>();
        for (CourseDO courseDO : courseDOList){
            courseDOMap.put(courseDO.getId(), courseDO);
        }
        List<CourseTimePlaceDO> timePlaceModelList = new ArrayList<>();
        List<CourseTimePlaceDO> courseTimePlaceAll = courseTimePlaceDAO.getCourseTimePlaceAll();
        for (CourseTimePlaceDO courseTimePlaceDO : courseTimePlaceAll){
            if (courseDOMap.containsKey(courseTimePlaceDO.getCourseId())){
                timePlaceModelList.add(courseTimePlaceDO);
            }
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
        for (CourseTimePlaceDO courseTimePlaceDO : timePlaceModelList){
            CourseDO courseDO = courseDOMap.get(courseTimePlaceDO.getCourseId());
            Integer courseWeek = courseTimePlaceDO.getCourseWeek();
            Integer courseTime = courseTimePlaceDO.getCourseTime();
            String coursePlace = courseTimePlaceDO.getCoursePlace();
            Map<String, String> map = mapMap.get(ECourse.getDescByValue(courseTime));
            map.put(ECourseWeek.getDescByValue(courseWeek),"课程名称：" + courseDO.getName() + "<br/>　上课地点：" + coursePlace);
        }
        return mapMap;
    }

    @Override
    public Map<Integer, List<CourseDO>> getCourseByStudentId(Integer classId, List<Integer> studentIdList) {
        Map<Integer, List<CourseDO>> result = new HashMap<>();
        List<CourseTimePlaceDO> timePlaceDOList = courseTimePlaceDAO.getCourseTimePlaceByClassId(classId);
        List<CourseDO> courseAll = courseDAO.getCourseAll();
        Map<Integer, CourseDO> courseDOMap = new HashMap<>();
        for (CourseDO courseDO : courseAll){
            courseDOMap.put(courseDO.getId(), courseDO);
        }

        // 删除班级课程中的选修课程
        Iterator<CourseTimePlaceDO> iterator = timePlaceDOList.iterator();
        while (iterator.hasNext()){
            CourseTimePlaceDO courseTimePlaceDO = iterator.next();
            CourseDO courseDO = courseDOMap.get(courseTimePlaceDO.getCourseId());
            if (courseDO.getType() == 0){
                iterator.remove();
            }
        }
        // 查看所有人的选修课程有哪些
        List<ChoiceCoursesDO> choiceCoursesDOList = new ArrayList<>();
        if (!CollectionUtils.isEmpty(studentIdList)) {
            choiceCoursesDOList = choiceCoursesDAO.getCourseByStudentIds(studentIdList);
        }
        Map<Integer, List<ChoiceCoursesDO>> choiceCourseMap = new HashMap<>();
        for (ChoiceCoursesDO choiceCoursesDO : choiceCoursesDOList){
            List<ChoiceCoursesDO> choiceCoursesDOS = choiceCourseMap.get(choiceCoursesDO.getStudentId());
            if (choiceCoursesDOS == null){
                choiceCoursesDOS = new ArrayList<>();
                choiceCourseMap.put(choiceCoursesDO.getStudentId(), choiceCoursesDOS);
            }
            choiceCoursesDOS.add(choiceCoursesDO);
        }
        for (Integer studentId : studentIdList){
            List<Integer> courseIdList = new ArrayList<>();
            List<CourseDO> courseDOList = new ArrayList<>();
            // 获取每个学生具体的课程
            // 加入班级的课程
            for (CourseTimePlaceDO courseTimePlaceDO : timePlaceDOList){
                if (!courseIdList.contains(courseTimePlaceDO.getId())) {
                    courseDOList.add(courseDOMap.get(courseTimePlaceDO.getCourseId()));
                    courseIdList.add(courseTimePlaceDO.getCourseId());
                }
            }
            // 加入选修的课程
            List<ChoiceCoursesDO> choiceCoursesDOS = choiceCourseMap.get(studentId);
            if (!CollectionUtils.isEmpty(choiceCoursesDOS)){
                for (ChoiceCoursesDO choiceCoursesDO : choiceCoursesDOS){
                    if (!courseIdList.contains(choiceCoursesDO.getId())) {
                        courseDOList.add(courseDOMap.get(choiceCoursesDO.getCourseId()));
                        courseIdList.add(choiceCoursesDO.getCourseId());
                    }
                }
            }
            result.put(studentId, courseDOList);
        }
        return result;
    }

    @Override
    public Map<String, Map<String, String>> getCourseScheduleByClassId(String classId) {
        List<CourseTimePlaceDO> timePlaceModelList = courseTimePlaceDAO.getCourseTimePlaceByClassId(Integer.valueOf(classId));
        List<CourseDO> courseAll = courseDAO.getCourseAll();
        Map<Integer, CourseDO> courseDOMap = new HashMap<>();
        for (CourseDO courseDO : courseAll){
            courseDOMap.put(courseDO.getId(), courseDO);
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
        for (CourseTimePlaceDO courseTimePlaceDO : timePlaceModelList){
            CourseDO courseDO = courseDOMap.get(courseTimePlaceDO.getCourseId());
            Integer courseWeek = courseTimePlaceDO.getCourseWeek();
            Integer courseTime = courseTimePlaceDO.getCourseTime();
            String coursePlace = courseTimePlaceDO.getCoursePlace();
            Map<String, String> map = mapMap.get(ECourse.getDescByValue(courseTime));
            map.put(ECourseWeek.getDescByValue(courseWeek),"课程名称：" + courseDO.getName() + "<br/>　上课地点：" + coursePlace);
        }
        return mapMap;
    }
}
