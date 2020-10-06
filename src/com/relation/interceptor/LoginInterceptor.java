package com.relation.interceptor;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LoginInterceptor implements HandlerInterceptor {

    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {
        HttpSession sess=httpServletRequest.getSession();
        System.out.println("enter interceptor");
        if(httpServletRequest.getRequestURI().contains("login")){
            return true;
        }
        if(httpServletRequest.getRequestURI().contains("register")){
            return true;
        }
        if(sess.getAttribute("user")!=null){
            return true;
        }
        if(httpServletRequest.getRequestURI().contains("user_center.jsp")){
            return true;
        }
        httpServletResponse.sendRedirect("../login.jsp");
        return false;
    }
}
