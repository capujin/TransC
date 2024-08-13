package com.mvc.test.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.mvc.test.entity.Roles;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface RolesMapper extends BaseMapper<Roles> {
    @Select("select * from roles")
    List<Roles> getAll();

    @Insert("insert into roles(role_name) values(#{roleName})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    void insertRole(Roles role);

    @Select("select * from roles where user_id = #{userId}")
    Roles getRoleByUserId(String userId);
}
