package com.netcracker.edu.backend.controller;


import com.netcracker.edu.backend.entity.Role;
import com.netcracker.edu.backend.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/role")
public class RoleController {

    @Autowired
    private RoleService roleService;

    @RequestMapping(value = "/role/{roleName}", method = RequestMethod.GET)
    public ResponseEntity<Role> getRoleByRoleName(@PathVariable(name = "roleName") String roleName) {
        Role role = roleService.findByRole(roleName);
        return ResponseEntity.ok(role);
    }

    @RequestMapping()
    public List<Role> getAllRoles() {
        return roleService.findAll();
    }

    /*@RequestMapping(method = RequestMethod.POST)
    public User saveUser(@RequestBody User user) {
        return userService.save(user);
    }*/
}
