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

    @Update("update beihua.choice_courses set course_id = #{courseId}, student_id = #{studentId}, modtime = unix_timestamp() where id = #{id} and is_del = 0")
    public void updateChoiceCourse(ChoiceCoursesDO choiceCoursesDO);

    @Update("update beihua.choice_courses set is_del = 1 where id = #{id}")
    public void deleteChoiceCourse(@Param("id") Integer id);

    @Select("select * from beihua.choice_courses where id = #{id} and is_del = 0")
    public ChoiceCoursesDO getChoiceById(@Param("id") Integer id);

    @Select("select * from beihua.choice_courses where is_del = 0")
    public List<ChoiceCoursesDO> getCourseAll();
}
