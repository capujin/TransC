package com.mvc.test.service.Impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mvc.test.entity.User.UsersRoles;
import com.mvc.test.mapper.UsersRolesMapper;
import com.mvc.test.service.UsersRolesService;
import org.springframework.stereotype.Service;

@Service
public class UsersRolesServiceImpl extends ServiceImpl<UsersRolesMapper, UsersRoles> implements UsersRolesService {
}
