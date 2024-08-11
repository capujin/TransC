package com.mvc.test.entity;

import lombok.Data;

@Data
public class Permissions {
    String id;
    String name;
    String description;

    public Permissions(String name, String description) {
        this.name = name;
        this.description = description;
    }
}
