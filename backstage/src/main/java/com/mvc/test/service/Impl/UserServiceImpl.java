package com.mvc.test.service.Impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mvc.test.entity.User.Roles;
import com.mvc.test.entity.User.User;
import com.mvc.test.entity.User.UserSecurity;
import com.mvc.test.mapper.*;
import com.mvc.test.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;

@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

}
