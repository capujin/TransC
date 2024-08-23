package com.mvc.test.service.Impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mvc.test.entity.User.Permissions;
import com.mvc.test.mapper.PermissionsMapper;
import com.mvc.test.service.PermissionsService;
import org.springframework.stereotype.Service;

@Service
public class PermissionsServiceImpl extends ServiceImpl<PermissionsMapper,Permissions> implements PermissionsService {
}
