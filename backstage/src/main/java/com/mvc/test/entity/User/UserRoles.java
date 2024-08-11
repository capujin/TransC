package com.mvc.test.entity.User;

import lombok.Data;

@Data
public class UserRoles {
    private String userId;
    private String RoleId;

    public UserRoles(String userId, String roleId) {
        this.userId = userId;
        RoleId = roleId;
    }
}
