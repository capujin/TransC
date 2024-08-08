package com.mvc.test.controller;

import com.mvc.test.entity.User;
import com.mvc.test.service.UserService;
import com.mvc.test.utils.JwtUtils;
import com.mvc.test.utils.Result;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/login")
@CrossOrigin
public class LoginController {

    @Autowired
    private UserService userService;

    @ApiOperation(value = "我是value，代码在hello()这哟~", notes = "我是notes，负责显示描述信息")
    @GetMapping("/check")
    public Result login(User user) {
        Result result = new Result();
        JwtUtils jwtUtils = new JwtUtils();
        if (userService.findUserByNameAndPassword(user) != null) {
            // 存储payload
            Map<String, String> payloadMap = new HashMap<>();
            // 存储header
            Map<String,Object> headerMap = new HashMap<>();
            payloadMap.put("uName", user.getuName());
            payloadMap.put("uPassword", user.getuPassword());
            // 获取token
            String token = JwtUtils.getToken(headerMap, payloadMap);
            result.setCode("200");
            result.setMsg("登录成功");
            user.setuId(userService.findUserByNameAndPassword(user));
            result.setData(user);
            result.setToken(token);
        } else {
            result.setCode("500");
            result.setMsg("登录失败");
        }
        return result;
    }
}
