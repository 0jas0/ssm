package com.jas.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IndexController {
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
