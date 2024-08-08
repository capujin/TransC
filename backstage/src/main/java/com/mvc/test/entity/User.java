package com.mvc.test.entity;


import java.util.Date;

public class User {
    private String id;
    private String username;
    private String password;
    private Date created_at;

    public User(String id, String username, String password, Date created_at) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.created_at = created_at;
    }

    @Override
    public String toString() {
        return "User{" +
                "id='" + id + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", created_at=" + created_at +
                '}';
    }
}
