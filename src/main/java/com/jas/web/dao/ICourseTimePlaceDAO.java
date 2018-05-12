package com.jas.web.dao;

import com.jas.web.bean.domain.CourseDO;
import com.jas.web.bean.domain.CourseTimePlaceDO;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ICourseTimePlaceDAO {

    @Insert("insert into beihua.course_time_place (`course_id`,`name`,`course_week`,`course`,`course_place`,`addtime`,`modtime`)" +
            " values (#{courseId},#{name},#{courseWeek},#{course},#{coursePlace},unix_timestamp(),unix_timestamp())")
    @Options(useGeneratedKeys=true, keyProperty="id")
    public int addCourseTimePlace(CourseTimePlaceDO courseTimePlaceDO);

    @Update("update beihua.course_time_place set name = #{name}, course_week = #{courseWeek}, course = #{course}, course_place = #{coursePlace} modtime = unix_timestamp() where course_id = #{courseId} and is_del = 0")
    public void updateCourseTimePlace(CourseTimePlaceDO courseTimePlaceDO);

    @Update("update beihua.course_time_place set is_del = 1 where id = #{id}")
    public void deleteCourseTimePlace(@Param("id") Integer id);

    @Select("select * from beihua.course_time_place where id = #{id} and is_del = 0")
    public CourseTimePlaceDO getCourseById(@Param("id") Integer id);

    @Select("select * from beihua.course_time_place where is_del = 0")
    public List<CourseTimePlaceDO> getCourseAll();
}
