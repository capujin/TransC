package com.mvc.test.entity.User;

import lombok.Data;

@Data
public class UserStatus {
    private String userId;
    private String status;  // 用户当前账号状态 “ACTIVE”，“INACTIVE”，“LOCKED”
}
