package com.jas.web.controller;

import com.alibaba.fastjson.JSONObject;
import com.jas.web.bean.model.UsernameModel;
import com.jas.web.bean.param.UsernameParam;
import com.jas.web.enums.ERole;
import com.jas.web.exception.ParamNotValidException;
import com.jas.web.service.IUsernameService;
import com.jas.web.utils.EUDataGridResult;
import com.jas.web.utils.ResponseUtil;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;

@Controller
@RequestMapping("/teacher")
public class TeacherController {

    @Resource
    private IUsernameService usernameService;

    @RequestMapping(value = "/addView")
    public String add(){
        return "teacher_add";
    }

    @RequestMapping(value = "/editView")
    public String edit(){
        return "teacher_edit";
    }


    @RequestMapping(value = "/list")
    public String list(){
        return "teacher_list";
    }

    @RequestMapping(value = "/getData")
    @ResponseBody
    public Object getData(Integer page, Integer rows){
        EUDataGridResult result = usernameService.getTeacherList(page, rows);
        return result;
    }

    @RequestMapping(value = "/addUserName")
    @ResponseBody
    public Object addUserName(UsernameParam usernameParam){
        try {
            usernameParam.setStatus(ERole.TEACHER.getValue());
            int id = usernameService.insert(usernameParam.getUsernameModel());
            if (id > 0){
                return ResponseUtil.constructResponse(ResponseUtil.RETURN_STATUS_SUCCESS, "添加数据成功",id);
            }else {
                return ResponseUtil.constructResponse(ResponseUtil.RETURN_STATUS_FAILED,"添加数据失败",null);
            }
        }catch (ParamNotValidException e){
            return ResponseUtil.constructResponse(ResponseUtil.RETURN_STATUS_FAILED,e.getMessage(),null);
        }catch (Exception e){
            return ResponseUtil.constructResponse(ResponseUtil.RETURN_STATUS_FAILED,"添加数据失败",null);
        }
    }

    @RequestMapping(value = "/editUserName")
    @ResponseBody
    public Object editUserName(UsernameParam usernameParam){
        try {
            usernameParam.setStatus(ERole.TEACHER.getValue());
            usernameService.update(usernameParam);
            return ResponseUtil.constructResponse(ResponseUtil.RETURN_STATUS_SUCCESS, "添加数据成功",null);
        }catch (Exception e){
            return ResponseUtil.constructResponse(ResponseUtil.RETURN_STATUS_FAILED,"添加数据失败",null);
        }
    }

    @RequestMapping(value = "/deleteUserName")
    @ResponseBody
    public Object deleteUserName(@RequestParam("ids") String ids){
        try {
            usernameService.delete(ids);
            return ResponseUtil.constructResponse(ResponseUtil.RETURN_STATUS_SUCCESS, "添加数据成功",null);
        }catch (Exception e){
            return ResponseUtil.constructResponse(ResponseUtil.RETURN_STATUS_FAILED,"添加数据失败",null);
        }
    }


}
