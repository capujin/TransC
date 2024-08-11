package com.mvc.test.service.Impl;

import com.mvc.test.entity.User.User;
import com.mvc.test.mapper.AdminMapper;
import com.mvc.test.service.AdminService;
import com.mvc.test.service.UserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Collections;
import java.util.List;

@Service
public class AdminServiceImpl implements AdminService {
    @Resource
    private AdminMapper adminMapper;
    @Resource
    private UserService userService;

    @Override
    public List<User> findAllUsers() {
        try{
            List<User> users = adminMapper.findAllUsers();

            return users;
        }catch (Exception e){
            return null;
        }
    }
}
