package com.jas.web.bean.model;

import com.jas.web.bean.domain.BaseDO;
import com.jas.web.bean.domain.SystemSettingDO;
import com.jas.web.utils.DateUtil;

/**
 * <一句话描述>,
 * <详细介绍>,
 *
 * @author 琚安生
 * @since 设计wiki | 需求wiki
 */
public class SystemSettingModel{
    private Integer id;
    private String choiceStartStr;
    private String choiceEndStr;
    private Integer choiceStartTime;
    private Integer choiceEndTime;
    private boolean canChoiceCourse;

    public SystemSettingModel() {
    }
    public SystemSettingModel(SystemSettingDO systemSettingDO) {
        this.id = systemSettingDO.getId();
        if (systemSettingDO.getChoiceStartTime() != null){
            this.choiceStartStr = DateUtil.formatSeconds(systemSettingDO.getChoiceStartTime(), "yyyy-MM-dd");
        }
        if (systemSettingDO.getChoiceEndTime() != null){
            this.choiceEndStr = DateUtil.formatSeconds(systemSettingDO.getChoiceEndTime(), "yyyy-MM-dd");
        }
        this.choiceStartTime = systemSettingDO.getChoiceStartTime();
        this.choiceEndTime = systemSettingDO.getChoiceEndTime();
    }

    public void setChoiceStartTime(Integer choiceStartTime) {
        this.choiceStartTime = choiceStartTime;
    }

    public void setChoiceEndTime(Integer choiceEndTime) {
        this.choiceEndTime = choiceEndTime;
    }

    public boolean isCanChoiceCourse() {
        return canChoiceCourse;
    }

    public void setCanChoiceCourse(boolean canChoiceCourse) {
        this.canChoiceCourse = canChoiceCourse;
    }



    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getChoiceStartStr() {
        return choiceStartStr;
    }

    public void setChoiceStartStr(String choiceStartStr) {
        this.choiceStartStr = choiceStartStr;
    }

    public String getChoiceEndStr() {
        return choiceEndStr;
    }

    public void setChoiceEndStr(String choiceEndStr) {
        this.choiceEndStr = choiceEndStr;
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
