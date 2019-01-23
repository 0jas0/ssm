package com.jas.web.controller;

import com.jas.web.bean.model.AdministrativeClassModel;
import com.jas.web.bean.model.CollegeMajorModel;
import com.jas.web.exception.ParamNotValidException;
import com.jas.web.service.IAdministrativeClassService;
import com.jas.web.service.ICollegeMajorService;
import com.jas.web.utils.PaperUtil;
import com.jas.web.utils.ResponseUtil;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;

@Controller
@RequestMapping("/class")
public class AdministrativeClassController {

    @Resource
    private IAdministrativeClassService administrativeClassService;

    @Resource
    private ICollegeMajorService collegeMajorService;

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

    @RequestMapping(value = "/ajax-get-class-by-page")
    @ResponseBody
    public Object getAllTeacher(@RequestParam(value = "currentPage", defaultValue = "1") Integer currentPage, @RequestParam(value = "pageSize",defaultValue = "10") Integer pageSize){
        try {
            PaperUtil<AdministrativeClassModel> administrativeClassModels = administrativeClassService.getClassByPage(currentPage, pageSize);
            return ResponseUtil.constructResponse(ResponseUtil.RETURN_STATUS_SUCCESS, "获取班级成功", administrativeClassModels);
        }catch (Exception e){
            e.printStackTrace();
            return ResponseUtil.constructResponse(ResponseUtil.RETURN_STATUS_FAILED,"获取班级列表失败",null);
        }
    }

    @RequestMapping(value = "/ajax-delete-class")
    @ResponseBody
    public Object deleteClass(@RequestParam(value = "id",required = true) Integer id){
        try {
            //数据的校验
            administrativeClassService.deleteClass(id);
            return ResponseUtil.constructResponse(ResponseUtil.RETURN_STATUS_SUCCESS, "删除班级成功", "");
        }catch (Exception e){
            e.printStackTrace();
            return ResponseUtil.constructResponse(ResponseUtil.RETURN_STATUS_FAILED,"删除班级失败","");
        }
    }

    @RequestMapping(value = "/ajax-get-class-by-collegeMajor")
    @ResponseBody
    public Object getClassByCollegeMajor(@RequestParam("collegeId") Integer college, @RequestParam("majorId") Integer major){
        try {
            List<AdministrativeClassModel> administrativeClassModels = administrativeClassService.getByCollegeMajor(college, major);
            return ResponseUtil.constructResponse(ResponseUtil.RETURN_STATUS_SUCCESS, "获取班级信息成功", administrativeClassModels);
        }catch (Exception e){
            e.printStackTrace();
            return ResponseUtil.constructResponse(ResponseUtil.RETURN_STATUS_FAILED,"获取班级信息失败",null);
        }
    }
    @RequestMapping(value = "ajax/get-class-by-collegeId")
    @ResponseBody
    public Object getClassByCollege(@RequestParam("collegeId") Integer college){
        try {
            List<AdministrativeClassModel> administrativeClassModels = administrativeClassService.getByCollege(college);
            return ResponseUtil.constructResponse(ResponseUtil.RETURN_STATUS_SUCCESS, "获取班级信息成功", administrativeClassModels);
        }catch (Exception e){
            e.printStackTrace();
            return ResponseUtil.constructResponse(ResponseUtil.RETURN_STATUS_FAILED,"获取班级信息失败",null);
        }
    }

    @RequestMapping("/class_list")
    public String classListView(){
        return "class/class_list";
    }
    @RequestMapping("/class_add")
    public String studentAddView(){
        return "class/class_add";
    }
    @RequestMapping("/class_edit")
    public String studentEditView(@RequestParam("id") Integer id, Model model){
        AdministrativeClassModel administrativeClassModel = administrativeClassService.getById(id);
        List<CollegeMajorModel> majorByParentId = collegeMajorService.getMajorByParentId(administrativeClassModel.getCollege());
        List<CollegeMajorModel> college = collegeMajorService.getMajorByParentId(0);
        model.addAttribute("collegeList",college);
        model.addAttribute("majorList",majorByParentId);
        model.addAttribute("classDO",administrativeClassModel);
        return "class/class_edit";
    }
}
