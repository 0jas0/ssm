package com.jas.web.dao;

import com.jas.web.bean.StudentDO;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IStudentDAO {

    @Insert("insert into ability.student (`student_id`,`password`,`real_name`,`major`,`email`,`mobile`,`identity_card_number`,`evaluate`,`addtime`,`modtime`)" +
            " values (#{studentId},#{password},#{realName},#{major},#{email},#{mobile},#{identityCardNumber},#{evaluate},unix_timestamp(),unix_timestamp())")
    @Options(useGeneratedKeys=true, keyProperty="id")
    public int addStudent(StudentDO studentDO);

    @Update("update ability.student set password = #{password}, real_name = #{realName}, major = #{major}, email = #{email}, mobile = #{mobile}, identity_card_number = #{identityCardNumber}, evaluate = #{evaluate},modtime = unix_timestamp() where student_id = #{studentId} and is_del = 0")
    public void updateStudent(StudentDO studentDO);

    @Update("update ability.student set is_del = 1 where student_id = #{studentId}")
    public void deleteStudent(@Param("studentId") String studentId);

    @Select("select * from ability.student where student_id = #{studentId} and is_del = 0")
    public StudentDO getStudentByStudentId(@Param("studentId") String studentId);

    @Select("select * from ability.student where is_del = 0 order by #{sort} #{lift} limit ${start},${size}")
    public List<StudentDO> listStudentByPage(@Param("start") Integer start, @Param("size") Integer size, @Param("sort") String sort, @Param("lift") String lift);

    @Select("select * from ability.student where is_del = 0")
    public List<StudentDO> listStudentAll();

}
