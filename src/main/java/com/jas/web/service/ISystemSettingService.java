package com.jas.web.service;

import com.jas.web.bean.model.SystemSettingModel;

/**
 * <一句话描述>,
 * <详细介绍>,
 *
 * @author 琚安生
 * @since 设计wiki | 需求wiki
 */
public interface ISystemSettingService {
    SystemSettingModel getSystemSettingModel();

    void updateSystemSetting(SystemSettingModel systemSettingModel);
}
