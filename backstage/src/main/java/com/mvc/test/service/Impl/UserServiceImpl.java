package com.mvc.test.service.Impl;

import com.mvc.test.entity.User;
import com.mvc.test.mapper.UserMapper;
import com.mvc.test.service.UserService;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;

@Service
public class UserServiceImpl implements UserService {

    @Resource
    private UserMapper userMapper;
    @Override
    public User authenticate(String username, String password) {
        // 查询用户 ID。如果用户名和密码匹配，则返回非 null 值
        User user = userMapper.findUserByNameAndPassword(username, password);
        return user;
    }
}
