package com.jas.web.dao;

import com.jas.web.bean.AdminDO;
import com.jas.web.bean.StudentDO;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IAdminDAO {

    @Select("select * from ability.admin where name = #{name} and is_del = 0")
    public AdminDO getAdminByName(@Param("name") String name);

}
