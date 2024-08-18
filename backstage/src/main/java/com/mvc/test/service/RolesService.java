package com.mvc.test.service;

import com.mvc.test.entity.Roles;

import java.util.List;

public interface RolesService {
    public boolean save(Roles roles);
    public Roles getRoleById(String id);
    public List<String> getPermissonsById(String id);
}
