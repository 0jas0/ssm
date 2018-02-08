package com.jas.web.service;

import com.jas.web.model.UserModel;
import com.jas.web.param.UserParam;

import java.util.List;

public interface IUserService {
    public List<UserModel> listUsers(UserParam userParam);
}
