package com.mvc.test.service;

import com.mvc.test.entity.Menu;

import java.util.List;

public interface MenuService {
    public List<Menu> loadAllMenus();
//    public List<Menu> findById();
    public List<Menu> getAllMenus();
}
