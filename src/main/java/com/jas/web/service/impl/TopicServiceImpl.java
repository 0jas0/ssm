package com.jas.web.service.impl;

import com.github.pagehelper.PageHelper;
import com.jas.web.bean.domain.TopicDO;
import com.jas.web.bean.domain.UsernameDO;
import com.jas.web.bean.model.TopicModel;
import com.jas.web.bean.model.UsernameModel;
import com.jas.web.bean.param.UsernameParam;
import com.jas.web.dao.ITopicDAO;
import com.jas.web.dao.IUsernameDAO;
import com.jas.web.enums.ERole;
import com.jas.web.exception.ParamNotValidException;
import com.jas.web.service.ITopicService;
import com.jas.web.utils.EUDataGridResult;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.LinkedList;
import java.util.List;

@Service
public class TopicServiceImpl implements ITopicService{
    @Resource
    ITopicDAO topicDAO;

    @Transactional
    public int insert(TopicModel topicModel) {
        topicModel.setStatus(0);
        topicModel.setTeacherId(2);
        int id = topicDAO.insert(new TopicDO(topicModel));
        return id;
    }

    @Transactional
    public void update(TopicModel topicModel){
        TopicDO topicDO = topicDAO.getById(topicModel.getTopicId());
        topicDO.setLimitStudent(topicModel.getLimitStudent());
        topicDO.setMajor(topicModel.getMajor());
        topicDO.setRemark(topicModel.getRemark());
        topicDO.setTitle(topicModel.getTitle());
        topicDAO.upload(topicDO);
    }

    public TopicModel getByTopicId(String topicId){
        TopicDO topicDO = topicDAO.getById(topicId);
        TopicModel topicModel = new TopicModel(topicDO);
        return topicModel;
    }

    public EUDataGridResult getList(Integer page, Integer rows) {
        PageHelper.startPage(page, rows);
        List<TopicDO> topicDOS = topicDAO.getAll();


        List<TopicModel> list = new LinkedList<>();
        for (TopicDO topicDO : topicDOS){
            list.add(new TopicModel(topicDO));
        }
        EUDataGridResult result = new EUDataGridResult();
        int end = page*rows > list.size() ? list.size() : page*rows;
        int start = (page - 1) * rows < 0 ? 0 : ((page - 1) * rows > list.size() ? list.size() : (page-1) * rows);
        List<TopicModel> topicModels = list.subList(start, end);

        result.setRows(topicModels);

        result.setTotal(list.size());
        return result;
    }

    @Transactional
    public void delete(String ids) {
        String[] strIds = ids.split(",");
        for (String id : strIds){
            topicDAO.delete(id);
        }
    }
}

