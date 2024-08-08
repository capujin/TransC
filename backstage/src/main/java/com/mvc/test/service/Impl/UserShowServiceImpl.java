package com.mvc.test.service.Impl;

import com.mvc.test.entity.User;
import com.mvc.test.mapper.UserMapper;
import com.mvc.test.service.UserShowService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Collections;
import java.util.List;

@Service
public class UserShowServiceImpl implements UserShowService {

    @Resource
    private UserMapper userMapper;
    @Override
    public List<User> loadAllUsersByPage(int pagenum, int totalpage) {
        List<User> users = userMapper.loadAllUsersByPage(pagenum,totalpage);
        return users;
    }

    @Override
    public int getUserTotal() {
        return userMapper.getUserTotal();
    }
}
