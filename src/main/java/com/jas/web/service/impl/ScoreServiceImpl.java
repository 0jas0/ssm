package com.jas.web.service.impl;

import com.jas.web.bean.domain.ScoreDO;
import com.jas.web.bean.model.ScoreModel;
import com.jas.web.dao.IScoreDAO;
import com.jas.web.service.IScoreService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

@Service
public class ScoreServiceImpl implements IScoreService{
    @Resource
    IScoreDAO scoreDAO;

    @Override
    @Transactional
    public void addScore(ScoreModel scoreModel) {
        scoreDAO.addScore(new ScoreDO(scoreModel));
    }

    @Override
    @Transactional
    public void modifyScore(ScoreModel scoreModel) {
        scoreDAO.updateScore(new ScoreDO(scoreModel));
    }
}
