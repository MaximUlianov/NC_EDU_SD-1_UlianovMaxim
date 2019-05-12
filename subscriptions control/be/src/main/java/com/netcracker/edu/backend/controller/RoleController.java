package com.netcracker.edu.backend.controller;


import com.netcracker.edu.backend.entity.Role;
import com.netcracker.edu.backend.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/role")
public class RoleController {

    @Autowired
    private RoleService roleService;

    @RequestMapping()
    public List<Role> getAllRoles() {
        return roleService.findAll();
    }
}
