package com.jas.web.dao;

import com.jas.web.bean.domain.TeacherDO;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ITeacherDAO {

    @Insert("insert into beihua.teacher (`teacher_id`,`password`,`name`,`born_date`,`sex`,`education`,`position`,`nation`,`address`,`postalcode`,`mobile`,`identity_card_number`,`addtime`,`modtime`)" +
            " values (#{teacherId},#{password},#{name},#{bornDate},#{sex},#{education},#{position},#{nation},#{address},#{postalcode},#{mobile},#{identityCardNumber},unix_timestamp(),unix_timestamp())")
    @Options(useGeneratedKeys=true, keyProperty="id")
    public int addTeacher(TeacherDO teacherDO);

    @Update("update beihua.teacher set password = #{password}, name = #{name}, born_date = #{bornDate}, sex = #{sex}, education = #{education}, position = #{position}," +
            " nation = #{nation}, address = #{address}, postalcode = #{postalcode}, mobile = #{mobile}, modtime = unix_timestamp() where teacher_id = #{teacherId} and is_del = 0")
    public void updateTeacher(TeacherDO teacherDO);

    @Update("update beihua.teacher set is_del = 1, modtime = unix_timestamp() where teacher_id = #{teacherId}")
    public void deleteTeacher(@Param("teacherId") String teacherId);

    @Select("select * from beihua.teacher where teacher_id = #{teacherId} and is_del = 0")
    public TeacherDO getTeacherByTeacherId(@Param("teacherId") String teacherId);

    @Select("select * from beihua.teacher where is_del = 0 order by #{sort} #{lift} limit ${start},${size}")
    public List<TeacherDO> listTeacherByPage(@Param("start") Integer start, @Param("size") Integer size, @Param("sort") String sort, @Param("lift") String lift);

    @Select("select count(*) from beihua.teacher where is_del = 0")
    public Integer getTotalNum();

    @Select("select * from beihua.teachear where is_del = 0")
    public List<TeacherDO> listTeacherAll();
}
