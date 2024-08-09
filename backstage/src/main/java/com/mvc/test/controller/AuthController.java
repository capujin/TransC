package com.mvc.test.controller;

import com.mvc.test.utils.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/authenticate")
public class AuthController {

    @Autowired
    private JwtUtils jwtUtil;

    @PostMapping
    public Map<String, String> createToken(@RequestBody Map<String, String> user) {
        String username = user.get("username");
        String id = user.get("id");
        String token = jwtUtil.generateToken(username,id);
        Map<String, String> response = new HashMap<>();
        response.put("token", token);
        return response;
    }
}
