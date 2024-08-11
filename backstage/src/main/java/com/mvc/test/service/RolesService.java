package com.mvc.test.service;

import com.mvc.test.entity.Roles;

public interface RolesService {
    public boolean save(Roles roles);
    public Roles getRoleById(String id);
}
