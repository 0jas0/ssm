package com.jas.web.dao;

import com.jas.web.domain.UserDO;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IUserDAO {
    @Select("select * from test.user")
    public List<UserDO>  listUsers();

}
