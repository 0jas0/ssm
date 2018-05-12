package com.jas.web.controller;

import com.jas.web.bean.model.StudentModel;
import com.jas.web.bean.model.TeacherModel;
import com.jas.web.exception.ParamNotValidException;
import com.jas.web.service.IStudentService;
import com.jas.web.service.ITeacherService;
import com.jas.web.utils.PaperUtil;
import com.jas.web.utils.ResponseUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.mail.Multipart;

@Controller
@RequestMapping("/student")
public class StudentController {

    @Resource
    private IStudentService studentService;

    @RequestMapping(value = "/ajax-add-student")
    @ResponseBody
    public Object addStudent(@RequestParam("image")MultipartFile image, StudentModel studentModel){
        try {
            //数据的校验
            studentService.addStudent(image, studentModel);
            return ResponseUtil.constructResponse(ResponseUtil.RETURN_STATUS_SUCCESS, "添加学生成功", null);
        }catch (ParamNotValidException e){
            return ResponseUtil.constructResponse(ResponseUtil.RETURN_STATUS_FAILED,e.getMessage(),null);
        }catch (Exception e){
            e.printStackTrace();
            return ResponseUtil.constructResponse(ResponseUtil.RETURN_STATUS_FAILED,"添加学生失败",null);
        }
    }

    @RequestMapping(value = "/ajax-modify-student")
    @ResponseBody
    public Object modifyStudent(@RequestParam(value = "image",required = false)MultipartFile image,StudentModel studentModel){
        try {
            //数据的校验
            studentService.modifyStudent(image, studentModel);
            return ResponseUtil.constructResponse(ResponseUtil.RETURN_STATUS_SUCCESS, "修改学生信息成功", null);
        }catch (Exception e){
            e.printStackTrace();
            return ResponseUtil.constructResponse(ResponseUtil.RETURN_STATUS_FAILED,"添加学生信息失败",null);
        }
    }

    @RequestMapping(value = "/ajax-get-student")
    @ResponseBody
    public Object getByStudentId(@RequestParam(value = "studentId",required = true) String studentId){
        try {
            StudentModel studentModel = studentService.getStudentByStudentId(studentId);
            return ResponseUtil.constructResponse(ResponseUtil.RETURN_STATUS_SUCCESS, "获取学生信息成功", studentModel);
        }catch (Exception e){
            e.printStackTrace();
            return ResponseUtil.constructResponse(ResponseUtil.RETURN_STATUS_FAILED,"获取学生信息失败",null);
        }
    }
}
