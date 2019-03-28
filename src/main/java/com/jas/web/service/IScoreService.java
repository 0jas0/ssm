package com.jas.web.service;
import com.jas.web.bean.model.ClassScoreModel;
import com.jas.web.bean.model.CourseModel;
import com.jas.web.bean.model.ScoreDetailModel;
import com.jas.web.bean.model.ScoreModel;
import com.jas.web.utils.PaperUtil;

import java.util.List;
import java.util.Map;

public interface IScoreService {
    void addScore(ScoreModel scoreModel);

    void modifyScore(ScoreModel scoreModel);

    Map<String,List<CourseModel>> getScoreBystudentId(Integer studentId);

    PaperUtil<ScoreDetailModel> getScoreDetailByCourseId(Integer courseId,Integer currentPage,Integer pageSize);

    ScoreModel getScoreById(Integer scoreId);

    void deleteScore(Integer scoreId);

    /**
     * 编辑学生的成绩信息
     * @param scoreDetailModel
     */
    void editScoreByStudentId(ScoreDetailModel scoreDetailModel);

    /**
     * 获取整个班级的成绩
     * 通过courseId排序
     * @param classId
     * @return
     */
    List<ClassScoreModel> getScoreByClassId(Integer classId);
}
