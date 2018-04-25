package com.jas.web.service.impl;

import com.github.pagehelper.PageHelper;
import com.jas.web.bean.domain.StudentTopicDO;
import com.jas.web.bean.domain.StudentUploadDO;
import com.jas.web.bean.domain.TopicDO;
import com.jas.web.bean.domain.UsernameDO;
import com.jas.web.bean.model.ScoreManagementModel;
import com.jas.web.bean.model.TopicModel;
import com.jas.web.dao.ScoreManagementDAO;
import com.jas.web.service.IScoreManagementService;
import com.jas.web.utils.EUDataGridResult;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@Service
public class ScoreManagementServiceImpl implements IScoreManagementService {

    @Resource
    ScoreManagementDAO scoreManagementDAO;

    //学生查看 自己的成绩
    public ScoreManagementModel getByStudent(HttpServletRequest request) {
        HttpSession session = request.getSession();
        String userId = (String) session.getAttribute("userId");
        String[] split = userId.split(",");
        StudentTopicDO studentTopicDO = scoreManagementDAO.getByStudent(Integer.parseInt(split[0]));
        List<StudentUploadDO> studentUploadDOList = scoreManagementDAO.getByStudentTopicId(studentTopicDO.getId());
        ScoreManagementModel scoreManagementModel = new ScoreManagementModel();
        double total = 0;
        if (studentUploadDOList.size() > 0) {
            for (StudentUploadDO studentUploadDO : studentUploadDOList) {
                if (0 == studentUploadDO.getStatus()) {
                    scoreManagementModel.setOpeningReport(studentUploadDO.getScore());
                    total = total + studentUploadDO.getScore();
                } else if (1 == studentUploadDO.getStatus()) {
                    scoreManagementModel.setCommitments(studentUploadDO.getScore());
                    total = total + studentUploadDO.getScore();
                } else if (2 == studentUploadDO.getStatus()) {
                    scoreManagementModel.setMidtermCheck(studentUploadDO.getScore());
                    total = total + studentUploadDO.getScore();
                } else if (3 == studentUploadDO.getStatus()) {
                    scoreManagementModel.setPaper(studentUploadDO.getScore());
                    total = total + studentUploadDO.getScore();
                }
            }
        }
        scoreManagementModel.setTotal(total);
        return scoreManagementModel;

    }

    //老师查看
    public EUDataGridResult getByTeacher(HttpServletRequest request, Integer page, Integer rows) {
        PageHelper.startPage(page, rows);
        HttpSession session = request.getSession();
        String userId = (String) session.getAttribute("userId");
        String[] split = userId.split(",");
        //查询 该老师发布的通过的课题
        List<TopicDO> topicDolList = scoreManagementDAO.getByTeacher(Integer.parseInt(split[0]));
        List<ScoreManagementModel> scoreManagementModelList = new ArrayList<>();
        ScoreManagementModel scoreManagementModel = null;
        if (topicDolList.size() > 0) {
            for (TopicDO t : topicDolList) {
                List<StudentTopicDO> studentTopicDOList = scoreManagementDAO.getByTopicId(t.getTopicId());
                if (studentTopicDOList.size() > 0) {
                    for (StudentTopicDO studentTopicDO : studentTopicDOList) {
                        UsernameDO student = scoreManagementDAO.getByStudentOwnId(Integer.parseInt(studentTopicDO.getStudentId()));
                        List<StudentUploadDO> studentUploadDOList = scoreManagementDAO.getByStudentTopicId(studentTopicDO.getId());
                        scoreManagementModel = new ScoreManagementModel();
                        scoreManagementModel.setStudentName(student.getName());
                        scoreManagementModel.setTopicName(t.getTitle());
                        scoreManagementModel.setTopicId(t.getTopicId());
                        double total = 0;
                        if (studentUploadDOList.size() > 0) {
                            for (StudentUploadDO studentUploadDO : studentUploadDOList) {
                                if (0 == studentUploadDO.getStatus()) {
                                    scoreManagementModel.setOpeningReport(studentUploadDO.getScore());
                                    total = total + studentUploadDO.getScore();
                                } else if (1 == studentUploadDO.getStatus()) {
                                    scoreManagementModel.setCommitments(studentUploadDO.getScore());
                                    total = total + studentUploadDO.getScore();
                                } else if (2 == studentUploadDO.getStatus()) {
                                    scoreManagementModel.setMidtermCheck(studentUploadDO.getScore());
                                    total = total + studentUploadDO.getScore();
                                } else if (3 == studentUploadDO.getStatus()) {
                                    scoreManagementModel.setPaper(studentUploadDO.getScore());
                                    total = total + studentUploadDO.getScore();
                                }
                            }
                        }
                        scoreManagementModel.setTotal(total);
                        scoreManagementModelList.add(scoreManagementModel);

                    }
                }
            }
        }
        EUDataGridResult result = new EUDataGridResult();
        int end = page * rows > scoreManagementModelList.size() ? scoreManagementModelList.size() : page * rows;
        int start = (page - 1) * rows < 0 ? 0 : ((page - 1) * rows > scoreManagementModelList.size() ? scoreManagementModelList.size() : (page - 1) * rows);
        List<ScoreManagementModel> list = scoreManagementModelList.subList(start, end);
        result.setRows(list);

        result.setTotal(scoreManagementModelList.size());
        return result;

    }

    //管理员查看学生成绩
    public EUDataGridResult getStudentScore(HttpServletRequest request, Integer page, Integer rows, String name, String serialNumber, String major) {
        PageHelper.startPage(page, rows);
        List<UsernameDO> usernameDOList = scoreManagementDAO.selectStudent(name, serialNumber, major);
        List<ScoreManagementModel> scoreManagementModelList = new ArrayList<>();
        ScoreManagementModel scoreManagementModel = null;
        if (usernameDOList.size() > 0) {
            for (UsernameDO student : usernameDOList) {
                StudentTopicDO studentTopicDO = scoreManagementDAO.getByStudent(student.getId());
                List<StudentUploadDO> studentUploadDOList = scoreManagementDAO.getByStudentTopicId(studentTopicDO.getId());
                scoreManagementModel = new ScoreManagementModel();
                double total = 0;
                if (studentUploadDOList.size() > 0) {
                    for (StudentUploadDO studentUploadDO : studentUploadDOList) {
                        if (0 == studentUploadDO.getStatus()) {
                            scoreManagementModel.setOpeningReport(studentUploadDO.getScore());
                            total = total + studentUploadDO.getScore();
                        } else if (1 == studentUploadDO.getStatus()) {
                            scoreManagementModel.setCommitments(studentUploadDO.getScore());
                            total = total + studentUploadDO.getScore();
                        } else if (2 == studentUploadDO.getStatus()) {
                            scoreManagementModel.setMidtermCheck(studentUploadDO.getScore());
                            total = total + studentUploadDO.getScore();
                        } else if (3 == studentUploadDO.getStatus()) {
                            scoreManagementModel.setPaper(studentUploadDO.getScore());
                            total = total + studentUploadDO.getScore();
                        }
                    }
                }
                scoreManagementModel.setStudentName(student.getName());
                scoreManagementModel.setTotal(total);
                scoreManagementModelList.add(scoreManagementModel);
            }

        }
        EUDataGridResult result = new EUDataGridResult();
        int end = page*rows > scoreManagementModelList.size() ? scoreManagementModelList.size() : page*rows;
        int start = (page - 1) * rows < 0 ? 0 : ((page - 1) * rows > scoreManagementModelList.size() ? scoreManagementModelList.size() : (page-1) * rows);
        List<ScoreManagementModel> list = scoreManagementModelList.subList(start, end);
        result.setRows(list);

        result.setTotal(scoreManagementModelList.size());
        return result;
    }
}
