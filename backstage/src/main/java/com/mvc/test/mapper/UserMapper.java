package com.mvc.test.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.mvc.test.entity.User;
import org.apache.ibatis.annotations.*;

public interface UserMapper extends BaseMapper<User> {
    @Select("select * from users where username = #{username} and password = #{password}")
    User findUserByNameAndPassword(@Param("username") String username, @Param("password") String password);
}
