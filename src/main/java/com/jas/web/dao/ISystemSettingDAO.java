package com.jas.web.dao;

import com.jas.web.bean.domain.SystemSettingDO;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

/**
 * <一句话描述>,
 * <详细介绍>,
 *
 * @author 琚安生
 * @since 设计wiki | 需求wiki
 */
@Repository
public interface ISystemSettingDAO {
    @Select("select * from beihua.system_setting order by id limit 1")
    SystemSettingDO getSystemSettingDO();

    @Update("update beihua.system_setting set `choice_start_time` = #{choiceStartTime}, `choice_end_time` = #{choiceEndTime}, modtime = unix_timestamp() where id = #{id} ")
    void updateSystemSettingDO(SystemSettingDO systemSettingDO);
}
