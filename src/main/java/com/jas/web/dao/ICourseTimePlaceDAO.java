package com.jas.web.dao;

import com.jas.web.bean.domain.CourseDO;
import com.jas.web.bean.domain.CourseTimePlaceDO;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ICourseTimePlaceDAO {

    @Insert("insert into beihua.course_time_place (`course_id`,`course_week`,`course_time`,`course_place`,`class_id`,`addtime`,`modtime`)" +
            " values (#{courseId},#{courseWeek},#{courseTime},#{coursePlace},#{classId},unix_timestamp(),unix_timestamp())")
    @Options(useGeneratedKeys=true, keyProperty="id")
    public int addCourseTimePlace(CourseTimePlaceDO courseTimePlaceDO);

    @Update("update beihua.course_time_place set course_id = #{courseId}, class_id = #{classId}, course_week = #{courseWeek}, course_time = #{courseTime}, course_place = #{coursePlace}, modtime = unix_timestamp() where id = #{id} and is_del = 0")
    public void updateCourseTimePlace(CourseTimePlaceDO courseTimePlaceDO);

    @Update("update beihua.course_time_place set is_del = 1 where id = #{id}")
    public void deleteCourseTimePlace(@Param("id") Integer id);

    @Select("select * from beihua.course_time_place where id = #{id} and is_del = 0")
    public CourseTimePlaceDO getCourseTimePlaceById(@Param("id") Integer id);

    @Select("select * from beihua.course_time_place where is_del = 0")
    public List<CourseTimePlaceDO> getCourseTimePlaceAll();

    @Select("select * from beihua.course_time_place where course_id = #{courseId} and is_del = 0")
    public List<CourseTimePlaceDO> getCourseTimePlaceByCourseId(@Param("courseId") Integer courseId);

    @Select("select * from beihua.course_time_place where class_id = #{classId} and is_del = 0")
    List<CourseTimePlaceDO> getCourseTimePlaceByClassId(@Param("classId") Integer classId);

    @Select("select bc.semester from beihua.course_time_place bctp left join beihua.course bc on bctp.course_id = bc.id where " +
            "class_id = #{classId} and bc.is_del = 0 and bctp.is_del = 0 order by bc.semester desc limit 1")
    Integer getNewSemesterByClassId(@Param("classId") Integer classId);

    @Select("select * from beihua.course_time_place where class_id = #{classId} and is_del = 0 group by course_id")
    List<CourseTimePlaceDO> getUniqueCourseTimePlaceByClassId(@Param("classId") Integer classId);

    @Select("select class_id from beihua.course_time_place where course_id = #{courseId} and is_del = 0 group by class_id")
    List<Integer> getClassIdByCourseId(@Param("courseId") Integer courseId);
}
