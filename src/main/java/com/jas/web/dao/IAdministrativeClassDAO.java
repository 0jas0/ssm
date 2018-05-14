package com.jas.web.dao;

import com.jas.web.bean.domain.AdministrativeClassDO;
import com.jas.web.bean.domain.StudentDO;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IAdministrativeClassDAO {

    @Insert("insert into beihua.administrative_class (`class_id`,`name`,`class_number`,`instructor`,`major`,`college`,`addtime`,`modtime`)" +
            " values (#{classId},#{name},#{classNumber},#{instructor},#{major},#{college},unix_timestamp(),unix_timestamp())")
    @Options(useGeneratedKeys=true, keyProperty="id")
    public int addAdministrativeClass(AdministrativeClassDO administrativeClassDO);

    @Update("update beihua.administrative_class set name = #{name}, class_number = #{classNumber}, instructor = #{instructor}, major = #{major}," +
            " college = #{college}, modtime = unix_timestamp() where class_id = #{classId} and is_del = 0")
    public void updateAdministrativeClass(AdministrativeClassDO administrativeClassDO);

    @Update("update beihua.administrative_class set is_del = 1, modtime = unix_timestamp() where class_id = #{classId}")
    public void deleteAdministrativeClass(@Param("classId") String classId);

    @Update("update beihua.administrative_class set is_del = 1, modtime = unix_timestamp() where id = #{id}")
    public void deleteById(@Param("id") Integer id);

    @Select("select * from beihua.administrative_class where class_id = #{classId} and is_del = 0")
    public AdministrativeClassDO getAdminstrativeClassByClassId(@Param("classId") String classId);

    @Select("select * from beihua.administrative_class where is_del = 0")
    public List<AdministrativeClassDO> getAdminstrativeClassAll();

    @Select("select * from beihua.administrative_class where college = #{college} and major = #{major} and is_del = 0")
    public List<AdministrativeClassDO> getAdminstrativeClasssByCollegeMajor(@Param("college") Integer college,@Param("major") Integer major);

    @Select("select count(*) from beihua.administrative_class where is_del = 0")
    public int getTotalNum();

    @Select("select * from beihua.administrative_class where is_del = 0 order by #{sort} #{lift} limit ${start},${size}")
    public List<AdministrativeClassDO> listByPage(@Param("start") Integer start, @Param("size") Integer size, @Param("sort") String sort, @Param("lift") String lift);

    @Select("select * from beihua.administrative_class where id = #{id} and is_del = 0")
    public AdministrativeClassDO getAdminstrativeClassById(@Param("id") Integer id);
}
