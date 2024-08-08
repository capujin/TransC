package com.mvc.test.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.mvc.test.entity.User;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface UserMapper extends BaseMapper<User> {
    @Select("select u_id from user where u_name = #{username} and u_password = #{password}")
    String findUserByNameAndPassword(@Param("username") String username, @Param("password") String password);


}
