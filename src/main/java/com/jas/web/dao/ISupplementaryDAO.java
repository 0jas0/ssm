package com.jas.web.dao;

import com.jas.web.bean.domain.AdminDO;
import com.jas.web.bean.domain.SupplementaryDO;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

@Repository
public interface ISupplementaryDAO {

    @Insert("insert into beihua.supplementary (`course_id`,`student_id`,`grade`,`addtime`,`modtime`)" +
            " values (#{courseId},#{studentId},#{grade},unix_timestamp(),unix_timestamp())")
    @Options(useGeneratedKeys=true, keyProperty="id")
    public int addSupplementtaryDO(SupplementaryDO supplementaryDO);

    @Update("update beihua.supplementary set course_id = #{courseId}, student_id = #{studentId}, grade = #{grade} modtime = unix_timestamp() where id = #{id} and is_del = 0")
    public void updateSupplementtaryDO(AdminDO adminDO);

    @Update("update beihua.supplementary set is_del = 1, modtime = unix_timestamp() where id = #{id}")
    public void deleteSupplementtaryDO(@Param("id") Integer id);

    @Select("select * from beihua.supplementary where name = #{name} and is_del = 0")
    public AdminDO getById(@Param("id") Integer id);

}
