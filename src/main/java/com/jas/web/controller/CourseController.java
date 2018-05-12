package com.jas.web.controller;

import com.jas.web.bean.model.CourseModel;
import com.jas.web.bean.model.CourseTimePlaceModel;
import com.jas.web.exception.ParamNotValidException;
import com.jas.web.service.ICourseService;
import com.jas.web.utils.ResponseUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

@Controller
@RequestMapping("/course")
public class CourseController {
    @Resource
    ICourseService courseService;

    @RequestMapping("ajax/modify-course")
    @ResponseBody
    public Object modifyCourse(CourseModel courseModel) {
        try {
            courseService.modifyCourse(courseModel);
            return ResponseUtil.constructResponse(ResponseUtil.RETURN_STATUS_SUCCESS, "修改课程成功", null);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseUtil.constructResponse(ResponseUtil.RETURN_STATUS_FAILED, "修改课程失败", null);
        }
    }


    @RequestMapping("ajax/modify-course-time-place")
    @ResponseBody
    public Object ajaxmodifyCourseTimePlace(CourseTimePlaceModel courseTimePlaceModel) {
        try {
            courseService.modifyCourseTimePlace(courseTimePlaceModel);
            return ResponseUtil.constructResponse(ResponseUtil.RETURN_STATUS_SUCCESS, "修改课程时间地点成功", null);
        } catch (ParamNotValidException e) {
            return ResponseUtil.constructResponse(ResponseUtil.RETURN_STATUS_FAILED, e.getMessage(), null);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseUtil.constructResponse(ResponseUtil.RETURN_STATUS_FAILED, "修改课程时间地点失败", null);
        }
    }

    @RequestMapping("ajax/add-course-time-place")
    @ResponseBody
    public Object ajaxAddCourseTimePlace(CourseTimePlaceModel courseTimePlaceModel) {
        try {
            courseService.addCourseTimePlace(courseTimePlaceModel);
            return ResponseUtil.constructResponse(ResponseUtil.RETURN_STATUS_SUCCESS, "添加课程时间地点成功", null);
        } catch (ParamNotValidException e) {
            return ResponseUtil.constructResponse(ResponseUtil.RETURN_STATUS_FAILED, e.getMessage(), null);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseUtil.constructResponse(ResponseUtil.RETURN_STATUS_FAILED, "添加课程时间地点失败", null);
        }
    }

    @RequestMapping("ajax/delete-course")
    @ResponseBody
    public Object ajaxDeleteCourse(@RequestParam("id") Integer id) {
        try {
            courseService.deleteCourse(id);
            return ResponseUtil.constructResponse(ResponseUtil.RETURN_STATUS_SUCCESS, "删除课程成功", null);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseUtil.constructResponse(ResponseUtil.RETURN_STATUS_FAILED, "删除课程失败", null);
        }
    }

    @RequestMapping("ajax/delete-course-time-place")
    @ResponseBody
    public Object ajaxDeleteCourseTimePlace(@RequestParam("id") Integer id) {
        try {
            courseService.deleteCourseTimePlace(id);
            return ResponseUtil.constructResponse(ResponseUtil.RETURN_STATUS_SUCCESS, "删除课程时间地点成功", null);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseUtil.constructResponse(ResponseUtil.RETURN_STATUS_FAILED, "添加课程时间地点成功", null);
        }
    }


    @RequestMapping("ajax/add-course")
    @ResponseBody
    public Object ajaxAddCourse(CourseModel courseModel) {
        try {
            courseService.addCourse(courseModel);
            return ResponseUtil.constructResponse(ResponseUtil.RETURN_STATUS_SUCCESS, "添加课程成功", null);
        } catch (ParamNotValidException e) {
            return ResponseUtil.constructResponse(ResponseUtil.RETURN_STATUS_FAILED, e.getMessage(), null);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseUtil.constructResponse(ResponseUtil.RETURN_STATUS_FAILED, "添加课程失败", null);

        }
    }
}