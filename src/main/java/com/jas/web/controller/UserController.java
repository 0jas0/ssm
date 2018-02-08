package com.jas.web.controller;

import com.jas.web.model.UserModel;
import com.jas.web.param.UserParam;
import com.jas.web.service.IUserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;

@Controller
@RequestMapping("/user")
public class UserController {
    @Resource
    IUserService userService;

    @RequestMapping("ajax/list_user")
    @ResponseBody
    public Object listUsers(){
        List<UserModel> userModelList = userService.listUsers(new UserParam());
        return userModelList;
    }
}
