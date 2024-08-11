package com.mvc.test.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.mvc.test.entity.User.UserSecurity;
import org.apache.ibatis.annotations.Select;

public interface UserSecurityMapper extends BaseMapper {
    @Select("select * from user_security where user_id=#{id}")
    UserSecurity getUserSecurity(String id);
}
