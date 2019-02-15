package com.jas.web.service.impl;

import com.jas.web.bean.domain.*;
import com.jas.web.bean.model.*;
import com.jas.web.dao.*;
import com.jas.web.enums.ECourseSemester;
import com.jas.web.service.ICourseService;
import com.jas.web.service.IScoreService;
import com.jas.web.service.IStudentService;
import com.jas.web.utils.PaperUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.*;

@Service
public class ScoreServiceImpl implements IScoreService{
    @Resource
    IScoreDAO scoreDAO;
    
    @Resource
    IChoiceCoursesDAO choiceCoursesDAO;

    @Resource
    ICourseDAO courseDAO;

    @Resource
    IStudentDAO studentDAO;

    @Resource
    ICourseTimePlaceDAO courseTimePlaceDAO;

    @Resource
    IAdministrativeClassDAO administrativeClassDAO;

    @Resource
    IStudentService studentService;

    @Override
    @Transactional
    public void addScore(ScoreModel scoreModel) {
        scoreDAO.addScore(new ScoreDO(scoreModel));
    }

    @Override
    @Transactional
    public void modifyScore(ScoreModel scoreModel) {
        scoreDAO.updateScore(new ScoreDO(scoreModel));
    }

    @Override
    public Map<String,List<CourseModel>> getScoreBystudentId(Integer studentId) {
        StudentDO student = studentDAO.getStudentById(studentId);
        List<CourseDO> courseAll = courseDAO.getCourseAll();
        Map<Integer,CourseDO> courseDOMap = new HashMap<>();
        for (CourseDO courseDO : courseAll){
            courseDOMap.put(courseDO.getId(), courseDO);
        }
        //该学生所有的成绩
        List<ScoreDO> scoreDOS = scoreDAO.getScoreByStudentId(studentId);
        Map<Integer,ScoreDO> scoreDOMap = new HashMap<>();
        for (ScoreDO scoreDO : scoreDOS){
            scoreDOMap.put(scoreDO.getCourseId(),scoreDO);
        }
        //查看学生所有的课程
        //1、选修课程
        List<ChoiceCoursesDO> choiceCoursesDOS = choiceCoursesDAO.getCourseByStudentId(studentId);
        //2、必修课程
        List<CourseTimePlaceDO> courseTimePlaceDOS = courseTimePlaceDAO.getUniqueCourseTimePlaceByClassId(student.getClassId());
        List<CourseDO> myCourse = new LinkedList<>();
        for (ChoiceCoursesDO choiceCoursesDO : choiceCoursesDOS){
            CourseDO courseDO = courseDOMap.get(choiceCoursesDO.getCourseId());
            myCourse.add(courseDO);
        }
        for (CourseTimePlaceDO courseTimePlaceDO : courseTimePlaceDOS){
            CourseDO courseDO = courseDOMap.get(courseTimePlaceDO.getCourseId());
            myCourse.add(courseDO);
        }
        Map<String,List<CourseModel>> map = new LinkedHashMap<>();
        for (CourseDO courseDO : myCourse){
            List<CourseModel> courseModels = map.get(ECourseSemester.getDescByValue(courseDO.getSemester()));
            if(CollectionUtils.isEmpty(courseModels)){
                courseModels = new LinkedList<>();
                map.put(ECourseSemester.getDescByValue(courseDO.getSemester()), courseModels);
            }
            CourseModel courseModel = new CourseModel(courseDO);
            ScoreDO scoreDO = scoreDOMap.get(courseDO.getId());
            if (scoreDO != null){
                courseModel.setGrade(scoreDO.getGrade());
            }
            courseModels.add(courseModel);
        }
        return map;
    }

    @Override
    public PaperUtil<ScoreDetailModel> getScoreDetailByCourseId(Integer courseId,Integer currentPage,Integer pageSize) {
        int totalNum = scoreDAO.getTotalNumByCourseId(courseId);
        List<StudentModel> studentModels = studentService.getStudentByCourseId(courseId);
        totalNum += studentModels.size();
        int startRecord = (currentPage - 1) * pageSize > totalNum ? totalNum : (currentPage - 1) * pageSize;
        List<ScoreDO> scoreDOS = scoreDAO.getScoreByCourseId(courseId,startRecord,pageSize);
        List<ScoreDetailModel> scoreDetailModels = new LinkedList<>();
        List<AdministrativeClassDO> allClass = administrativeClassDAO.getAllClass();
        Map<Integer,AdministrativeClassDO> classDOMap = new HashMap<>();
        for (AdministrativeClassDO administrativeClassDO : allClass){
            classDOMap.put(administrativeClassDO.getId(), administrativeClassDO);
        }
        for (ScoreDO scoreDO : scoreDOS){
            ScoreDetailModel scoreDetailModel = new ScoreDetailModel();
            scoreDetailModel.setId(scoreDO.getId());
            scoreDetailModel.setCourseId(courseId);
            scoreDetailModel.setAccessCredits(scoreDO.getAccessCredits());
            scoreDetailModel.setFailReason(scoreDO.getFailReason());
            scoreDetailModel.setGrade(scoreDO.getGrade());
            scoreDetailModel.setReworkSituation(scoreDO.getReworkSituation());
            StudentDO studentDO = studentDAO.getStudentById(scoreDO.getStudentId());
            scoreDetailModel.setStudentId(studentDO.getStudentId());
            scoreDetailModel.setStudentName(studentDO.getName());
            AdministrativeClassDO administrativeClassDO = classDOMap.get(studentDO.getClassId());
            if (administrativeClassDO != null){
                scoreDetailModel.setClassName(administrativeClassDO.getName());
            }
            scoreDetailModels.add(scoreDetailModel);
        }
        for (StudentModel studentModel : studentModels){
            ScoreDetailModel scoreDetailModel = new ScoreDetailModel();
            scoreDetailModel.setCourseId(courseId);
            scoreDetailModel.setFailReason("");
            scoreDetailModel.setStudentId(studentModel.getStudentId());
            scoreDetailModel.setStudentName(studentModel.getName());
            AdministrativeClassDO administrativeClassDO = classDOMap.get(studentModel.getClassId());
            if (administrativeClassDO != null){
                scoreDetailModel.setClassName(administrativeClassDO.getName());
            }
            scoreDetailModels.add(scoreDetailModel);
        }
        PaperUtil<ScoreDetailModel> paperUtil = new PaperUtil<>();
        paperUtil.setCurrentPage(currentPage);
        paperUtil.setData(scoreDetailModels);
        paperUtil.setPageSize(pageSize);
        paperUtil.setTotalRecord(totalNum);

        return paperUtil;
    }

    @Override
    public ScoreModel getScoreById(Integer scoreId) {
        ScoreDO score = scoreDAO.getScoreById(scoreId);
        return new ScoreModel(score);
    }

    @Override
    public void deleteScore(Integer scoreId) {
        scoreDAO.deleteScore(scoreId);
    }
}
