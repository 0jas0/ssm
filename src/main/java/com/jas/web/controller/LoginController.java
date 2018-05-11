package com.jas.web.controller;

import com.jas.web.bean.AdminDO;
import com.jas.web.exception.ParamNotValidException;
import com.jas.web.model.StudentModel;
import com.jas.web.service.IAdminService;
import com.jas.web.service.IStudentService;
import com.jas.web.utils.Md5Util;
import com.jas.web.utils.ResponseUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/login")
public class LoginController {
    @Resource
    IStudentService studentService;

    @Resource
    IAdminService adminService;

    @RequestMapping("ajax/do-login")
    @ResponseBody
    public Object login(@RequestParam("username") String username, @RequestParam("password") String password, @RequestParam("type") int type, HttpSession session){
        try {
            boolean flag = true;
            if (type == 0){
                //管理员登陆
                AdminDO adminDO = adminService.getByName(username);
                if (adminDO == null || !adminDO.getPassword().equals(Md5Util.md5Password(password))){
                    flag = false;
                }
            }else{
                //学生登陆
                StudentModel student = studentService.getStudentByStudentId(username);
                if (student == null || !student.getPassword().equals(Md5Util.md5Password(password))){
                    flag = false;
                }
            }
            if (flag){
                //登陆成功后保存登陆的用户名和角色
                session.setAttribute("username", username);
                session.setAttribute("type", type);
                return ResponseUtil.constructResponse(ResponseUtil.RETURN_STATUS_SUCCESS,"登陆成功" , null);
            }else {
                return ResponseUtil.constructResponse(ResponseUtil.RETURN_STATUS_FAILED,"用户名或密码错误" , null);
            }
        }catch (Exception e){
            e.printStackTrace();
            return ResponseUtil.constructResponse(ResponseUtil.RETURN_STATUS_FAILED,"系统异常请稍后重试" , null);
        }
    }

}
