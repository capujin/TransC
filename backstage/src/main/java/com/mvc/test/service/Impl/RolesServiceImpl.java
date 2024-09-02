package com.mvc.test.service.Impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mvc.test.entity.User.Roles;
import com.mvc.test.mapper.RolesMapper;
import com.mvc.test.service.RolesService;
import org.springframework.stereotype.Service;

@Service
public class RolesServiceImpl extends ServiceImpl<RolesMapper, Roles> implements RolesService{
}
