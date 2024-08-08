package com.mvc.test.service.Impl;

import com.mvc.test.entity.Menu;
import com.mvc.test.mapper.MenuMapper;
import com.mvc.test.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class MenuServiceImpl implements MenuService {

    @Resource
    private MenuMapper menuMapper;

    public List<Menu> loadAllMenus(){
        return menuMapper.loadTopMenus();
    }

    public List<Menu> getAllMenus() {
        List<Menu> allMenus = menuMapper.getAllMenus(); // 获取所有菜单数据
        return constructMenuHierarchy(allMenus, null); // 构建菜单层次结构
    }

    // 递归构建菜单层次结构
    private List<Menu> constructMenuHierarchy(List<Menu> allMenus, Integer parentId) {
        List<Menu> children = new ArrayList<>();
        for (Menu menu : allMenus) {
            if ((parentId == null && menu.getParentId() == null) || (parentId != null && parentId.equals(menu.getParentId()))) {
                menu.setChildren(constructMenuHierarchy(allMenus, menu.getMenuId()));
                children.add(menu);
            }
        }
        return children;
    }

}
