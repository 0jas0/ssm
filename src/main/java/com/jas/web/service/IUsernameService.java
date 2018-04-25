package com.jas.web.service;


import com.jas.web.bean.model.UsernameModel;
import com.jas.web.bean.param.UsernameParam;
import com.jas.web.utils.EUDataGridResult;

public interface IUsernameService {
    public int insert(UsernameModel usernameModel);

    public UsernameModel getBySerialNumber(String serialNumber, Integer status);

    EUDataGridResult getTeacherList(Integer page, Integer rows);

    EUDataGridResult getStudentList(Integer page, Integer rows);

    void update(UsernameParam usernameParam);

    void delete(String ids);

    UsernameModel getById(Integer integer);
}
