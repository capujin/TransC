package com.mvc.test.entity.User;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@TableName(value = "role_permissions")
@AllArgsConstructor
public class RolePermissions {
    @TableField(value = "role_id")
    private Integer roleId;
    @TableField(value = "permission_id")
    private Integer permissionId;
}
