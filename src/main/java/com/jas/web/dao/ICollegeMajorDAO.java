package com.jas.web.dao;

import com.jas.web.bean.domain.AdminDO;
import com.jas.web.bean.domain.CollegeMajorDO;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ICollegeMajorDAO {


    @Select("select * from beihua.college_major where is_del = 0")
    public List<CollegeMajorDO> getAllCollegeMajor();

}
