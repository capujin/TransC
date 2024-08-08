package com.mvc.test.service.Impl;

import com.mvc.test.entity.User;
import com.mvc.test.mapper.UserMapper;
import com.mvc.test.service.UserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Resource
    private UserMapper userMapper;

    @Override
    public List<User> loadAllUsers() {
        System.out.println(userMapper.selectList(null));
        return userMapper.selectList(null);
    }

    @Override
    public int save(User user) {
        return userMapper.save(user.getuId(),user.getuName(),user.getuPassword());
    }

    @Override
    public String findUserByNameAndPassword(User user) {
        return userMapper.findUserByNameAndPassword(user.getuName(),user.getuPassword());

    }

    @Override
    public String findUserByName(User user) {
//        System.out.println("111111111111111111111111111"+userMapper.selectById(user.getuName()));
        userMapper.findUserByName(user.getuName());
        return userMapper.findUserByName(user.getuName());
    }

    @Override
    public User getUserById(String uid) {
        User user = userMapper.selectById(uid);
        return user;
    }

    @Override
    public int deleteUser(String uid) {
        return userMapper.deleteById(uid);
    }

    @Override
    public int updateUser(User user) {
        return userMapper.updateUser(user.getuId(),user.getuPassword());
    }
}
