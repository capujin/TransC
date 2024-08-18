package com.mvc.test.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.mvc.test.entity.Permissions;
import com.mvc.test.entity.User.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface PermissionMapper extends BaseMapper<Permissions> {
    //@Select("select name,description from permission where username = #{username} and password = #{password}")
//创建新用户
    @Insert("insert into permissions(name, description) values(#{name}, #{desription})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    void insertUser(User user);

}
