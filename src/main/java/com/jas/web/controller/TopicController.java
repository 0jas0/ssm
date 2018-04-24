package com.jas.web.controller;

import com.jas.web.bean.param.TopicParam;
import com.jas.web.bean.param.UsernameParam;
import com.jas.web.enums.ERole;
import com.jas.web.exception.ParamNotValidException;
import com.jas.web.service.ITopicService;
import com.jas.web.service.IUsernameService;
import com.jas.web.utils.EUDataGridResult;
import com.jas.web.utils.ResponseUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

@Controller
@RequestMapping("/topic")
public class TopicController {

    @Resource
    private ITopicService topicService;

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

    @RequestMapping(value = "/getData")
    @ResponseBody
    public Object getData(Integer page, Integer rows){
        EUDataGridResult result = topicService.getList(page, rows);
        return result;
    }

    @RequestMapping(value = "/addTopic")
    @ResponseBody
    public Object addUserName(TopicParam topicParam){
        try {
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
}
