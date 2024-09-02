package com.mvc.test.entity.User;

import com.baomidou.mybatisplus.annotation.*;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@TableName(value = "users")
@NoArgsConstructor
public class User extends Model<User> {
    public enum Status {
        ACTIVE, INACTIVE, LOCKED, DISABLED
    }
    @TableId(value = "id",type = IdType.AUTO)
    private Integer id;
    @TableField(value = "username")
    private String username;
    @TableField(value = "password")
    private String password;
    @TableField(value = "created_at",fill = FieldFill.INSERT)
    private Date createdAt;
    @TableField(value = "status")
    private Status status;
}
