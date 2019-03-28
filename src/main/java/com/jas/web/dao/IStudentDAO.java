package com.jas.web.dao;

import com.jas.web.bean.domain.StudentDO;
import com.jas.web.bean.domain.TeacherDO;
import org.apache.ibatis.annotations.*;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IStudentDAO {

    @Insert("insert into beihua.student (`student_id`,`photo`,`password`,`name`,`born_date`,`sex`,`political_outlook`,`class_id`,`college`,`major`,`native_place`,`nation`,`address`,`postalcode`,`mobile`,`identity_card_number`,`addtime`,`modtime`)" +
            " values (#{studentId},#{photo},#{password},#{name},#{bornDate},#{sex},#{politicalOutlook},#{classId},#{college},#{major},#{nativePlace},#{nation},#{address},#{postalcode},#{mobile},#{identityCardNumber},unix_timestamp(),unix_timestamp())")
    @Options(useGeneratedKeys=true, keyProperty="id")
    public int addStudent(StudentDO studentDO);

    @Update("update beihua.student set password = #{password}, photo = #{photo}, name = #{name}, born_date = #{bornDate}, sex = #{sex}, political_outlook = #{politicalOutlook}, class_id = #{classId}, college = #{college}," +
            " major = #{major},native_place = #{nativePlace}, nation = #{nation}, address = #{address}, postalcode = #{postalcode}, identity_card_number = #{identityCardNumber}, mobile = #{mobile}, modtime = unix_timestamp() where student_id = #{studentId} and is_del = 0")
    public void updateStudent(StudentDO studentDO);

    @Update("update beihua.student set is_del = 1, modtime = unix_timestamp() where student_id = #{studentId}")
    public void deleteStudent(@Param("studentId") String studentId);

    @Select("select * from beihua.student where student_id = #{studentId} and is_del = 0")
    public StudentDO getStudentByStudentId(@Param("studentId") String studentId);

    public List<StudentDO> listStudentByPage(@Param("keyword") String keyword, @Param("start") Integer start, @Param("size") Integer size, @Param("sort") String sort, @Param("lift") String lift);

    @Select("select count(*) from beihua.student where is_del = 0")
    public int getTotalNum();

    @Select("select * from beihua.student where id = #{studentId} and is_del = 0")
    StudentDO getStudentById(@Param("studentId") Integer studentId);

    @Select("select * from beihua.student where is_del = 0")
    List<StudentDO> getAllStudent();

    @Select("select * from beihua.student where class_id = #{classId} and is_del = 0")
    List<StudentDO> getStudentByClassId(Integer classId);
}
