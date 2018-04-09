package com.jas.web.controller;

import com.alibaba.fastjson.JSONObject;
import com.jas.web.bean.model.TeacherModel;
import com.jas.web.service.ITeacherService;
import com.jas.web.utils.ResponseUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;

@Controller
@RequestMapping("/teacher")
public class TeacherController {

    @Resource
    private ITeacherService teacherService;

    @RequestMapping(value = "/ajax-get-all-teacher")
    @ResponseBody
    public Object getAllTeacher(){
        try {
            List<TeacherModel> teacherModelList = teacherService.listTeacherAll();
            return ResponseUtil.constructResponse(ResponseUtil.RETURN_STATUS_SUCCESS, "获取所有教师成功", teacherModelList);
        }catch (Exception e){
            e.printStackTrace();
            return ResponseUtil.constructResponse(ResponseUtil.RETURN_STATUS_FAILED,"获取所有教师失败",null);
        }
    }
}
