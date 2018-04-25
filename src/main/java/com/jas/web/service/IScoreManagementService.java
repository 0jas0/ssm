package com.jas.web.service;

import com.jas.web.bean.model.ScoreManagementModel;
import com.jas.web.utils.EUDataGridResult;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public interface IScoreManagementService {

    public ScoreManagementModel getByStudent(HttpServletRequest request);

    public EUDataGridResult getByTeacher(HttpServletRequest request, Integer page, Integer rows);


}
