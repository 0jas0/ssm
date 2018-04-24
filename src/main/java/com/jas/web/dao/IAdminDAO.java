package com.jas.web.dao;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

@Repository
public interface IAdminDAO {
    @Select("select password from `admin` where name = #{adminName} and is_del = 0")
    public String getPasswordByAdmin(@Param("adminName") String adminName);
}
