package com.mvc.test.entity.User;


import lombok.Data;

import java.util.Date;

@Data
public class User {
    private String id;
    private String username;
    private String password;
    private Date createdAt;

    public String getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Date getCreated_at() {
        return createdAt;
    }

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    @Override
    public String toString() {
        return "User{" +
                ", username='" + username + '\'' +
                ", created_at=" + createdAt +
                '}';
    }
}
