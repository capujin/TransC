package com.mvc.test.DTO.UserDTO;

import com.mvc.test.entity.User.User;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class UserDTO {
    private String id;
    private String username;
    private Date createdAt;
    private String roleName;
    private int enabled;

    public UserDTO(String id, String username, Date createdAt, String roleName, int enabled) {
        this.id = id;
        this.username = username;
        this.createdAt = createdAt;
        this.roleName = roleName;
        this.enabled = enabled;
    }
}
