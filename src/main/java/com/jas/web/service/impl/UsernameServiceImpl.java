package com.jas.web.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.jas.web.bean.domain.UsernameDO;
import com.jas.web.bean.model.UsernameModel;
import com.jas.web.bean.param.UsernameParam;
import com.jas.web.dao.IUsernameDAO;
import com.jas.web.enums.ERole;
import com.jas.web.exception.ParamNotValidException;
import com.jas.web.service.IUsernameService;
import com.jas.web.utils.EUDataGridResult;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

@Service
public class UsernameServiceImpl implements IUsernameService{

    @Resource
    private IUsernameDAO usernameDAO;

    @Transactional
    public int insert(UsernameModel usernameModel) {
        UsernameDO usernameDO = usernameDAO.getById(usernameModel.getSerialNumber(), usernameModel.getStatus());
        if (usernameDO != null){
            throw new ParamNotValidException("编号已经存在，请更换编号");
        }
        int id = usernameDAO.insert(new UsernameDO(usernameModel));
        return id;
    }

    @Transactional
    public void update(UsernameModel usernameModel){
        usernameDAO.upload(new UsernameDO(usernameModel));
    }

    @Override
    public UsernameModel getBySerialNumber(String serialNumber, Integer status){
        UsernameDO usernameDO = usernameDAO.getById(serialNumber, status);
        UsernameModel usernameModel = new UsernameModel(usernameDO);
        return usernameModel;
    }

    @Override
    public EUDataGridResult getTeacherList(Integer page, Integer rows) {
        PageHelper.startPage(page, rows);
        List<UsernameDO> usernameDOS = usernameDAO.getByStatus(ERole.TEACHER.getValue());


        List<UsernameModel> list = new LinkedList<>();
        for (UsernameDO usernameDO : usernameDOS){
            list.add(new UsernameModel(usernameDO));
        }
        EUDataGridResult result = new EUDataGridResult();
        int end = page*rows > list.size() ? list.size() : page*rows;
        int start = (page - 1) * rows < 0 ? 0 : ((page - 1) * rows > list.size() ? list.size() : (page-1) * rows);
        List<UsernameModel> usernameModels = list.subList(start, end);

        result.setRows(usernameModels);

        result.setTotal(list.size());
        return result;
    }

    @Override
    public EUDataGridResult getStudentList(Integer page, Integer rows) {
        PageHelper.startPage(page, rows);
        List<UsernameDO> usernameDOS = usernameDAO.getByStatus(ERole.STUDENT.getValue());


        List<UsernameModel> list = new LinkedList<>();
        for (UsernameDO usernameDO : usernameDOS){
            list.add(new UsernameModel(usernameDO));
        }
        EUDataGridResult result = new EUDataGridResult();
        int end = page*rows > list.size() ? list.size() : page*rows;
        int start = (page - 1) * rows < 0 ? 0 : ((page - 1) * rows > list.size() ? list.size() : (page-1) * rows);
        List<UsernameModel> usernameModels = list.subList(start, end);

        result.setRows(usernameModels);

        result.setTotal(list.size());
        return result;
    }

    @Override
    public void update(UsernameParam usernameParam) {
        UsernameModel usernameModel = usernameParam.getUsernameModel();
        usernameDAO.upload(new UsernameDO(usernameModel));
    }

    @Transactional
    @Override
    public void delete(String ids) {
        String[] strIds = ids.split(",");
        for (String id : strIds){
            usernameDAO.deleteById(Integer.valueOf(id));
        }
    }

    @Override
    public UsernameModel getById(Integer integer) {
        UsernameDO usernameDO = usernameDAO.getByUserId(integer);
        return new UsernameModel(usernameDO);
    }
}
