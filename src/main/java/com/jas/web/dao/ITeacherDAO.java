package com.jas.web.dao;

import org.apache.ibatis.annotations.Insert;
import org.springframework.stereotype.Repository;

@Repository
public interface ITeacherDAO {
    @Insert("insert into beihua.teacher ")
    public int addTeacher(TeacherDO teacherDO);
}
