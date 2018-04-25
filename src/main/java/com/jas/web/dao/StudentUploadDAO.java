package com.jas.web.dao;

import com.jas.web.bean.domain.StudentUploadDO;
import org.apache.ibatis.annotations.*;

import javax.annotation.Resource;
import java.util.List;

@Resource
public interface StudentUploadDAO {
    @Insert("insert into `student_upload` (`student_topic_id`,`upload_time`,`name`,`path`,`score`,`status`,`addtime`,`modtime`)" +
            " values (#{studentTopicId},#{uploadTime},#{name},#{path},#{score},#{status},unix_timestamp(),unix_timestamp())")
    @Options(useGeneratedKeys=true, keyProperty="id")
    public int insert(StudentUploadDO studentUploadDO);

    @Update("update `student_upload` set is_del = 1 where student_topic_id = #{studentTopicId}")
    public void delete(@Param("topicId") String topicId, @Param("status") Integer status);

    @Select("select * from `student_upload` where student_topic_id = #{studentTopicId}")
    public StudentUploadDO getById(@Param("studentTopicId") Integer studentTopicId);

    @Select("select su.* from `student_upload` su left join `student_topic` st on su.student_topic_id = su.id where st.student_id = #{id} order by su.status asc")
    public List<StudentUploadDO> getByStudentId(@Param("id") String id);
}
