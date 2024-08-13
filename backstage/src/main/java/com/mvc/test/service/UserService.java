package com.mvc.test.service;

import com.mvc.test.entity.Roles;
import com.mvc.test.entity.User.User;
import com.mvc.test.entity.User.UserRoles;
import com.mvc.test.entity.User.UserSecurity;

public interface UserService {
    public User authenticate(String username, String password);
    public boolean checkUsernameExists(String username);
    public boolean save(User user);
    public User getUserById(String id);
    public UserSecurity getUserSecurityById(String id);
    public boolean saveUserRoles(UserRoles userRoles);
    public Roles getRoleNameById(String userId);
    //    public List<User> loadAllUsers();

//    public String findUserByNameAndPassword(User user);
//    public String findUserByName(User user);

//    public int deleteUser(String uid);
//    public int updateUser(User user);
}
