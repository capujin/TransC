package com.mvc.test.service;

import com.mvc.test.entity.User.User;

import java.util.List;

public interface AdminService {
    public List<User> findAllUsers();
}
