package com.jas.web.controller;

import com.alibaba.fastjson.JSONObject;
import com.jas.web.bean.model.TeacherModel;
import com.jas.web.bean.param.TeacherParam;
import com.jas.web.service.ITeacherService;
import com.jas.web.utils.ResponseUtil;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.validation.Valid;
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

    @RequestMapping(value = "/ajax-add-teacher")
    @ResponseBody
    public Object addTeacher(@Valid TeacherParam teacherParam, BindingResult bindingResult){
        //数据的校验
        if (bindingResult.hasErrors()){
            FieldError fieldError = bindingResult.getFieldError();
            String errorMessage = fieldError.getDefaultMessage();
            return ResponseUtil.constructResponse(ResponseUtil.RETURN_STATUS_FAILED, errorMessage, null);
        }
        teacherService.addTeacher(teacherParam);
        return ResponseUtil.constructResponse(ResponseUtil.RETURN_STATUS_SUCCESS, "添加教师成功", null);
    }

    @RequestMapping(value = "/ajax-modify-teacher")
    @ResponseBody
    public Object modifyTeacher(@Valid TeacherParam teacherParam, BindingResult bindingResult){
        //数据的校验
        if (bindingResult.hasErrors()){
            FieldError fieldError = bindingResult.getFieldError();
            String errorMessage = fieldError.getDefaultMessage();
            return ResponseUtil.constructResponse(ResponseUtil.RETURN_STATUS_FAILED, errorMessage, null);
        }
        teacherService.modifyTeacher(teacherParam);
        return ResponseUtil.constructResponse(ResponseUtil.RETURN_STATUS_SUCCESS, "修改教师成功", null);
    }

    @RequestMapping(value = "/ajax-get-teacher")
    @ResponseBody
    public Object getByTeacherId(@RequestParam(value = "teacherId",required = true) String teacherId){
        TeacherModel teacherModel = teacherService.getByTeacherId(teacherId);
        return ResponseUtil.constructResponse(ResponseUtil.RETURN_STATUS_SUCCESS, "获取教师信息成功", teacherModel);
    }
}
