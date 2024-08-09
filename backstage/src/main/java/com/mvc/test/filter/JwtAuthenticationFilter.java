package com.mvc.test.filter;

import com.mvc.test.utils.JwtUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class JwtAuthenticationFilter extends OncePerRequestFilter {
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        String authHeader = request.getHeader("Authorization");

        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            String token = authHeader.substring(7);
            try {
                new JwtUtils().decodeToken(token);
                // 如果需要，可以在这里设置用户的认证信息到 SecurityContext 中
            } catch (Exception e) {
                // 处理 token 验证异常
            }
        }

        filterChain.doFilter(request, response);
    }
}
