package com.jas.web.service;
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
}
