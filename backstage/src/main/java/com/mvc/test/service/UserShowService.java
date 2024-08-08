package com.mvc.test.service;

import com.mvc.test.entity.User;

import java.util.List;

public interface UserShowService {
    public List<User> loadAllUsersByPage(int pagenum, int totalpage);
    public int getUserTotal();
}
