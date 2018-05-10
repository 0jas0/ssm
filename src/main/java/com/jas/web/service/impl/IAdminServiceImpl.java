package com.jas.web.service.impl;

import com.jas.web.bean.AdminDO;
import com.jas.web.dao.IAdminDAO;
import com.jas.web.service.IAdminService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class IAdminServiceImpl implements IAdminService{
    @Resource
    IAdminDAO adminDAO;

    @Override
    public AdminDO getByName(String name) {
        AdminDO admin = adminDAO.getAdminByName(name);
        return admin;
    }
}
