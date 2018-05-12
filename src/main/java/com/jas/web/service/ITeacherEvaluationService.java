package com.jas.web.service;

public interface ITeacherEvaluationService {
    void evaluationTeacher(String studentId, String teacherId, Integer courseId, Integer evaluationGrade);
}
