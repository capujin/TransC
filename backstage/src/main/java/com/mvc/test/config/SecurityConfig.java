package com.mvc.test.config;

import com.mvc.test.filter.JwtAuthenticationFilter;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
// 在controller的方法加上 @PreAuthorize("hasAuthority('DELETE')")
// 即可判断请求是否有DELETE权限
// 在controller的方法加上 @PreAuthorize("#name == authentication.principal.username")
// 将输入的user参数与authentication上下文作对比

// @PreFilter("filterObject.name == authentication.name")
// 在service方法上加,对输出数据的过滤

// @PostFilter("filterObject.name == authentication.principal.username")
// 在controller方法上加,对输入数据的过滤
public class SecurityConfig extends WebSecurityConfigurerAdapter {

//    @Autowired
//    private AuthenticationProviderService authenticationProvider;
//    @Override
//    protected void configure(AuthenticationManagerBuilder auth){
//        auth.authenticationProvider(authenticationProvider);
//    }
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeRequests().anyRequest().permitAll()
                .and()
                .addFilterBefore(new JwtAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);
    }
}
