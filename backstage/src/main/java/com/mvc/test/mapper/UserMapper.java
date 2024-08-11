package com.mvc.test.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.mvc.test.entity.User.User;
import com.mvc.test.entity.User.UserRoles;
import org.apache.ibatis.annotations.*;

public interface UserMapper extends BaseMapper<User> {
    @Select("select * from users where username = #{username} and password = #{password}")
    User findUserByNameAndPassword(@Param("username") String username, @Param("password") String password);
    //更新用户信息
    @Update("update users set username = #{username}, password = #{password} where id = #{id}")
    void updateUser(String id);
//    修改密码
    @Update("update users set username = #{user.username}, password = #{password} where id = #{id}")
    void updatePassword(User user);
//获取权限
    @Select("select * from users where id = #{id}")
    User selectById(String id);
//判断用户名是否存在
    @Select("SELECT COUNT(*) FROM users WHERE username = #{username}")
    int checkUsernameExists(@Param("username") String username);
//设置用户状态
    @Update("update user_status set status = #{user.username} where user_id = #{id}")
    @Options(useGeneratedKeys = true, keyProperty = "user_id")
    void insertUser(User user);

    @Insert("insert into user_roles values ")
    int saveUserRoles(UserRoles userRoles);
}
