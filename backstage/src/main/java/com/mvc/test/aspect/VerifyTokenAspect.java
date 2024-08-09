package com.mvc.test.aspect;

import com.mvc.test.utils.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class VerifyTokenAspect implements HandlerInterceptor {
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse,Object object){
        String token = httpServletRequest.getHeader("token");
        System.out.println("8888");
        System.out.println(token);
        return false;
    }
}
