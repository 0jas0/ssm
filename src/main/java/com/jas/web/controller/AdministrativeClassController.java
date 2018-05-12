package com.jas.web.controller;

import com.jas.web.bean.model.AdministrativeClassModel;
import com.jas.web.bean.model.StudentModel;
import com.jas.web.exception.ParamNotValidException;
import com.jas.web.service.IAdministrativeClassService;
import com.jas.web.service.IStudentService;
import com.jas.web.utils.ResponseUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;

@Controller
@RequestMapping("/class")
public class AdministrativeClassController {

    @Resource
    private IAdministrativeClassService administrativeClassService;

    @RequestMapping(value = "/ajax-add-class")
    @ResponseBody
    public Object addClass(AdministrativeClassModel administrativeClassModel){
        try {
            //数据的校验
            administrativeClassService.addAdministrativeClass(administrativeClassModel);
            return ResponseUtil.constructResponse(ResponseUtil.RETURN_STATUS_SUCCESS, "添加班级成功", null);
        }catch (ParamNotValidException e){
            return ResponseUtil.constructResponse(ResponseUtil.RETURN_STATUS_FAILED,e.getMessage(),null);
        }catch (Exception e){
            e.printStackTrace();
            return ResponseUtil.constructResponse(ResponseUtil.RETURN_STATUS_FAILED,"添加班级失败",null);
        }
    }

    @RequestMapping(value = "/ajax-modify-class")
    @ResponseBody
    public Object modifyStudent(AdministrativeClassModel administrativeClassModel){
        try {
            //数据的校验
            administrativeClassService.modifyAdministrativeClass(administrativeClassModel);
            return ResponseUtil.constructResponse(ResponseUtil.RETURN_STATUS_SUCCESS, "修改班级信息成功", null);
        }catch (ParamNotValidException e){
            return ResponseUtil.constructResponse(ResponseUtil.RETURN_STATUS_FAILED,e.getMessage(),null);
        }catch (Exception e){
            e.printStackTrace();
            return ResponseUtil.constructResponse(ResponseUtil.RETURN_STATUS_FAILED,"添加班级信息失败",null);
        }
    }

    @RequestMapping(value = "/ajax-get-class-by-classId")
    @ResponseBody
    public Object getByStudentId(@RequestParam(value = "classId",required = true) String classId){
        try {
            AdministrativeClassModel administrativeClassModel = administrativeClassService.getByClassId(classId);
            return ResponseUtil.constructResponse(ResponseUtil.RETURN_STATUS_SUCCESS, "获取班级信息成功", administrativeClassModel);
        }catch (Exception e){
            e.printStackTrace();
            return ResponseUtil.constructResponse(ResponseUtil.RETURN_STATUS_FAILED,"获取班级信息失败",null);
        }
    }
}
