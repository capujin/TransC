package com.mvc.test.service.Impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mvc.test.entity.Roles;
import com.mvc.test.entity.User.User;
import com.mvc.test.entity.User.UserRoles;
import com.mvc.test.entity.User.UserSecurity;
import com.mvc.test.mapper.*;
import com.mvc.test.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;

@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    @Resource
    private UserMapper userMapper;
    @Resource
    private AdminMapper adminMapper;
    @Resource
    private UserSecurityMapper userSecurityMapper;
    @Autowired
    private UserRolesMapper userRolesMapper;
    @Autowired
    private RolesMapper rolesMapper;

    @Override
    public User authenticate(String username, String password) {
        // 查询用户 ID。如果用户名和密码匹配，则返回非 null 值
        User user = userMapper.findUserByNameAndPassword(username, password);
        return user;
    }

    @Override
    public boolean checkUsernameExists(String username) {
        if(userMapper.checkUsernameExists(username) == 0){
            return true;
        }else{
            return false;
        }
    }

    @Override
    public boolean save(User user) {
        try {
            adminMapper.insertUser(user);
            System.out.println("插入成功");
            return true;  // 如果插入成功，返回 true
        } catch (Exception e) {
            System.out.println("插入失败");
            System.out.println(e);
            return false;  // 如果插入失败，返回 false
        }
    }

    @Override
    public User getUserById(String id) {
        User user = userMapper.selectById(id);
        return user;
    }

    @Override
    public UserSecurity getUserSecurityById(String id) {
        UserSecurity userSecurity = userSecurityMapper.getUserSecurity(id);
        return userSecurity;
    }

    @Override
    public boolean saveUserRoles(UserRoles userRoles) {
        if(userRolesMapper.insertUserRoles(userRoles) != 0){
            return true;
        }
        return false;
    }

    @Override
    public Roles getRoleById(String userId) {
        Roles role = rolesMapper.getRoleByUserId(userId);
        return role;
    }

}
