package com.mvc.test.service.Impl;

import com.mvc.test.entity.Roles;
import com.mvc.test.entity.User.UserRoles;
import com.mvc.test.mapper.PermissionMapper;
import com.mvc.test.mapper.RolesMapper;
import com.mvc.test.mapper.UserRolesMapper;
import com.mvc.test.service.RolesService;
import org.apache.catalina.session.PersistentManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class RolesServiceImpl implements RolesService {

    @Resource
    private RolesMapper rolesMapper;
    @Resource
    private UserRolesMapper userRolesMapper;
    @Resource
    private PermissionMapper permissionMapper;

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

//    返回Permissons List
    @Override
    public List<String> getPermissonsById(String roleId) {
        System.out.println("service层："+permissionMapper.selectByMap(Collections.singletonMap("role_id", roleId)));
        return new ArrayList<>('1');
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
