package com.jas.web.dao;


import com.jas.web.bean.domain.StudentTopicDO;
import com.jas.web.bean.domain.StudentUploadDO;
import com.jas.web.bean.domain.TopicDO;
import com.jas.web.bean.domain.UsernameDO;
import com.jas.web.bean.model.TopicModel;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface ScoreManagementDAO {

    //学生查询自己的成绩
    @Select("select * from `student_upload` su" +
            "LEFT JOIN `student_topic` st ON st.id = su.student_topic_id" +
            "LEFT JOIN `username` us ON us.id = st.student_id" +
            "where  st.student_id = #{studentId} " +
            "AND us.`status` = 0" +
            "and is_del = 0 order by su.id desc")
    public List<StudentUploadDO> getByStudentId(@Param("studentId") Integer studentId);

    //老师查询成绩
    @Select("select su.*,u.name AS studentName,u.id AS studentId from `student_upload` su" +
            "LEFT JOIN `student_topic` st ON st.id = su.student_topic_id" +
            "LEFT JOIN `topic` t ON t.topic_id = st.topic_id" +
            "LEFT JOIN  `username` u ON u.id = st.student_id" +
            "LEFT JOIN `username` us ON us.id = t.teacher_id" +
            "where  us.id = #{teacherId}" +
            "AND u.`status` =0" +
            "AND us.`status` = 1" +
            "AND t.`status` = 1" +
            "AND t.is_del =0" +
            "and su.is_del = 0 " +
            "order by su.student_topic_id , su.`status`desc")
    public List<StudentUploadDO> getByTeacherId(@Param("teacherId") Integer teacherId);

    //学生
    @Select("select * from `student_topic` where student_id = #{studentId} and is_del = 0 order by id desc")
    public StudentTopicDO getByStudent(@Param("studentId") Integer studentId);

    @Select("select * from `student_upload` where student_topic_id = #{studentTopicId} and is_del = 0 order by id desc")
    public List<StudentUploadDO> getByStudentTopicId(@Param("studentTopicId") Integer studentTopicId);

    //老师
    @Select("select * from `topic` where teacher_id = #{teacherId} and status = 1 and is_del = 0 order by id desc")
    public List<TopicDO> getByTeacher(@Param("teacherId") Integer teacherId);

    @Select("select * from `student_topic` where topic_id = #{topicId} and is_del = 0 order by id desc")
    public List<StudentTopicDO> getByTopicId(@Param("topicId") String topicId);

    @Select("select * from `username` where id = #{studentId} and status = 0 and is_del = 0 order by id desc")
    public UsernameDO getByStudentOwnId(@Param("studentId") Integer studentId);

    List<UsernameDO> selectStudent(@Param("name") String name,@Param("serialNumber") String serialNumber,@Param("major") String major);





}
