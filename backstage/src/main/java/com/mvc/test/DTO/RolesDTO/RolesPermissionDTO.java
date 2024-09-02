package com.mvc.test.DTO.RolesDTO;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@ApiModel(description = "新建角色")
@NoArgsConstructor
public class RolesPermissionDTO {
    @Schema(name = "角色名称", format = "string", example = "string")
    private String roleName;
    @Schema(name = "权限列表", format = "list", example = "[]")
//    @ApiModelProperty(value = "权限列表", example = "[]")
    private List<Integer> permission_ids;
}
