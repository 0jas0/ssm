package com.jas.web.dao;

import com.jas.web.bean.domain.AdministrativeClassDO;
import com.jas.web.bean.domain.CourseDO;
import com.jas.web.bean.domain.CourseTimePlaceDO;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ICourseDAO {

    @Insert("insert into beihua.course (`teacher_id`,`name`,`course_start`,`course_end`,`semester`,`college`,`period`,`credit`,`type`,`addtime`,`modtime`)" +
            " values (#{teacherId},#{name},#{courseStart},#{courseEnd},#{semester},#{college},#{period},#{credit},#{type},unix_timestamp(),unix_timestamp())")
    @Options(useGeneratedKeys=true, keyProperty="id")
    public int addCourse(CourseDO courseDO);

    @Update("update beihua.course set teacher_id = #{teacherId}, name = #{name}, course_start = #{courseStart}, course_end = #{courseEnd}," +
            " semester = #{semester}, college = #{college}, period = #{period}, credit = #{credit}, type = #{type}, modtime = unix_timestamp() where id = #{id} and is_del = 0")
    public void updateCourse(CourseDO courseDO);

    @Update("update beihua.course set is_del = 1 where id = #{id}")
    public void deleteCourse(@Param("id") Integer id);

    @Select("select * from beihua.course where id = #{id} and is_del = 0")
    public CourseDO getCourseById(@Param("id") Integer id);

    @Select("select * from beihua.course where is_del = 0")
    public List<CourseDO> getCourseAll();

    @Select("select * from beihua.course where teacher_id = #{teacherId} and is_del = 0")
    public List<CourseDO> getCourseByTeacherId(@Param("teacherId") String teacherId);

    @Select("select count(*) from beihua.course where is_del = 0")
    public int getTotalNum();

    @Select("select * from beihua.course where is_del = 0 order by #{sort} #{lift} limit ${start},${size}")
    List<CourseDO> listCourseByPage(@Param("start") Integer start, @Param("size") Integer size, @Param("sort") String sort, @Param("lift") String lift);

    @Select("select * from beihua.course where teacher_id = #{teacherId} and is_del = 0 order by id desc limit #{startRecord},#{pageSize}")
    List<CourseDO> getCourseByTeacherIdAndPage(@Param("teacherId") String teacherId,@Param("startRecord") Integer startRecord, @Param("pageSize") Integer pageSize);

    @Select("select count(*) from beihua.course where teacher_id = #{teacherId} and is_del = 0")
    int getCourseNumByTeacherId(@Param("teacherId") String teacherId);

    @Select("select bc.* from beihua.course bc left join beihua.course_time_place bct on bc.id = bct.course_id where bc.type = #{type} and bct.class_id = #{classId} and bc.is_del = 0")
    List<CourseDO> getCourseByClassAndType(@Param("classId") Integer classId,@Param("type") Integer type);
}
