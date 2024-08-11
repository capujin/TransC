package com.mvc.test.filter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mvc.test.utils.JwtUtils;
import com.mvc.test.utils.Result;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

public class JwtAuthenticationFilter extends OncePerRequestFilter {
    private JwtUtils jwtUtils = new JwtUtils(); // Assuming JwtUtils has appropriate methods
    private ObjectMapper objectMapper = new ObjectMapper(); // 用于将 Result 转换为 JSON
    private static final String[] EXCLUDED_PATHS = {/*"/user/login"*/};
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws IOException, ServletException {
        String requestURI = request.getRequestURI();
        // 检查请求路径是否需要拦截
        if (shouldFilter(requestURI)) {
            response.setContentType("application/json; charset=UTF-8");
            try {
                String authHeader = request.getHeader("Authorization");
                if (authHeader != null && authHeader.startsWith("Bearer ")) {
                    String token = authHeader.substring(7);
                    String userId = jwtUtils.extractId(token);
                    if (userId != null) {
                        UsernamePasswordAuthenticationToken authentication =
                                new UsernamePasswordAuthenticationToken(userId, null, new ArrayList<>());
                        SecurityContextHolder.getContext().setAuthentication(authentication);
                    }
                } else {
                    // 如果没有授权头或者授权头无效
                    sendUnauthorizedResponse(response, Result.unauthorized());
                    return;
                }
            } catch (Exception e) {
                // 处理 token 验证异常
                sendUnauthorizedResponse(response, Result.internalServerError());
                return;
            }
        }else{
            filterChain.doFilter(request, response);
        }
    }

    private boolean shouldFilter(String requestURI) {
        // 检查请求路径是否需要拦截
        for (String path : EXCLUDED_PATHS) {
            if (requestURI.matches(path)) {
                return true;
            }
        }
        return false;
    }

    private void sendUnauthorizedResponse(HttpServletResponse response, Result result) throws IOException {
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        response.getWriter().write(objectMapper.writeValueAsString(result));
    }
}
