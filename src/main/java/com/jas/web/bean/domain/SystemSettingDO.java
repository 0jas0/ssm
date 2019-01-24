package com.jas.web.bean.domain;

import com.jas.web.bean.model.SystemSettingModel; /**
 * <一句话描述>,
 * <详细介绍>,
 *
 * @author 琚安生
 * @since 设计wiki | 需求wiki
 */
public class SystemSettingDO extends BaseDO{
    private Integer choiceStartTime;
    private Integer choiceEndTime;


    public SystemSettingDO() {
    }

    public Integer getChoiceStartTime() {
        return choiceStartTime;
    }

    public void setChoiceStartTime(int choiceStartTime) {
        this.choiceStartTime = choiceStartTime;
    }

    public Integer getChoiceEndTime() {
        return choiceEndTime;
    }

    public void setChoiceEndTime(int choiceEndTime) {
        this.choiceEndTime = choiceEndTime;
    }


}
