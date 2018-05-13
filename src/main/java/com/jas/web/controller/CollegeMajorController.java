package com.jas.web.controller;

import com.jas.web.bean.model.CollegeMajorModel;
import com.jas.web.bean.model.CourseModel;
import com.jas.web.bean.model.CourseTimePlaceModel;
import com.jas.web.exception.ParamNotValidException;
import com.jas.web.service.ICollegeMajorService;
import com.jas.web.service.ICourseService;
import com.jas.web.utils.ResponseUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;

@Controller
@RequestMapping("/collegeMajor")
public class CollegeMajorController {
    @Resource
    ICollegeMajorService collegeMajorService;

    @RequestMapping("ajax/get-college")
    @ResponseBody
    public Object getCollege() {
        try {
            List<CollegeMajorModel> list = collegeMajorService.getCollege();
            return ResponseUtil.constructResponse(ResponseUtil.RETURN_STATUS_SUCCESS, "获取学院成功", list);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseUtil.constructResponse(ResponseUtil.RETURN_STATUS_FAILED, "获取学院失败", null);
        }
    }


    @RequestMapping("ajax/get-major-by-parentId")
    @ResponseBody
    public Object ajaxGetMajorByParentId(@RequestParam("parentId") Integer parentId) {
        try {
            List<CollegeMajorModel> list = collegeMajorService.getMajorByParentId(parentId);
            return ResponseUtil.constructResponse(ResponseUtil.RETURN_STATUS_SUCCESS, "获取专业成功", list);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseUtil.constructResponse(ResponseUtil.RETURN_STATUS_FAILED, "获取专业失败", null);
        }
    }
}