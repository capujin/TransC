package com.mvc.test.service.Impl;

import com.mvc.test.entity.Roles;
import com.mvc.test.entity.User.UserRoles;
import com.mvc.test.mapper.RolesMapper;
import com.mvc.test.mapper.UserRolesMapper;
import com.mvc.test.service.RolesService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class RolesServiceImpl implements RolesService {

    @Resource
    private RolesMapper rolesMapper;
    @Resource
    private UserRolesMapper userRolesMapper;

    @Override
    public boolean save(Roles roles) {
        return false;
    }

    @Override
    public Roles getRoleById(String id) {
//        传来的是userId
        String roleId = userRolesMapper.getRoleId(id);
        Roles role = rolesMapper.selectById(roleId);
        return role;
    }
//    @Override
//    public boolean save(Roles role) {
//        try {
//            rolesMapper.insertRole(role);
//            System.out.println("插入成功");
//            return true;  // 如果插入成功，返回 true
//        } catch (Exception e) {
//            System.out.println("插入失败");
//            System.out.println(e);
//            return false;  // 如果插入失败，返回 false
//        }
//        return false;
//    }
}
