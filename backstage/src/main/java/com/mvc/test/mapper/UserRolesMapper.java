package com.mvc.test.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.mvc.test.entity.User.User;
import com.mvc.test.entity.User.UserRoles;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

public interface UserRolesMapper  extends BaseMapper {
    @Select("select role_id from user_roles where user_id=#{id};")
    String getRoleId(String id);

    @Insert("insert into user_roles(user_id, role_id) values(#{userId}, #{roleId})")
    int insertUserRoles(UserRoles userRoles);
}
