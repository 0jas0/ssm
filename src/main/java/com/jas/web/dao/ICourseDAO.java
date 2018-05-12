package com.jas.web.dao;

import com.jas.web.bean.domain.AdministrativeClassDO;
import com.jas.web.bean.domain.CourseDO;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ICourseDAO {

    @Insert("insert into beihua.course (`teacher_id`,`name`,`course_start`,`course_end`,`class_id`,`semester`,`college`,`period`,`credit`,`type`,`addtime`,`modtime`)" +
            " values (#{teacherId},#{name},#{courseStart},#{courseEnd},#{classId},#{semester},#{college},#{period},#{credit},#{type},unix_timestamp(),unix_timestamp())")
    @Options(useGeneratedKeys=true, keyProperty="id")
    public int addCourse(CourseDO courseDO);

    @Update("update beihua.course set teacher_id = #{teacherId}, name = #{name}, course_start = #{courseStart}, course_end = #{courseEnd}, class_id = #{classId}," +
            " semester = #{semester}, college = #{college}, period = #{period}, credit = #{credit}, type = #{type}, modtime = unix_timestamp() where id = #{id} and is_del = 0")
    public void updateCourse(CourseDO courseDO);

    @Update("update beihua.course set is_del = 1 where id = #{id}")
    public void deleteCourse(@Param("id") Integer id);

    @Select("select * from beihua.course where id = #{id} and is_del = 0")
    public CourseDO getCourseById(@Param("id") Integer id);

    @Select("select * from beihua.course where is_del = 0")
    public List<CourseDO> getCourseAll();
}
