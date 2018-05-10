package com.jas.web.dao;

import com.jas.web.bean.domain.AdminDO;
import com.jas.web.bean.domain.TeacherDO;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IAdminDAO {

    @Insert("insert into beihua.admin (`name`,`password`,`addtime`,`modtime`)" +
            " values (#{name},#{password},unix_timestamp(),unix_timestamp())")
    @Options(useGeneratedKeys=true, keyProperty="id")
    public int addAdmin(AdminDO adminDO);

    @Update("update beihua.admin set password = #{password}, name = #{name} where id = #{id} and is_del = 0")
    public void updateAdmin(AdminDO adminDO);

    @Update("update beihua.admin set is_del = 1 where id = #{id}")
    public void deleteAdmin(@Param("id") String id);

    @Select("select * from beihua.admin where name = #{name} and is_del = 0")
    public AdminDO getByName(@Param("name") String name);

}
