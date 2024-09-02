package com.mvc.test.entity.User;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "users_roles")
public class UsersRoles {
    @TableField(value = "user_id")
    private Integer userId;
    @TableField(value = "role_id")
    private Integer roleId;
}
