package com.jas.web.controller;

import com.jas.web.bean.domain.AdminDO;
import com.jas.web.bean.model.*;
import com.jas.web.dao.IAdminDAO;
import com.jas.web.service.*;
import com.jas.web.utils.DateUtil;
import com.jas.web.utils.ResponseUtil;
import com.jas.web.utils.StringUtil;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import javax.xml.crypto.Data;
import java.util.Date;
import java.util.List;

@Controller
public class IndexController {

    @Resource
    IAdminDAO adminDAO;

    @Resource
    IStudentService studentService;

    @Resource
    ITeacherService teacherService;

    @Resource
    ISystemSettingService systemSettingService;

    @Resource
    ICollegeMajorService collegeMajorService;

    @Resource
    IAdministrativeClassService administrativeClassService;

    @RequestMapping("/do-login")
    @ResponseBody
    public Object doLogin(@RequestParam("username") String username, @RequestParam("password") String password,
                          @RequestParam("type") Integer type, HttpSession session){
        boolean flag = false;
        password = StringUtil.md5Password(password);
        Integer id = null;
        if (type == 0){
            //登陆的是管理员
            AdminDO adminDO = adminDAO.getByName(username);
            if (adminDO != null && adminDO.getPassword().equals(password)){
                id = adminDO.getId();
                flag = true;
            }
        }else if (type == 1){
            //登陆的是学生
            StudentModel studentModel = studentService.getStudentByStudentId(username);
            if (studentModel != null && studentModel.getPassword().equals(password)){
                id = studentModel.getId();
                flag = true;
            }
        }else if (type == 2){
            //登陆的是老师
            TeacherModel teacherModel = teacherService.getByTeacherId(username);
            if (teacherModel != null && teacherModel.getId() != null && teacherModel.getPassword().equals(password)){
                id = teacherModel.getId();
                flag = true;
            }
        }
        if (flag){
            SystemSettingModel systemSettingModel = systemSettingService.getSystemSettingModel();
            String nowData = DateUtil.getNowTimeByFormat("yyyy-MM-dd");
            long nowSeconds = DateUtil.getMillisFromStringByFormat(nowData, "yyyy-MM-dd") / 1000;
            if (Double.compare(nowSeconds, systemSettingModel.getChoiceStartTime()) >= 0 &&
                    Double.compare(nowSeconds, systemSettingModel.getChoiceEndTime()) <= 0){
                systemSettingModel.setCanChoiceCourse(true);
            }
            //登陆成功
            UserModel userModel = new UserModel();
            userModel.setUsername(username);
            userModel.setType(type);
            userModel.setId(id);
            session.setAttribute("userModel", userModel);
            session.setAttribute("sysModel", systemSettingModel);
            return ResponseUtil.constructResponse(ResponseUtil.RETURN_STATUS_SUCCESS, "登陆成功", null);
        }else {
            //登陆失败
            return ResponseUtil.constructResponse(ResponseUtil.RETURN_STATUS_FAILED, "用户名或密码错误", null);
        }
    }

    @RequestMapping("/loginOut")
    @ResponseBody
    public Object loginOut(HttpSession session){
        session.removeAttribute("userModel");
        return ResponseUtil.constructResponse(ResponseUtil.RETURN_STATUS_SUCCESS, "退出登陆成功", null);
    }

    @RequestMapping("/person/info")
    public String personInfo(Model model, HttpSession session){
        UserModel userModel = (UserModel)session.getAttribute("userModel");
        if (userModel.getType() == 1){
            // 学生
            StudentModel studentModel = studentService.getStudentById(userModel.getId());
            List<CollegeMajorModel> majorByParentId = collegeMajorService.getMajorByParentId(studentModel.getCollege());
            List<AdministrativeClassModel> classDO = administrativeClassService.getByCollegeMajor(studentModel.getCollege(), studentModel.getMajor());
            List<CollegeMajorModel> college = collegeMajorService.getMajorByParentId(0);
            model.addAttribute("collegeList",college);
            model.addAttribute("majorList",majorByParentId);
            model.addAttribute("classList",classDO);
            model.addAttribute("studentModel",studentModel);
            return "person/student_edit";
        }else {
            TeacherModel teacherModel = teacherService.getById(userModel.getId());
            model.addAttribute("teacherModel",teacherModel);
            return "person/teacher_edit";
        }
    }

    @RequestMapping("/login")
    public String login(){
        return "login";
    }
    @RequestMapping("/")
    public String main(){
        return "main";
    }
    @RequestMapping("/top")
    public String top(){
        return "top";
    }
    @RequestMapping("/left")
    public String left(){
        return "left";
    }
    @RequestMapping("/right")
    public String right(){
        return "right";
    }
    @RequestMapping("/index")
    public String index(){
        return "index";
    }
    @RequestMapping("/error")
    public String error(){
        return "error";
    }
}
