package com.jas.web.controller;

import com.jas.web.bean.model.*;
import com.jas.web.exception.ParamNotValidException;
import com.jas.web.service.*;
import com.jas.web.utils.PaperUtil;
import com.jas.web.utils.ResponseUtil;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/course")
public class CourseController {
    @Resource
    ICourseService courseService;

    @Resource
    ICollegeMajorService collegeMajorService;

    @Resource
    ITeacherService teacherService;

    @Resource
    IAdministrativeClassService administrativeClassService;

    @Resource
    IStudentService studentService;

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

    @RequestMapping(value = "/ajax-get-course-by-page")
    @ResponseBody
    public Object getAllTeacher(@RequestParam(value = "currentPage", defaultValue = "1") Integer currentPage, @RequestParam(value = "pageSize",defaultValue = "10") Integer pageSize){
        try {
            PaperUtil<CourseModel> courseByPage = courseService.getCourseByPage(currentPage, pageSize);
            return ResponseUtil.constructResponse(ResponseUtil.RETURN_STATUS_SUCCESS, "获取课程列表成功", courseByPage);
        }catch (Exception e){
            e.printStackTrace();
            return ResponseUtil.constructResponse(ResponseUtil.RETURN_STATUS_FAILED,"获取课程列表失败",null);
        }
    }

    @RequestMapping("/course_list")
    public String studentListView(){
        return "course/course_list";
    }
    @RequestMapping("/course_add")
    public String studentAddView(){
        return "course/course_add";
    }

    @RequestMapping("/course_edit")
    public String studentEditView(@RequestParam("courseId") Integer courseId, Model model){
        CourseModel courseModel = courseService.getCourseById(courseId);
        List<CollegeMajorModel> college = collegeMajorService.getCollege();
        List<TeacherModel> teacher = teacherService.getAllTeacher();
        model.addAttribute("courseModel",courseModel);
        model.addAttribute("collegeList",college);
        model.addAttribute("teacherALl",teacher);
        return "course/course_edit";
    }

    @RequestMapping("ajax/get-course-time-place-by-courseId")
    @ResponseBody
    public Object getCourseTimePlaceByCourseId(@RequestParam("courseId") Integer courseId){
        try {
            List<CourseTimePlaceModel> list = courseService.getCOurseTimePlaceByCourseId(courseId);
            return ResponseUtil.constructResponse(ResponseUtil.RETURN_STATUS_SUCCESS,"获取课程时间地点成功",list);
        }catch (Exception e){
            e.printStackTrace();
            return ResponseUtil.constructResponse(ResponseUtil.RETURN_STATUS_FAILED,"获取课程时间地点失败",null);
        }
    }

    @RequestMapping("/course_time_place_list")
    public String courseTimePlaceView(@RequestParam("courseId") Integer courseId,Model model){
        model.addAttribute("courseId",courseId);
        return "courseTimePlace/courseTimePlace_list";
    }

    @RequestMapping("/course_time_place_add")
    public String courseTimePlaceAddView(@RequestParam("courseId") Integer courseId, Model model){
        CourseModel course = courseService.getCourseById(courseId);
        model.addAttribute("collegeId", Integer.valueOf(course.getCollege()));
        model.addAttribute("courseId",courseId);
        return "courseTimePlace/courseTimePlace_add";
    }

    @RequestMapping("/course_time_place_edit")
    public String courseTimePlaceEditView(@RequestParam("courseTimePlaceId") Integer courseId, Model model){
        CourseTimePlaceModel courseTimePlaceModel = courseService.getCourseTimePlaceById(courseId);
        CourseModel courseModel = courseService.getCourseById(courseTimePlaceModel.getCourseId());
        List<AdministrativeClassModel> classModels = administrativeClassService.getByCollege(Integer.valueOf(courseModel.getCollege()));
        model.addAttribute("classModels",classModels);
        model.addAttribute("courseTimePlaceModel", courseTimePlaceModel);
        return "courseTimePlace/courseTimePlace_edit";
    }

    @RequestMapping("/cousre-schedule-by-studentId")
    @ResponseBody
    public Object courseScheduleByStudentId(@RequestParam("studentId") Integer studentId){
        try {
            Map<Integer,Map<Integer,String>> courseSchedule = courseService.getCourseScheduleByStudentId(studentId);
            return ResponseUtil.constructResponse(ResponseUtil.RETURN_STATUS_SUCCESS,"获取课程表成功",courseSchedule);
        }catch (Exception e){
            e.printStackTrace();
            return ResponseUtil.constructResponse(ResponseUtil.RETURN_STATUS_FAILED,"获取课程表失败",null);
        }
    }


}