package com.mvc.test.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.mvc.test.entity.Permissions;
import com.mvc.test.entity.User.User;
import org.apache.ibatis.annotations.*;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties;

import java.util.List;
@Mapper
public interface AdminMapper {
//通过id查询用户
    @Select("select * from users where id = #{id}")
    User findUserById(@Param("id") Long id);
//获取所有用户
    @Select("select * from users")
    List<User> findAllUsers();

//创建新用户
    @Insert("insert into users(username, password) values(#{username}, #{password})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    void insertUser(User user);

//删除用户
    @Delete("delete from users where id = #{id}")
    void deleteUserById(@Param("id") Long id);
}
