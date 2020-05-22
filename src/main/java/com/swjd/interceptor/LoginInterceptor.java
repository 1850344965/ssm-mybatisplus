package com.swjd.interceptor;

import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LoginInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //获取请求的地址
        String requestUri=request.getRequestURI();
        //1.如果是登录页面我们放行-后缀是Login或返回的是'login'
        if (requestUri.indexOf("login")>=0||requestUri.indexOf("Login")>=0){
            return true;
        }
        //2.如果登录过
        HttpSession session=request.getSession();//获取session
        if (session.getAttribute("activeName")!=null){
            return true;
        }

        //不放行并且需要回到登录页面
        request.getRequestDispatcher("/toLogin").forward(request,response);
        return false;
    }
}
