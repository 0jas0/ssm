package com.jas.web.controller;

import com.jas.web.bean.model.EvalutionModel;
import com.jas.web.bean.model.EvalutionTeacherModel;
import com.jas.web.bean.model.TeacherModel;
import com.jas.web.bean.model.UserModel;
import com.jas.web.service.ITeacherEvaluationService;
import com.jas.web.service.ITeacherService;
import com.jas.web.utils.PaperUtil;
import com.jas.web.utils.ResponseUtil;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("/teacher")
public class TeacherController {

    @Resource
    private ITeacherService teacherService;

    @Resource
    private ITeacherEvaluationService teacherEvaluationService;

    @RequestMapping(value = "/ajax-get-teacher-by-page")
    @ResponseBody
    public Object getTeacherByPage(@RequestParam(value = "currentPage", defaultValue = "1") Integer currentPage,
                                   @RequestParam(value = "pageSize",defaultValue = "10") Integer pageSize,
                                   @RequestParam(value = "keyword") String keyword){
        try {
            PaperUtil<TeacherModel> teacherModelList = teacherService.getTeacherByPage(keyword, currentPage, pageSize);
            return ResponseUtil.constructResponse(ResponseUtil.RETURN_STATUS_SUCCESS, "获取所有教师成功", teacherModelList);
        }catch (Exception e){
            e.printStackTrace();
            return ResponseUtil.constructResponse(ResponseUtil.RETURN_STATUS_FAILED,"获取所有教师失败",null);
        }
    }

    @RequestMapping(value = "ajax/get-all-teacher")
    @ResponseBody
    public Object getAllTeacher(){
        try {
            List<TeacherModel> teacherModelList = teacherService.getAllTeacher();
            return ResponseUtil.constructResponse(ResponseUtil.RETURN_STATUS_SUCCESS, "获取所有教师成功", teacherModelList);
        }catch (Exception e){
            e.printStackTrace();
            return ResponseUtil.constructResponse(ResponseUtil.RETURN_STATUS_FAILED,"获取所有教师失败",null);
        }
    }

    @RequestMapping(value = "/ajax-add-teacher")
    @ResponseBody
    public Object addTeacher(TeacherModel teacherModel){
        //数据的校验
        teacherService.addTeacher(teacherModel);
        return ResponseUtil.constructResponse(ResponseUtil.RETURN_STATUS_SUCCESS, "添加教师成功", null);
    }
    @RequestMapping(value = "/ajax-delete-teacher")
    @ResponseBody
    public Object deleteTeacher(@RequestParam("teacherId") String teacherId){
        //数据的校验
        teacherService.deleteTeacher(teacherId);
        return ResponseUtil.constructResponse(ResponseUtil.RETURN_STATUS_SUCCESS, "删除教师成功", null);
    }

    @RequestMapping(value = "/ajax-modify-teacher")
    @ResponseBody
    public Object modifyTeacher(TeacherModel teacherModel){
        //数据的校验
        teacherService.modifyTeacher(teacherModel);
        return ResponseUtil.constructResponse(ResponseUtil.RETURN_STATUS_SUCCESS, "修改教师成功", null);
    }

    @RequestMapping(value = "/ajax-get-teacher")
    @ResponseBody
    public Object getByTeacherId(@RequestParam(value = "teacherId",required = true) String teacherId){
        TeacherModel teacherModel = teacherService.getByTeacherId(teacherId);
        return ResponseUtil.constructResponse(ResponseUtil.RETURN_STATUS_SUCCESS, "获取教师信息成功", teacherModel);
    }

    @RequestMapping(value = "ajax/teacher-evaluation")
    @ResponseBody
    public Object evaluationTeacher(@RequestParam("studentId")String studentId, @RequestParam("teacherId") String teacherId,
                                    @RequestParam("courseId") Integer courseId, @RequestParam("evaluationGrade") Integer evaluationGrade){
        try {
            teacherEvaluationService.evaluationTeacher(studentId, teacherId, courseId, evaluationGrade);
            return ResponseUtil.constructResponse(ResponseUtil.RETURN_STATUS_SUCCESS, "评价教师成功", null);
        }catch (Exception e){
            e.printStackTrace();
            return ResponseUtil.constructResponse(ResponseUtil.RETURN_STATUS_FAILED,"评价教师失败",null);
        }
    }


    @RequestMapping("/teacher_list")
    public String studentListView(){
        return "teacher/teacher_list";
    }
    @RequestMapping("/teacher_add")
    public String studentAddView(){
        return "teacher/teacher_add";
    }

    @RequestMapping("/teacher_edit")
    public String studentEditView(@RequestParam("teacherId") String teacherId, Model model){
        TeacherModel teacherModel = teacherService.getByTeacherId(teacherId);
        model.addAttribute("teacherModel",teacherModel);
        return "teacher/teacher_edit";
    }

    @RequestMapping("/evaluation_teacher")
    public String evaluationTeacherView(){
        return "teacher/evaluation_teacher";
    }
    @RequestMapping("ajax/student_evaluation_teacher_list")
    @ResponseBody
    public Object evaluationTeacherList(HttpSession session) {
        try {
            UserModel userModel =(UserModel) session.getAttribute("userModel");
            List<EvalutionModel> list = teacherService.studentEvaluationTeacherList(userModel.getId());
            return ResponseUtil.constructResponse(ResponseUtil.RETURN_STATUS_SUCCESS, "获取需要评级的老师列表成功", list);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseUtil.constructResponse(ResponseUtil.RETURN_STATUS_FAILED, "获取需要评级的老师列表失败", null);
        }
    }

    @RequestMapping("view_evaluation")
    public String viewEvaluation(){
        return "teacher/view_evaluation";
    }

    @RequestMapping("ajax/get-evaluation-by-teacherId")
    @ResponseBody
    public Object getEvaluationByTeacherId(@RequestParam(value = "currentPage", defaultValue = "1") Integer currentPage,
                                           @RequestParam(value = "pageSize",defaultValue = "10") Integer pageSize, HttpSession session){
        try {
            UserModel userModel = (UserModel) session.getAttribute("userModel");
            PaperUtil<EvalutionTeacherModel> evalutionTeacherModels = teacherService.getEvaluationByTeacherIdAndPage(userModel.getUsername(), currentPage, pageSize);
            return ResponseUtil.constructResponse(ResponseUtil.RETURN_STATUS_SUCCESS, "老师获取评价列表成功", evalutionTeacherModels);
        }catch (Exception e){
            e.printStackTrace();
            return ResponseUtil.constructResponse(ResponseUtil.RETURN_STATUS_SUCCESS, "老师获取评价列表失败", null);
        }
    }
}
