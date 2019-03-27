package com.jas.web.service.impl;

import com.jas.web.bean.domain.*;
import com.jas.web.bean.model.*;
import com.jas.web.dao.*;
import com.jas.web.enums.ECourseSemester;
import com.jas.web.enums.ECourseType;
import com.jas.web.enums.EEvaluationGrade;
import com.jas.web.service.ICourseService;
import com.jas.web.service.IScoreService;
import com.jas.web.service.ITeacherService;
import com.jas.web.utils.PaperUtil;
import com.jas.web.utils.StringUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.*;

@Service
public class TeacherServiceImpl implements ITeacherService{

    @Resource
    private ITeacherDAO teacherDAO;

    @Resource
    private IScoreService scoreService;

    @Resource
    private IStudentDAO studentDAO;

    @Resource
    private ICourseService courseService;

    @Resource
    private IteachingEvaluationDAO iteachingEvaluationDAO;

    @Resource
    private ICourseDAO courseDAO;
    
    @Resource
    private ICollegeMajorDAO collegeMajorDAO;

    @Transactional
    public void addTeacher(TeacherModel teacherModel) {
        teacherModel.setPassword(StringUtil.md5Password(teacherModel.getPassword()));
        teacherDAO.addTeacher(new TeacherDO(teacherModel));
    }

    @Transactional
    public void modifyTeacher(TeacherModel teacherModel) {
        TeacherDO teacherDO = teacherDAO.getTeacherByTeacherId(teacherModel.getTeacherId());
        if (StringUtil.isEmpty(teacherModel.getPassword())){
            teacherModel.setPassword(teacherDO.getPassword());
        }else {
            teacherModel.setPassword(StringUtil.md5Password(teacherModel.getPassword()));
        }
        teacherDAO.updateTeacher(new TeacherDO(teacherModel));
    }

    @Override
    public TeacherModel getByTeacherId(String teacherId) {
        TeacherDO teacher = teacherDAO.getTeacherByTeacherId(teacherId);
        TeacherModel teacherModel = new TeacherModel(teacher);
        return teacherModel;
    }

    @Override
    public TeacherModel getById(Integer teacherId) {
        TeacherDO teacher = teacherDAO.getTeacherById(teacherId);
        TeacherModel teacherModel = new TeacherModel(teacher);
        return teacherModel;
    }

    @Override
    public PaperUtil<TeacherModel> getTeacherByPage(String keyword, Integer currentPage, Integer pageSize) {
        PaperUtil<TeacherModel> paperUtil = new PaperUtil<>();
        int totalNum = teacherDAO.getTotalNum();
        int startRecord = (currentPage - 1) * pageSize > totalNum ? totalNum : (currentPage - 1) * pageSize;
        List<TeacherDO> teacherDOList = teacherDAO.listTeacherByPage(keyword, startRecord, pageSize, "id", "desc");
        List<TeacherModel> teacherModelList = new LinkedList<>();
        for (TeacherDO teacherDO : teacherDOList){
            teacherModelList.add(new TeacherModel(teacherDO));
        }
        paperUtil.setCurrentPage(currentPage);
        paperUtil.setData(teacherModelList);
        paperUtil.setPageSize(pageSize);
        paperUtil.setTotalRecord(totalNum);
        return paperUtil;
    }

    @Override
    public void deleteTeacher(String teacherId) {
        teacherDAO.deleteTeacher(teacherId);
    }

    @Override
    public List<TeacherModel> getAllTeacher() {
        List<TeacherDO> teacherDOS = teacherDAO.listTeacherAll();
        List<TeacherModel> teacherModelList = new LinkedList<>();
        for (TeacherDO teacherDO : teacherDOS){
            teacherModelList.add(new TeacherModel(teacherDO));
        }
        return teacherModelList;
    }

    @Override
    public List<EvalutionModel> studentEvaluationTeacherList(Integer studentId) {
        List<EvalutionModel> list = new LinkedList<>();
        StudentDO student = studentDAO.getStudentById(studentId);
        //获取老师列表
        List<TeacherDO> teacherDOS = teacherDAO.listTeacherAll();
        Map<String,TeacherDO> teacherDOMap = new HashMap<>();
        for (TeacherDO teacherDO : teacherDOS){
            teacherDOMap.put(teacherDO.getTeacherId(), teacherDO);
        }
        //获取成绩
        Map<String, List<CourseModel>> scoreBystudent = scoreService.getScoreBystudentId(student.getId());
        //获取最新的学期
        Integer newSemester = courseService.getNewSemester(student.getId(), student.getClassId());
        //获取对老师的评价
        if (newSemester != null){
            List<CourseModel> courseModels = scoreBystudent.get(ECourseSemester.getDescByValue(newSemester));
            for (CourseModel courseModel : courseModels){
                EvalutionModel evalutionModel = new EvalutionModel();
                evalutionModel.setCourseId(courseModel.getId());
                evalutionModel.setCourseName(courseModel.getName());
                evalutionModel.setStudentId(student.getStudentId());
                String teacherId = courseModel.getTeacherId();
                evalutionModel.setTeacherId(teacherId);
                evalutionModel.setTeacherName(teacherDOMap.get(teacherId).getName());
                TeachingEvaluationDO teacherEvaluation = iteachingEvaluationDAO.getTeacherEvaluationByStudentIdAndTeacherIdAndCourseId(student.getStudentId(), teacherId, courseModel.getId());
                if (teacherEvaluation != null){
                    evalutionModel.setEvaluationGrade(teacherEvaluation.getEvaluationGrade());
                }
                list.add(evalutionModel);
            }
        }

        return list;
    }

    @Override
    public PaperUtil<EvalutionTeacherModel> getEvaluationByTeacherIdAndPage(String teacherId, Integer currentPage, Integer pageSize) {
        PaperUtil<EvalutionTeacherModel> paperUtil = new PaperUtil<>();
        int totalNum = courseDAO.getCourseNumByTeacherId(teacherId);
        List<CourseModel> courseModels =  courseService.getCourseByTeacherIdAndPage(teacherId, currentPage, pageSize);
        List<EvalutionTeacherModel> evalutionTeacherModels = new LinkedList<>();
        List<CollegeMajorDO> collegeMajor = collegeMajorDAO.getAllCollegeMajor();
        Map<Integer,CollegeMajorDO> collegeMajorDOMap = new HashMap<>();
        for (CollegeMajorDO collegeMajorDO : collegeMajor){
            collegeMajorDOMap.put(collegeMajorDO.getId(), collegeMajorDO);
        }
        List<TeachingEvaluationDO> teachingEvaluationDOS = iteachingEvaluationDAO.getTeacherEvaluationByTeacherId(teacherId);
        Map<Integer,List<Integer>> teachingEvalutionMap = new HashMap<>();
        for (TeachingEvaluationDO teachingEvaluationDO : teachingEvaluationDOS){
            Integer courseId = teachingEvaluationDO.getCourseId();
            List<Integer> grades = teachingEvalutionMap.get(courseId);
            if (grades == null){
                grades = new LinkedList<>();
                teachingEvalutionMap.put(courseId, grades);
            }
            grades.add(teachingEvaluationDO.getEvaluationGrade());
        }
        for (CourseModel courseModel : courseModels){
            EvalutionTeacherModel evalutionTeacherModel = new EvalutionTeacherModel();
            evalutionTeacherModel.setCollege(Integer.valueOf(courseModel.getCollege()));
            CollegeMajorDO collegeMajorDO = collegeMajorDOMap.get(Integer.valueOf(courseModel.getCollege()));
            if (collegeMajorDO != null){
                evalutionTeacherModel.setCollegeName(collegeMajorDO.getName());
            }
            evalutionTeacherModel.setCourseId(courseModel.getId());
            evalutionTeacherModel.setCourseName(courseModel.getName());
            evalutionTeacherModel.setType(courseModel.getType());
            evalutionTeacherModel.setTypeName(ECourseType.getDescByValue(courseModel.getType()));
            List<Integer> grades = teachingEvalutionMap.get(courseModel.getId());
            String evalution = "";
            if (grades != null){
                int sum = 0;
                for (Integer grade : grades){
                    sum += grade;
                }
                Float size = grades.size()*1.0F;
                evalution = EEvaluationGrade.getDescByValue(Math.round(sum / size));
            }
            //设置评价
            evalutionTeacherModel.setEvalution(evalution);
            //设置评价人数
            evalutionTeacherModel.setEvalutionNum(grades == null ? 0 : grades.size());
            evalutionTeacherModels.add(evalutionTeacherModel);
        }
        paperUtil.setCurrentPage(currentPage);
        paperUtil.setData(evalutionTeacherModels);
        paperUtil.setPageSize(pageSize);
        paperUtil.setTotalRecord(totalNum);
        return paperUtil;
    }
}
