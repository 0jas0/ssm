package com.jas.web.filter;

import com.jas.web.utils.StringUtil;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class LoginFilter implements Filter{
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        /*HttpServletRequest request = (HttpServletRequest) servletRequest;
        String requestURI = request.getRequestURI();
        HttpSession session = request.getSession();
        String userId =  (String) session.getAttribute("userId");

        if (requestURI.contains("/ajaxLogin") || requestURI.contains(".js") || requestURI.contains(".css")|| requestURI.contains(".png")|| requestURI.contains(".ico")|| requestURI.contains(".jpg")|| requestURI.contains(".html") || requestURI.contains(".jsp")){
            filterChain.doFilter(servletRequest,servletResponse);
            return;
        }
        if(!requestURI.equals("/login") && StringUtil.isEmpty(userId)){
            HttpServletResponse response = (HttpServletResponse) servletResponse;
            response.sendRedirect("/login");
            return;
        }*/
        filterChain.doFilter(servletRequest,servletResponse);
    }

    @Override
    public void destroy() {

    }
}
