package com.jas.web.service.impl;

import com.jas.web.bean.domain.TeachingEvaluationDO;
import com.jas.web.dao.IteachingEvaluationDAO;
import com.jas.web.service.ITeacherEvaluationService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class TeacherEvaluationServiceImpl implements ITeacherEvaluationService {
    @Resource
    IteachingEvaluationDAO iteachingEvaluationDAO;


    @Override
    public void evaluationTeacher(String studentId, String teacherId, Integer courseId, Integer evaluationGrade) {
        TeachingEvaluationDO teachingEvaluationDO1 = iteachingEvaluationDAO.getTeacherEvaluationByStudentIdAndTeacherIdAndCourseId(studentId, teacherId, courseId);
        //如果该课程同学已经做了评价，要先删除
        if (teachingEvaluationDO1 != null){
            iteachingEvaluationDAO.deleteTeacherEvaluation(teachingEvaluationDO1.getId());
        }
        TeachingEvaluationDO teachingEvaluationDO = new TeachingEvaluationDO();
        teachingEvaluationDO.setCourseId(courseId);
        teachingEvaluationDO.setEvaluationGrade(evaluationGrade);
        teachingEvaluationDO.setStudentId(studentId);
        teachingEvaluationDO.setTeacherId(teacherId);
        iteachingEvaluationDAO.addTeacherEvaluation(teachingEvaluationDO);
    }
}
