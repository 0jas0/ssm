package com.jas.web.dao;

import com.jas.web.bean.domain.TopicPhaseTimeDO;
import com.jas.web.bean.domain.UsernameDO;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ITopicPhaseTimeDAO {
    @Insert("insert into `topic_phase_time` (`topic_id`,`start_time`,`end_time`,`status`,`addtime`,`modtime`)" +
            " values (#{topicId},#{startTime},#{endTime},#{status},unix_timestamp(),unix_timestamp())")
    @Options(useGeneratedKeys=true, keyProperty="id")
    public int insert(TopicPhaseTimeDO topicPhaseTimeDO);

    @Update("update `topic_phase_time` set start_time = #{startTime}, end_time = #{endTime}, status = #{status}, modtime = unix_timestamp()" +
            " where topic_id = #{topicId} and is_del = 0")
    public void upload(TopicPhaseTimeDO topicPhaseTimeDO);

    @Update("update `topic_phase_time` set is_del = 1 where topic_id = #{topicId}")
    public void delete(@Param("topicId") String topicId, @Param("status") Integer status);

    @Select("select * from `topic_phase_time` where topic_id = #{topicId} and is_del = 0")
    public TopicPhaseTimeDO getById(@Param("topicId") String topicId);

    @Select("select * from `topic_phase_time` where  is_del = 0")
    public List<TopicPhaseTimeDO> getAll();
}
