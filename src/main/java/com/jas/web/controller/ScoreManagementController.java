package com.jas.web.controller;

import com.jas.web.bean.model.ScoreManagementModel;
import com.jas.web.service.IScoreManagementService;
import com.jas.web.utils.EUDataGridResult;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/scoreManagement")
public class ScoreManagementController {

    @Resource
    private IScoreManagementService iScoreManagementService;

    //学生查看 自己的成绩
    @RequestMapping(value = "/getByStudent")
    @ResponseBody
    public ScoreManagementModel getByStudent(HttpServletRequest request){
        return iScoreManagementService.getByStudent(request);
    }

    //老师
    @RequestMapping(value = "/getByTeacher")
    @ResponseBody
    public EUDataGridResult getByTeacher(HttpServletRequest request, Integer page, Integer rows){
        return iScoreManagementService.getByTeacher(request, page, rows);
    }
}
