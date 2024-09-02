package com.mvc.test.interceotor;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mvc.test.annotation.VerifyToken;
import com.mvc.test.utils.JwtUtils;
import com.mvc.test.utils.Result;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.method.HandlerMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;

@Component
public class JwtInterceptor implements HandlerInterceptor {
    private JwtUtils jwtUtils = new JwtUtils();
    private ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (handler instanceof HandlerMethod) {
            System.out.println("还得是这个");
            HandlerMethod handlerMethod = (HandlerMethod) handler;
            VerifyToken verifyToken = handlerMethod.getMethodAnnotation(VerifyToken.class);
            if (verifyToken != null && verifyToken.required()) {
                String authHeader = request.getHeader("Authorization");
                if (authHeader != null) {
                    String[] authHeaders = authHeader.split(",");
                    for (String header : authHeaders) {
                        header = header.trim();
                        if (header.startsWith("Bearer ")) {
                            // 只处理 Bearer 令牌
                            String token = header.substring(7); // 提取 Bearer 令牌
                            try {
                                System.out.println("userId:"+token);
                                String userId = jwtUtils.extractId(token);
                                System.out.println("userId----");
                                if (userId != null) {
                                    // 可以在这里设置认证对象到 SecurityContext 中
                                    UsernamePasswordAuthenticationToken authentication =
                                            new UsernamePasswordAuthenticationToken(userId, null, new ArrayList<>());
                                    SecurityContextHolder.getContext().setAuthentication(authentication);
                                    return true;
                                }
                            } catch (Exception e) {
                                System.out.println("这里的问题");
                                response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                                response.setContentType("application/json; charset=UTF-8");
                                Result result = Result.internalServerError();
                                response.getWriter().write(objectMapper.writeValueAsString(result));
                                return false;
                            }
                            break;
                        }

                    }
                }
                else{
                    response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                    response.setContentType("application/json; charset=UTF-8");
                    Result result = new Result(-1, "权限不足", null);
                    response.getWriter().write(objectMapper.writeValueAsString(result));
                    return false;
                }
            }
        }
        return true;
    }
}
