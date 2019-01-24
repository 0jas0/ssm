package com.jas.web.service.impl;

import com.jas.web.bean.domain.SystemSettingDO;
import com.jas.web.bean.model.SystemSettingModel;
import com.jas.web.dao.ISystemSettingDAO;
import com.jas.web.service.ISystemSettingService;
import com.jas.web.utils.DateUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * <一句话描述>,
 * <详细介绍>,
 *
 * @author 琚安生
 * @since 设计wiki | 需求wiki
 */
@Service
public class SystemSettingServiceImpl implements ISystemSettingService{

    @Resource
    private ISystemSettingDAO systemSettingDAO;

    @Override
    public SystemSettingModel getSystemSettingModel() {
        SystemSettingDO systemSettingDO = systemSettingDAO.getSystemSettingDO();
        return new SystemSettingModel(systemSettingDO);
    }

    @Override
    public void updateSystemSetting(SystemSettingModel systemSettingModel) {
        SystemSettingDO systemSettingDO = new SystemSettingDO();
        long startTime = DateUtil.getMillisFromStringByFormat(systemSettingModel.getChoiceStartStr(), "yyyy-MM-dd") / 1000;
        long endTime = DateUtil.getMillisFromStringByFormat(systemSettingModel.getChoiceEndStr(), "yyyy-MM-dd") / 1000;
        systemSettingDO.setChoiceStartTime((int)startTime);
        systemSettingDO.setChoiceEndTime((int)endTime);
        systemSettingDO.setId(systemSettingModel.getId());
        systemSettingDAO.updateSystemSettingDO(systemSettingDO);
    }
}
