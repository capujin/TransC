package com.mvc.test.entity;


import java.util.Date;

public class User {
    private String id;
    private String username;
    private String password;
    private Date created_at;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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
        return created_at;
    }

    public void setCreated_at(Date created_at) {
        this.created_at = created_at;
    }

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
