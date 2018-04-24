package com.jas.web.dao;

import com.github.pagehelper.Page;
import com.jas.web.bean.domain.UsernameDO;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IUsernameDAO {

    @Insert("insert into `username` (`serial_number`,`password`,`name`,`sex`,`email`,`major`,`mobile`,`status`,`addtime`,`modtime`)" +
            " values (#{serialNumber},#{password},#{name},#{sex},#{email},#{major},#{mobile},#{status},unix_timestamp(),unix_timestamp())")
    @Options(useGeneratedKeys=true, keyProperty="id")
    public int insert(UsernameDO usernameDO);

    @Update("update `username` set serial_number = #{serialNumber}, password = #{password}, name = #{name}, sex = #{sex}, email = #{email}, major = #{major}, mobile = #{mobile}, modtime = unix_timestamp()" +
            " where id = #{id} and status = #{status} and is_del = 0")
    public void upload(UsernameDO usernameDO);

    @Update("update `username` set is_del = 1 where serial_number = #{serialNumber} and status = #{status}")
    public void delete(@Param("serialNumber") String serialNumber, @Param("status") Integer status);

    @Update("update `username` set is_del = 1 where id = #{id}")
    public void deleteById(@Param("id") Integer id);


    @Select("select * from `username` where serial_number = #{serialNumber} and status = #{status}")
    public UsernameDO getById(@Param("serialNumber") String serialNumber, @Param("status") Integer status);

    @Select("select * from `username` where status = #{status} and is_del = 0 order by id desc")
    public List<UsernameDO> getByStatus(@Param("status") Integer status);

}
