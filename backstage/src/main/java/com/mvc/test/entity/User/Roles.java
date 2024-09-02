package com.mvc.test.entity.User;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@TableName(value = "roles")
@NoArgsConstructor
public class Roles {
    @TableId(value = "id",type = IdType.AUTO)
    private Integer id;
    @TableField(value = "role_name")
    private String roleName;
}
