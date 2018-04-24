package com.jas.web.dao;

import com.jas.web.bean.domain.StudentTopicDO;
import com.jas.web.bean.domain.TopicDO;
import com.jas.web.bean.domain.UsernameDO;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ITopicDAO {
    @Insert("insert into `topic` (`topic_id`,`title`,`major`,`limit_student`,`teacher_id`,`status`,`remark`,`addtime`,`modtime`)" +
            " values (#{topicId},#{title},#{major},#{limitStudent},#{teacherId},#{status},#{remark},unix_timestamp(),unix_timestamp())")
    @Options(useGeneratedKeys=true, keyProperty="id")
    public int insert(TopicDO topicDO);

    @Update("update `topic` set title = #{title}, major = #{major}, limit_student = #{limitStudent}, teacher_id = #{teacherId}, status = #{status}, remark = #{remark}, modtime = unix_timestamp()" +
            " where topic_id = #{topicId} and is_del = 0")
    public void upload(TopicDO topicDO);

    @Update("update `topic` set is_del = 1 where topic_id = #{topicId}")
    public void delete(@Param("topicId") String topicId);

    @Select("select * from `topic` where topic_id = #{topicId} and is_del = 0")
    public TopicDO getById(@Param("topicId") String topicId);

    @Select("select * from `topic` where  is_del = 0")
    public List<TopicDO> getAll();

    @Insert("insert into `student_topic` (`topic_id`, `student_id`, `addtime`,`modtime`) values (#{topicId}, #{studentId}, unix_timestamp(), unix_timestamp())")
    public int insertStudentTopic(StudentTopicDO studentTopicDO);

    @Select("select topic_id from `student_topic` where student_id = #{studentId} and is_del = 0")
    public String getTopicIdByStudentId(@Param("studentId") String studentId);

    @Update("update `student_topic` set is_del = 1 where student_id = #{studentId}")
    public void deleteStudentTopic(@Param("studentId") String studentId);

    @Select("select student_id from `student_topic` where topic_id = #{topicId} and is_del = 0")
    public List<String> getStudentByTopicId(@Param("topicId") String topicId);

}
