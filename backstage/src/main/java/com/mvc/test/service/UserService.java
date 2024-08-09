package com.mvc.test.service;

import com.mvc.test.entity.User;

import java.util.List;

public interface UserService {
    public User authenticate(String username, String password);
//    public List<User> loadAllUsers();
//    public int save(User user);
//    public String findUserByNameAndPassword(User user);
//    public String findUserByName(User user);
//    public User getUserById(String uid);
//    public int deleteUser(String uid);
//    public int updateUser(User user);
}
