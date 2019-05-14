package com.netcracker.edu.backend.service;

import com.netcracker.edu.backend.entity.Response;
import com.netcracker.edu.backend.entity.Role;

import java.util.List;

public interface RoleService {

    List<Role> findAll();

    Role findByRole(String roleName);
    Role save(Role user);

    Response delete(long id);
}
