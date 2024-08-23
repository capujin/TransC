package com.mvc.test.entity.User;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@TableName(value = "permissions")
@NoArgsConstructor
public class Permissions  extends Model<Permissions> {
    @TableId(value = "id",type = IdType.AUTO)
    private String id;
    @TableField(value = "model")
    private String model;
    @TableField(value = "name")
    private String name;
}
