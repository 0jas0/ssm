package com.jas.web.controller;

import com.jas.web.bean.model.ElectiveCourseModle;
import com.jas.web.bean.model.TopicModel;
import com.jas.web.bean.model.UsernameModel;
import com.jas.web.bean.param.TopicParam;
import com.jas.web.bean.param.UsernameParam;
import com.jas.web.enums.ERole;
import com.jas.web.exception.ParamNotValidException;
import com.jas.web.service.ITopicService;
import com.jas.web.service.IUsernameService;
import com.jas.web.utils.EUDataGridResult;
import com.jas.web.utils.ResponseUtil;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.jms.Topic;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("/topic")
public class TopicController {

    @Resource
    private ITopicService topicService;

    @Resource
    private IUsernameService usernameService;

    @RequestMapping(value = "/addView")
    public String add(){
        return "topic_add";
    }

    @RequestMapping(value = "/editView")
    public String edit(){
        return "topic_edit";
    }


    @RequestMapping(value = "/list")
    public String list(){
        return "topic_list";
    }

    @RequestMapping(value = "/electiveList")
    public String electivelist(){
        return "elective_list";
    }

    @RequestMapping(value = "/selfTopic")
    public String selfTopic(){
        return "self_topic";
    }

    @RequestMapping(value = "/getData")
    @ResponseBody
    public Object getData(Integer page, Integer rows){
        EUDataGridResult result = topicService.getList(page, rows);
        return result;
    }

    @RequestMapping(value = "/addTopic")
    @ResponseBody
    public Object addUserName(TopicParam topicParam, HttpServletRequest request){
        try {
            HttpSession session = request.getSession();
            String userId = (String)session.getAttribute("userId");
            String[] split = userId.split(",");
            if (split[1].equals("1")){
                topicParam.setUserId(Integer.valueOf(split[0]));
            }
            topicService.insert(topicParam.getTopicModel());
            return ResponseUtil.constructResponse(ResponseUtil.RETURN_STATUS_SUCCESS, "添加数据成功",null);
        }catch (ParamNotValidException e){
            return ResponseUtil.constructResponse(ResponseUtil.RETURN_STATUS_FAILED,e.getMessage(),null);
        }catch (Exception e){
            return ResponseUtil.constructResponse(ResponseUtil.RETURN_STATUS_FAILED,"添加数据失败",null);
        }
    }

    @RequestMapping(value = "/editTopic")
    @ResponseBody
    public Object editUserName(TopicParam topicParam){
        try {
            topicService.update(topicParam.getTopicModel());
            return ResponseUtil.constructResponse(ResponseUtil.RETURN_STATUS_SUCCESS, "添加数据成功",null);
        }catch (Exception e){
            return ResponseUtil.constructResponse(ResponseUtil.RETURN_STATUS_FAILED,"添加数据失败",null);
        }
    }

    @RequestMapping(value = "/deleteTopic")
    @ResponseBody
    public Object deleteUserName(@RequestParam("ids") String ids){
        try {
            topicService.delete(ids);
            return ResponseUtil.constructResponse(ResponseUtil.RETURN_STATUS_SUCCESS, "添加数据成功",null);
        }catch (Exception e){
            return ResponseUtil.constructResponse(ResponseUtil.RETURN_STATUS_FAILED,"添加数据失败",null);
        }
    }

    @RequestMapping(value = "/getByMajor")
    @ResponseBody
    public Object getByMajor(HttpServletRequest request){
        HttpSession session = request.getSession();
        String userId = (String)session.getAttribute("userId");
        String[] split = userId.split(",");
        if (split[1].equals("0")){
            EUDataGridResult result = new EUDataGridResult();
            UsernameModel usernameModel = usernameService.getById(Integer.valueOf(split[0]));
            String major = usernameModel.getMajor();
            List<ElectiveCourseModle> electiveCourseModleList = topicService.getByMajor(major);
            result.setRows(electiveCourseModleList);
            result.setTotal(electiveCourseModleList.size());
            return result;
        }
        return null;
    }
    @RequestMapping(value = "/selectCourse")
    @ResponseBody
    public Object selectCourse(String topicId,HttpServletRequest request){
        HttpSession session = request.getSession();
        String userId = (String)session.getAttribute("userId");
        String[] split = userId.split(",");
        topicService.selectCourse(split[0], topicId);
        return ResponseUtil.constructResponse(ResponseUtil.RETURN_STATUS_SUCCESS, "添加数据成功",null);
    }

    @RequestMapping(value = "/getSelfTopic")
    @ResponseBody
    public Object getSuccessTopic(HttpServletRequest request){
        HttpSession session = request.getSession();
        String userId = (String)session.getAttribute("userId");
        String[] split = userId.split(",");
        List<TopicModel> topicModels = topicService.getByTeacher(split[0]);
        EUDataGridResult result = new EUDataGridResult();
        result.setRows(topicModels);
        result.setTotal(topicModels.size());
        return result;
    }

    @RequestMapping(value = "getStudentByTopicId")
    @ResponseBody
    public Object getStudentByTopicId(@Param("topicId") String topicId){
       List<UsernameModel> usernameModels =  topicService.getStudentByTopicId(topicId);
        return ResponseUtil.constructResponse(ResponseUtil.RETURN_STATUS_SUCCESS, "添加数据成功",usernameModels);
    }
}
