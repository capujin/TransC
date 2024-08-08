package com.mvc.test.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.mvc.test.entity.User;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface UserMapper extends BaseMapper<User> {
    @Select("select u_id from user where u_name = #{username} and u_password = #{password}")
    String findUserByNameAndPassword(@Param("username") String username, @Param("password") String password);

    @Select("SELECT * FROM user LIMIT #{totalpage} OFFSET #{pagenum}")
    List<User> loadAllUsersByPage(@Param("pagenum") int pagenum, @Param("totalpage") int totalpage);

    @Select("select count(*) from user")
    int getUserTotal();

    @Select("select u_id from user where #{uName} = u_name")
    String findUserByName(String uName);

    @Select("select * from user where #{uId} =u_id")
    User selectById(@Param("uId") String uId);

    @Insert("insert into user(u_id,u_name,u_password) values(#{uid},#{name},#{password})")
    int save(@Param("uid") String uid,@Param("name") String name,@Param("password") String password);

    @Delete("delete from user where u_id = #{uid}")
    int deleteById(@Param("uid") String uid);

    @Update("update user set u_password = #{password} where u_id = #{uid}")
    int updateUser(@Param("uid") String id,@Param("password") String password);


}
