package com.jas.web.controller;

import com.jas.web.bean.model.UsernameModel;
import com.jas.web.service.IAdminService;
import com.jas.web.service.IUsernameService;
import com.jas.web.utils.StringUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

@Controller
public class FirstController {

    @Resource
    IAdminService adminService;
    @Resource
    IUsernameService usernameService;

    @RequestMapping(value={"/","/first","/login"})
    public String first()throws Exception{
        return "login";
    }

    @RequestMapping("/home")
    public String home(HttpSession session)throws Exception{
        return "home";
    }

    @RequestMapping("/ajaxLogin")
    @ResponseBody
    public Object ajaxLogin(@RequestParam String username, @RequestParam String password, @RequestParam int type){
        Map<String,String> map = new HashMap<>();
       /* String pass = null;
        if (type == 2){
            //admin
            pass = adminService.getPasswordByAdmin(username);
        }else {
            //stu or teacher
            UsernameModel usernameModel = usernameService.getBySerialNumber(username, type);
            pass = usernameModel.getPassword();
        }
        if (StringUtil.isEmpty(pass)){
            //用户不存在
            map.put("msg", "account_error");
        }else if (!password.equals(pass)){
            //密码错误
            map.put("msg", "password_error");
        }*/
        return map;
    }
}
