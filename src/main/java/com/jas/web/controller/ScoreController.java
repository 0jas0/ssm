package com.jas.web.controller;

import com.jas.web.bean.model.ScoreModel;
import com.jas.web.bean.model.StudentModel;
import com.jas.web.exception.ParamNotValidException;
import com.jas.web.service.IScoreService;
import com.jas.web.service.IStudentService;
import com.jas.web.utils.ResponseUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;

@Controller
@RequestMapping("/score")
public class ScoreController {

    @Resource
    private IScoreService scoreService;

    @RequestMapping(value = "/ajax-add-score")
    @ResponseBody
    public Object addScore(ScoreModel scoreModel){
        try {
            //数据的校验
            scoreService.addScore(scoreModel);
            return ResponseUtil.constructResponse(ResponseUtil.RETURN_STATUS_SUCCESS, "添加成绩成功", null);
        }catch (ParamNotValidException e){
            return ResponseUtil.constructResponse(ResponseUtil.RETURN_STATUS_FAILED,e.getMessage(),null);
        }catch (Exception e){
            e.printStackTrace();
            return ResponseUtil.constructResponse(ResponseUtil.RETURN_STATUS_FAILED,"添加成绩失败",null);
        }
    }

    @RequestMapping(value = "/ajax-modify-score")
    @ResponseBody
    public Object modifyScore(ScoreModel scoreModel){
        try {
            //数据的校验
            scoreService.modifyScore(scoreModel);
            return ResponseUtil.constructResponse(ResponseUtil.RETURN_STATUS_SUCCESS, "修改成绩成功", null);
        }catch (ParamNotValidException e){
            return ResponseUtil.constructResponse(ResponseUtil.RETURN_STATUS_FAILED,e.getMessage(),null);
        }catch (Exception e){
            e.printStackTrace();
            return ResponseUtil.constructResponse(ResponseUtil.RETURN_STATUS_FAILED,"修改成绩失败",null);
        }
    }
}
