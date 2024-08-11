package com.mvc.test.DTO.UserDTO;

import java.util.List;

public class UserRegistrationDTO {
    private String username;
    private String password;
    private List<String> roleIds;

    public UserRegistrationDTO(String username, String password, List<String> roleIds) {
        this.username = username;
        this.password = password;
        this.roleIds = roleIds;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public List<String> getRoleIds() {
        return roleIds;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setRoleIds(List<String> roleIds) {
        this.roleIds = roleIds;
    }
}