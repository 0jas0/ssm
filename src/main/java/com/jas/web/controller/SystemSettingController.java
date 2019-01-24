package com.jas.web.controller;

import com.jas.web.bean.model.StudentModel;
import com.jas.web.bean.model.SystemSettingModel;
import com.jas.web.service.ISystemSettingService;
import com.jas.web.utils.ResponseUtil;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

/**
 * <一句话描述>,
 * <详细介绍>,
 *
 * @author 琚安生
 * @since 设计wiki | 需求wiki
 */
@Controller
@RequestMapping("/sys")
public class SystemSettingController {

    @Resource
    ISystemSettingService systemSettingService;

    @RequestMapping("/sys_view")
    public String studentListView(Model model){
        SystemSettingModel systemSettingModel = systemSettingService.getSystemSettingModel();
        model.addAttribute("systemModel", systemSettingModel);
        return "sys/sys_view";
    }

    @RequestMapping(value = "/ajax-modify-sys")
    @ResponseBody
    public Object modifySys(SystemSettingModel systemSettingModel){
        try {
            systemSettingService.updateSystemSetting(systemSettingModel);
            //数据的校验
            return ResponseUtil.constructResponse(ResponseUtil.RETURN_STATUS_SUCCESS, "修改系统信息成功", null);
        }catch (Exception e){
            e.printStackTrace();
            return ResponseUtil.constructResponse(ResponseUtil.RETURN_STATUS_FAILED,"添加系统信息失败",null);
        }
    }
}
