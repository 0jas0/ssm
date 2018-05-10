package com.jas.web.controller;

import com.jas.web.exception.ParamNotValidException;
import com.jas.web.model.PaperModel;
import com.jas.web.model.StudentModel;
import com.jas.web.service.IStudentService;
import com.jas.web.utils.ResponseUtil;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;

@Controller
@RequestMapping("/student")
public class StudentController {
    @Resource
    IStudentService studentService;

    @RequestMapping("ajax/add-student")
    @ResponseBody
    public Object ajaxAddStudent(StudentModel studentModel){
        try {
            studentService.addStudent(studentModel);
            return ResponseUtil.constructResponse(ResponseUtil.RETURN_STATUS_SUCCESS,"注册学生信息成功" , null);
        }catch (ParamNotValidException e){
            return ResponseUtil.constructResponse(ResponseUtil.RETURN_STATUS_FAILED,e.getMessage() , null);
        }catch (Exception e){
            return ResponseUtil.constructResponse(ResponseUtil.RETURN_STATUS_FAILED,"系统异常请稍后重试" , null);
        }
    }

    @RequestMapping("ajax/evaluate-student")
    @ResponseBody
    public Object evaluateStudent(@RequestParam("studentId") String StudentId, @RequestParam("evaluate") Integer evaluate){
        try {
            studentService.evaluateStudent(StudentId, evaluate);
            return ResponseUtil.constructResponse(ResponseUtil.RETURN_STATUS_SUCCESS,"评价学生成功" , null);
        }catch (Exception e){
            return ResponseUtil.constructResponse(ResponseUtil.RETURN_STATUS_FAILED,"系统异常请稍后重试" , null);
        }
    }

    @RequestMapping("ajax/update-student")
    @ResponseBody
    public Object ajaxupdateStudent(StudentModel studentModel){
        try {
            studentService.updateStudent(studentModel);
            return ResponseUtil.constructResponse(ResponseUtil.RETURN_STATUS_SUCCESS,"修改学生信息成功" , null);
        }catch (Exception e){
            return ResponseUtil.constructResponse(ResponseUtil.RETURN_STATUS_FAILED,"系统异常请稍后重试" , null);
        }
    }


    @RequestMapping("ajax/get-student-by-studentId")
    @ResponseBody
    public Object getStudentByStudentid(@RequestParam("studentId") String studentId){
        try {
            StudentModel studentModel = studentService.getStudentByStudentId(studentId);
            return ResponseUtil.constructResponse(ResponseUtil.RETURN_STATUS_SUCCESS,"获取学生信息成功" , studentModel);
        }catch (Exception e){
            return ResponseUtil.constructResponse(ResponseUtil.RETURN_STATUS_FAILED,"系统异常请稍后重试" , null);
        }
    }

    @RequestMapping("ajax/get-student-by-page")
    @ResponseBody
    public Object getStudentByStudentid(@RequestParam(value = "currentPage", defaultValue = "1") Integer currentPage, @RequestParam(value = "pageSize",defaultValue = "10") Integer pageSize){
        try {
            PaperModel<StudentModel> page = studentService.getStudentByPage(currentPage, pageSize);
            return ResponseUtil.constructResponse(ResponseUtil.RETURN_STATUS_SUCCESS,"分页获取学生信息成功" , page);
        }catch (Exception e){
            return ResponseUtil.constructResponse(ResponseUtil.RETURN_STATUS_FAILED,"系统异常请稍后重试" , null);
        }
    }


}
