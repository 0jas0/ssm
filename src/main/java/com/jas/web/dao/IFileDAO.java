package com.jas.web.dao;

import com.jas.web.bean.FileDO;
import com.jas.web.bean.StudentDO;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.io.File;
import java.util.List;

@Repository
public interface IFileDAO {

    @Insert("insert into ability.file (`student_id`,`path`,`file_name`,`file_type`,`addtime`,`modtime`)" +
            " values (#{studentId},#{path},#{fileName},#{fileType},unix_timestamp(),unix_timestamp())")
    @Options(useGeneratedKeys=true, keyProperty="id")
    public int addFile(FileDO fileDO);

    @Update("update ability.file set is_del = 1 where id = #{id}")
    public void deleteFile(@Param("id") Integer id);

    @Select("select * from ability.file where student_id = #{studentId} and is_del = 0")
    public List<FileDO> getFileByStudentId(@Param("studentId") String studentId);

}
