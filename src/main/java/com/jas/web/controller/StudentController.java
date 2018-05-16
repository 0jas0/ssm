package com.jas.web.controller;

import com.jas.web.bean.model.AdministrativeClassModel;
import com.jas.web.bean.model.CollegeMajorModel;
import com.jas.web.bean.model.StudentModel;
import com.jas.web.exception.ParamNotValidException;
import com.jas.web.service.IAdministrativeClassService;
import com.jas.web.service.ICollegeMajorService;
import com.jas.web.service.IStudentService;
import com.jas.web.utils.PaperUtil;
import com.jas.web.utils.ResponseUtil;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.util.List;

@Controller
@RequestMapping("/student")
public class StudentController {

    @Resource
    private IStudentService studentService;

    @Resource
    private ICollegeMajorService collegeMajorService;

    @Resource
    private IAdministrativeClassService administrativeClassService;

    @RequestMapping(value = "/ajax-add-student")
    @ResponseBody
    public Object addStudent(StudentModel studentModel){
        try {
            //数据的校验
            studentService.addStudent(studentModel);
            return ResponseUtil.constructResponse(ResponseUtil.RETURN_STATUS_SUCCESS, "添加学生成功", null);
        }catch (ParamNotValidException e){
            return ResponseUtil.constructResponse(ResponseUtil.RETURN_STATUS_FAILED,e.getMessage(),null);
        }catch (Exception e){
            e.printStackTrace();
            return ResponseUtil.constructResponse(ResponseUtil.RETURN_STATUS_FAILED,"添加学生失败",null);
        }
    }

    @RequestMapping(value = "/ajax-delete-student")
    @ResponseBody
    public Object addStudent(@RequestParam(value = "studentId",required = true) String studentId){
        try {
            //数据的校验
            studentService.deleteStudent(studentId);
            return ResponseUtil.constructResponse(ResponseUtil.RETURN_STATUS_SUCCESS, "删除学生成功", null);
        }catch (Exception e){
            e.printStackTrace();
            return ResponseUtil.constructResponse(ResponseUtil.RETURN_STATUS_FAILED,"删除学生失败",null);
        }
    }

    @RequestMapping(value = "/ajax-upload-file")
    @ResponseBody
    public Object uploadFile(@RequestParam("image") MultipartFile image){
        try {
            String fullPath =  studentService.uploadFile(image);
            return ResponseUtil.constructResponse(ResponseUtil.RETURN_STATUS_FAILED,"上图图片成功",fullPath);
        }catch (Exception e){
            e.printStackTrace();
            return ResponseUtil.constructResponse(ResponseUtil.RETURN_STATUS_FAILED,"上图图片失败",null);
        }
    }

    @RequestMapping(value = "/ajax-reupload-file")
    @ResponseBody
    public Object reUploadFile(@RequestParam("image") MultipartFile image,@RequestParam("student") String student){
        try {
            String fullPath =  studentService.reUploadFile(image,student);
            return ResponseUtil.constructResponse(ResponseUtil.RETURN_STATUS_FAILED,"上图图片成功",fullPath);
        }catch (Exception e){
            e.printStackTrace();
            return ResponseUtil.constructResponse(ResponseUtil.RETURN_STATUS_FAILED,"上图图片失败",null);
        }
    }

    @RequestMapping(value = "/ajax-modify-student")
    @ResponseBody
    public Object modifyStudent(StudentModel studentModel){
        try {
            //数据的校验
            studentService.modifyStudent(studentModel);
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

    @RequestMapping(value = "/ajax-get-student-by-page")
    @ResponseBody
    public Object getAllTeacher(@RequestParam(value = "currentPage", defaultValue = "1") Integer currentPage, @RequestParam(value = "pageSize",defaultValue = "10") Integer pageSize){
        try {
            PaperUtil<StudentModel> studentPape = studentService.getStudentByPage(currentPage, pageSize);
            return ResponseUtil.constructResponse(ResponseUtil.RETURN_STATUS_SUCCESS, "获取学生成功", studentPape);
        }catch (Exception e){
            e.printStackTrace();
            return ResponseUtil.constructResponse(ResponseUtil.RETURN_STATUS_FAILED,"获取学生列表失败",null);
        }
    }

    @RequestMapping("/student_list")
    public String studentListView(){
        return "student/student_list";
    }
    @RequestMapping("/student_add")
    public String studentAddView(){
        return "student/student_add";
    }

    @RequestMapping("/student_edit")
    public String studentEditView(@RequestParam("studentId") String studentId, Model model){
        StudentModel studentModel = studentService.getStudentByStudentId(studentId);
        List<CollegeMajorModel> majorByParentId = collegeMajorService.getMajorByParentId(studentModel.getCollege());
        List<AdministrativeClassModel> classDO = administrativeClassService.getByCollegeMajor(studentModel.getCollege(), studentModel.getMajor());
        List<CollegeMajorModel> college = collegeMajorService.getMajorByParentId(0);
        model.addAttribute("collegeList",college);
        model.addAttribute("majorList",majorByParentId);
        model.addAttribute("classList",classDO);
        model.addAttribute("studentModel",studentModel);
        return "student/student_edit";
    }

}
