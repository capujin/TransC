package com.mvc.test.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.mvc.test.entity.Roles;
import com.mvc.test.entity.User.User;
import com.mvc.test.entity.User.UserRoles;
import com.mvc.test.entity.User.UserSecurity;

public interface UserService extends IService<User> {
    public User authenticate(String username, String password);
    public boolean checkUsernameExists(String username);
    public boolean save(User user);
    public User getUserById(String id);
    public UserSecurity getUserSecurityById(String id);
    public boolean saveUserRoles(UserRoles userRoles);
    public Roles getRoleById(String userId);
}
