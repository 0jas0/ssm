package com.jas.web.controller;

import com.jas.web.model.UserModel;
import com.jas.web.param.UserParam;
import com.jas.web.service.IUserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.LinkedList;
import java.util.List;

@Controller
@RequestMapping("/user")
public class UserController {
    @Resource
    IUserService userService;

    @RequestMapping("ajax/list_user")
    @ResponseBody
    public Object listUsers(){
        //List<UserModel> userModelList = userService.listUsers(new UserParam());
        List<UserModel> userModelList = new LinkedList<UserModel>();
        userModelList.add(new UserModel(1,"aaaa"));
        userModelList.add(new UserModel(2,"bbbbb"));
        userModelList.add(new UserModel(3,"ccccc"));
        return userModelList;
    }
}
