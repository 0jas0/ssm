package com.jas.web.dao;

import com.jas.web.bean.domain.ChoiceCoursesDO;
import com.jas.web.bean.domain.CourseTimePlaceDO;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IChoiceCoursesDAO {

    @Insert("insert into beihua.choice_courses (`course_id`,`student_id`,`addtime`,`modtime`)" +
            " values (#{courseId},#{studentId},unix_timestamp(),unix_timestamp())")
    @Options(useGeneratedKeys=true, keyProperty="id")
    public int addChoiceCourses(ChoiceCoursesDO choiceCoursesDO);


    @Update("update beihua.choice_courses set is_del = 1 where student_id = #{studentId} and course_id = #{courseId}")
    public void deleteByStudentIdAndCourseId(@Param("studentId") Integer studentId,@Param("courseId") Integer courseId);

    @Select("select * from beihua.choice_courses where student_id = #{studentId} and is_del = 0")
    public List<ChoiceCoursesDO> getCourseByStudentId(@Param("studentId") Integer studentId);

    @Select("select bc.semester from beihua.choice_courses bcc left join beihua.course bc" +
            " on bcc.course_id = bc.id where bcc.student_id = #{studentId} and bc.is_del = 0 and bcc.is_del = 0 order by bc.semester limit 1")
    Integer getNewSemesterByStudentId(@Param("studentId")Integer studentId);

    @Select("select * from beihua.choice_courses where course_id = #{courseId} and is_del = 0")
    List<ChoiceCoursesDO> getChoiceByCourseId(@Param("courseId")Integer courseId);

    List<ChoiceCoursesDO> getCourseByStudentIds(@Param("list") List<Integer> list);
}
