package com.mvc.test.DTO.RolesDTO;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.v3.oas.annotations.media.Schema;

import java.util.List;

@ApiModel(description = "角色信息")
public class RolesPermissionDTO {
    @Schema(name = "学生ID", description = "学生ID属性", format = "int64", example = "1")
    @ApiModelProperty(value = "角色名称", example = "Admin")
    private String roleName;
    @ApiModelProperty(value = "权限列表", example = "Admin")
    private List<String> permission_ids;
}
