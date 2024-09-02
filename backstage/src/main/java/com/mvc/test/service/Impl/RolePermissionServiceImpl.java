package com.mvc.test.service.Impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mvc.test.entity.User.RolePermissions;
import com.mvc.test.mapper.RolePermissionsMapper;
import com.mvc.test.service.RolePermissionService;
import org.springframework.stereotype.Service;

@Service
public class RolePermissionServiceImpl extends ServiceImpl<RolePermissionsMapper, RolePermissions> implements RolePermissionService {
}
