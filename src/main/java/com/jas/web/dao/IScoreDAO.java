package com.jas.web.dao;

import com.jas.web.bean.domain.AdminDO;
import com.jas.web.bean.domain.ScoreDO;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

@Repository
public interface IScoreDAO {

    @Insert("insert into beihua.score (`course_id`,`student_id`,`grade`,`access_credits`,`is_rework`,`fail_reason`,`rework_situation`,`addtime`,`modtime`)" +
            " values (#{courseId},#{studentId},#{grade},#{accessCredits},#{isRework},#{failReason},#{reworkSituation},unix_timestamp(),unix_timestamp())")
    @Options(useGeneratedKeys=true, keyProperty="id")
    public int addScore(ScoreDO scoreDO);

    @Update("update beihua.score set course_id = #{courseId}, student_id = #{studentId}, grade = #{grade}," +
            " access_credits = #{accessCredits}, is_rework = #{isRework}, fail_reason = #{failReason}, rework_situation = #{reworkSituation}, modtime = unix_timestamp() where id = #{id} and is_del = 0")
    public void updateScore(ScoreDO scoreDO);

    @Update("update beihua.score set is_del = 1, modtime = unix_timestamp() where id = #{id}")
    public void deleteScore(@Param("id") String id);

    @Select("select * from beihua.score where id = #{id} and is_del = 0")
    public ScoreDO getScoreById(@Param("id") Integer id);

}
