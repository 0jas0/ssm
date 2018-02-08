package com.jas.web.service.impl;

import com.jas.web.dao.IUserDAO;
import com.jas.web.domain.UserDO;
import com.jas.web.model.UserModel;
import com.jas.web.param.UserParam;
import com.jas.web.service.IUserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.LinkedList;
import java.util.List;

@Service
public class IUserServiceImpl implements IUserService {
    @Resource
    IUserDAO userDAO;

    public List<UserModel> listUsers(UserParam userParam) {
        List<UserDO> listUsers = userDAO.listUsers();
        List<UserModel> userModelList = new LinkedList<UserModel>();
        for (UserDO userDO : listUsers){
            userModelList.add(new UserModel(userDO));
        }
        return userModelList;
    }
}
