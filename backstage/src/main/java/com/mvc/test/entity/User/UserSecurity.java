package com.mvc.test.entity.User;

import lombok.Data;

@Data
public class UserSecurity {
    private String userId;
    private int failedLoginAttempts;  // 用户登录失败的次数
    private int accountNonLocked; // 当前账户是否没被锁定
    private int accountNonExpired; // 账户是否没过期
    private int credentialsNonExpired; // 用户的凭据（通常是密码）是否未过期
    private int enabled; // 账户是否启用

    public String getUserId() {
        return userId;
    }

    public int getFailedLoginAttempts() {
        return failedLoginAttempts;
    }

    public int getAccountNonLocked() {
        return accountNonLocked;
    }

    public int getAccountNonExpired() {
        return accountNonExpired;
    }

    public int getCredentialsNonExpired() {
        return credentialsNonExpired;
    }

    public int getEnabled() {
        return enabled;
    }
}
