package com.jas.web.controller;

import com.jas.web.bean.param.UsernameParam;
import com.jas.web.enums.ERole;
import com.jas.web.exception.ParamNotValidException;
import com.jas.web.service.IUsernameService;
import com.jas.web.utils.EUDataGridResult;
import com.jas.web.utils.ResponseUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

@Controller
@RequestMapping("/student")
public class StudentController {

    @Resource
    private IUsernameService usernameService;

    @RequestMapping(value = "/addView")
    public String add(){
        return "student_add";
    }

    @RequestMapping(value = "/editView")
    public String edit(){
        return "student_edit";
    }


    @RequestMapping(value = "/list")
    public String list(){
        return "student_list";
    }

    @RequestMapping(value = "/getData")
    @ResponseBody
    public Object getData(Integer page, Integer rows){
        EUDataGridResult result = usernameService.getStudentList(page, rows);
        return result;
    }

    @RequestMapping(value = "/addUserName")
    @ResponseBody
    public Object addUserName(UsernameParam usernameParam){
        try {
            usernameParam.setStatus(ERole.STUDENT.getValue());
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
            usernameParam.setStatus(ERole.STUDENT.getValue());
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
