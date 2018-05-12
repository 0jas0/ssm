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

    @Update("update beihua.teaching_evaluation set course_id = #{courseId}, evaluation_grade = #{evaluationGrade}, teacher_id = #{teacherId},student_id = #{studentId} modtime = unix_timestamp() where id = #{id} and is_del = 0")
    public void updateTeacherEvaluation(TeachingEvaluationDO teachingEvaluationDO);

    @Update("update beihua.teaching_evaluation set is_del = 1, modtime = unix_timestamp() where id = #{id}")
    public void deleteTeacherEvaluation(@Param("id") Integer id);

    @Select("select * from beihua.teaching_evaluation where id = #{id} and is_del = 0")
    public TeacherDO getTeacherEvaluationByTeacherId(@Param("id") Integer id);

    @Select("select count(*) from beihua.teaching_evaluation where is_del = 0")
    public Integer getTotalNum();

    @Select("select * from beihua.teaching_evaluation where is_del = 0")
    public List<TeachingEvaluationDO> listTeacherAll();
}
