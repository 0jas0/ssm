package com.jas.web.service;
import com.jas.web.bean.model.ScoreModel;

public interface IScoreService {
    void addScore(ScoreModel scoreModel);

    void modifyScore(ScoreModel scoreModel);
}
