package com.jas.web.controller;

import com.jas.web.bean.model.*;
import com.jas.web.exception.ParamNotValidException;
import com.jas.web.service.ICourseService;
import com.jas.web.service.IScoreService;
import com.jas.web.service.IStudentService;
import com.jas.web.utils.PaperUtil;
import com.jas.web.utils.ResponseUtil;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/score")
public class ScoreController {

    @Resource
    private IScoreService scoreService;

    @Resource
    private ICourseService courseService;

    @Resource
    private IStudentService studentService;

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

    @RequestMapping(value = "/delete-score")
    @ResponseBody
    public Object deleteScore(@RequestParam("scoreId") Integer scoreId){
        try {
            scoreService.deleteScore(scoreId);
            return ResponseUtil.constructResponse(ResponseUtil.RETURN_STATUS_SUCCESS, "删除成绩成功", null);
        }catch (Exception e){
            e.printStackTrace();
            return ResponseUtil.constructResponse(ResponseUtil.RETURN_STATUS_FAILED,"删除成绩失败",null);
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

    @RequestMapping(value = "/ajax-get-score-by-student-id")
    @ResponseBody
    public Object getScoreByStudentId(HttpSession session){
        try {
            UserModel userModel = (UserModel) session.getAttribute("userModel");
            Map<String,List<CourseModel>> scoreMap = scoreService.getScoreBystudentId(userModel.getId());
            return ResponseUtil.constructResponse(ResponseUtil.RETURN_STATUS_SUCCESS,"获取个人成绩成功",scoreMap);
        }catch (Exception e){
            e.printStackTrace();
            return ResponseUtil.constructResponse(ResponseUtil.RETURN_STATUS_FAILED,"获取个人成绩失败",null);
        }
    }

    @RequestMapping("/score_student")
    public String courseScheduleView(){
        return "score/score_student";
    }

    @RequestMapping("/score_list")
    public String scoreListView(){
        return "score/score_list";
    }

    @RequestMapping("ajax/score-list-by-teacherId")
    @ResponseBody
    public Object scoreListTeacherId(@RequestParam("currentPage") Integer currentPage, @RequestParam("pageSize") Integer pageSize, HttpSession session){
        try {
            UserModel userModel = (UserModel) session.getAttribute("userModel");
            String teacherId = userModel.getUsername();
            List<CourseModel> courseModel = courseService.getCourseByTeacherIdAndPage(teacherId, currentPage, pageSize);
            int totalNum = courseService.getCourseNumByTeacherId(teacherId);
            PaperUtil<CourseModel> paperUtil = new PaperUtil<>();
            paperUtil.setCurrentPage(currentPage);
            paperUtil.setTotalRecord(totalNum);
            paperUtil.setPageSize(pageSize);
            paperUtil.setData(courseModel);
            return ResponseUtil.constructResponse(ResponseUtil.RETURN_STATUS_SUCCESS,"获取成绩列表成功",paperUtil);
        }catch (Exception e){
            e.printStackTrace();
            return ResponseUtil.constructResponse(ResponseUtil.RETURN_STATUS_FAILED,"获取成绩列表失败",null);
        }
    }
    @RequestMapping("ajax/score-detail-courseId")
    @ResponseBody
    public Object scoreDetailByCourseId(@RequestParam("courseId") Integer courseId, @RequestParam(value = "currentPage",defaultValue = "1") Integer currentPage, @RequestParam(value = "pageSize",defaultValue = "10") Integer pageSize){
        try {
            PaperUtil<ScoreDetailModel> list = scoreService.getScoreDetailByCourseId(courseId,currentPage,pageSize);
            return ResponseUtil.constructResponse(ResponseUtil.RETURN_STATUS_SUCCESS,"获取成绩详情列表成功",list);
        }catch (Exception e){
            e.printStackTrace();
            return ResponseUtil.constructResponse(ResponseUtil.RETURN_STATUS_FAILED,"获取成绩详情列表失败",null);
        }
    }

    @RequestMapping("ajax/edit-score-studentId")
    @ResponseBody
    public Object editScoreByStudentId(ScoreDetailModel scoreDetailModel){
        try {
            scoreService.editScoreByStudentId(scoreDetailModel);
            return ResponseUtil.constructResponse(ResponseUtil.RETURN_STATUS_SUCCESS,"获取成绩详情列表成功",null);
        }catch (Exception e){
            e.printStackTrace();
            return ResponseUtil.constructResponse(ResponseUtil.RETURN_STATUS_FAILED,"获取成绩详情列表失败",null);
        }
    }

    @RequestMapping("ajax/get-score-list-by-classId")
    @ResponseBody
    public Object getCourseTimePlaceByCourseId(@RequestParam("classId") Integer classId){
        try {
            List<ClassScoreModel> list = scoreService.getScoreByClassId(classId);
            return ResponseUtil.constructResponse(ResponseUtil.RETURN_STATUS_SUCCESS,"获取成绩列表成功",list);
        }catch (Exception e){
            e.printStackTrace();
            return ResponseUtil.constructResponse(ResponseUtil.RETURN_STATUS_FAILED,"获取成绩列表失败",null);
        }
    }

    @RequestMapping("/score_detail")
    public String scoreDetailView(@RequestParam("courseId") Integer courseId, Model model){
        model.addAttribute("courseId", courseId);
        return "score/score_detail";
    }

    @RequestMapping("/score_add")
    public String scoreAddView(@RequestParam("courseId") Integer courseId,Model model){
        //通过课程的id获取所有的学生的id
        List<StudentModel> studentModels = studentService.getStudentByCourseId(courseId);
        model.addAttribute("studentModels", studentModels);
        model.addAttribute("courseId",courseId);
        return "score/score_add";
    }

    @RequestMapping("/score_edit")
    public String scoreEditView(@RequestParam("scoreId") Integer scoreId,Model model){
        ScoreModel scoreModel = scoreService.getScoreById(scoreId);
        StudentModel student = studentService.getStudentById(scoreModel.getStudentId());
        model.addAttribute("studentModel",student);
        model.addAttribute("scoreModel",scoreModel);
        return "score/score_edit";
    }

    @RequestMapping("/class_score_list")
    public String courseTimePlaceView(@RequestParam("classId") Integer classId,Model model){
        model.addAttribute("classId",classId);
        return "score/class_score_list";
    }
}
