package com.jas.web.interceptor;

import com.jas.web.bean.model.UserModel;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;


public class LoginInterceptor extends HandlerInterceptorAdapter {

    private final static List<String> list = new ArrayList<>();

    static {
        list.add("/login");
        list.add("/do-login");
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        //如果访问的不是登陆页面或者登陆接口进行拦截
        String uri = request.getRequestURI();
        if (!list.contains(uri)){
            HttpSession session = request.getSession();
            UserModel userModel = (UserModel)session.getAttribute("userModel");
            if (userModel == null){
                //跳转登陆页面
                response.sendRedirect("/login");
                return false;
            }
        }
        return true;
    }
}
