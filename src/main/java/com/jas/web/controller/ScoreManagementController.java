package com.jas.web.controller;

import com.jas.web.bean.model.ScoreManagementModel;
import com.jas.web.service.IScoreManagementService;
import com.jas.web.utils.EUDataGridResult;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/scoreManagement")
public class ScoreManagementController {

    @RequestMapping(value = "/student_score")
    public String student(){
        return "student_scoreManage";
    }

    @RequestMapping(value = "/teacher_score")
    public String teacher(){
        return "teacher_scoreManage";
    }

    @RequestMapping(value = "/admin_score")
    public String admin(){
        return "admin_scoreManage";
    }

    @Resource
    private IScoreManagementService iScoreManagementService;

    //学生查看 自己的成绩
    @RequestMapping(value = "/getByStudent")
    @ResponseBody
    public EUDataGridResult getByStudent(HttpServletRequest request){
        EUDataGridResult result = new EUDataGridResult();
        List<ScoreManagementModel> list = new ArrayList<>();
        ScoreManagementModel scoreManagementModel = iScoreManagementService.getByStudent(request);
        list.add(scoreManagementModel);
        result.setRows(list);
        result.setTotal(list.size());
        return result;
    }

    //老师
    @RequestMapping(value = "/getByTeacher")
    @ResponseBody
    public EUDataGridResult getByTeacher(HttpServletRequest request, Integer page, Integer rows){
        return iScoreManagementService.getByTeacher(request, page, rows);
    }

    //管理员
    @RequestMapping(value = "/getStudentScore")
    @ResponseBody
    public EUDataGridResult getStudentScore(HttpServletRequest request, Integer page, Integer rows, String name, String serialNumber, String major) {
        return iScoreManagementService.getStudentScore(request, page, rows, name, serialNumber, major);
    }

    }
