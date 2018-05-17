package com.jas.web.dao;

import com.jas.web.bean.domain.TeacherDO;
import com.jas.web.bean.domain.TeachingEvaluationDO;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IteachingEvaluationDAO {

    @Insert("insert into beihua.teaching_evaluation (`course_id`,`evaluation_grade`,`teacher_id`,`student_id`,`addtime`,`modtime`)" +
            " values (#{courseId},#{evaluationGrade},#{teacherId},#{studentId},unix_timestamp(),unix_timestamp())")
    @Options(useGeneratedKeys=true, keyProperty="id")
    public int addTeacherEvaluation(TeachingEvaluationDO teachingEvaluationDO);

    @Update("update beihua.teaching_evaluation set is_del = 1, modtime = unix_timestamp() where id = #{id}")
    public void deleteTeacherEvaluation(@Param("id") Integer id);

    @Select("select * from beihua.teaching_evaluation where teacher_id = #{teacherId} and is_del = 0")
    public List<TeachingEvaluationDO> getTeacherEvaluationByTeacherId(@Param("teacherId") String teacherId);


    @Select("select * from beihua.teaching_evaluation where student_id = #{studentId} and teacher_id = #{teacherId} and course_id = #{courseId} and is_del = 0 limit 1")
    TeachingEvaluationDO getTeacherEvaluationByStudentIdAndTeacherIdAndCourseId(@Param("studentId") String studentId,@Param("teacherId") String teacherId,@Param("courseId") Integer courseId);
}
