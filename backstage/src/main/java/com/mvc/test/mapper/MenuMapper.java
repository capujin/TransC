package com.mvc.test.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.mvc.test.entity.Menu;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface MenuMapper extends BaseMapper<Menu> {
    @Select("select * from menu where 'order' = 1 and parent_id is null")
    public List<Menu> loadTopMenus();

    @Select("select * from menu")
    List<Menu> getAllMenus();
//  返回的事所有的菜单，其中一个是子菜单id

//    @Select("select * from menu")
//    public List<Menu> findById(Integer mid);
}
