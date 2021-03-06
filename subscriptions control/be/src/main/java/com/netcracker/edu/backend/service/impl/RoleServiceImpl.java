package com.netcracker.edu.backend.service.impl;

import com.netcracker.edu.backend.entity.Response;
import com.netcracker.edu.backend.entity.Role;
import com.netcracker.edu.backend.repository.RoleRepository;
import com.netcracker.edu.backend.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleServiceImpl implements RoleService {

    private RoleRepository roleRepository;

    @Autowired
    public RoleServiceImpl(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public List<Role> findAll() {
        return (List<Role>) roleRepository.findAll();
    }

    @Override
    public Role findByRole(String roleName) {
        return roleRepository.findByRole(roleName);
    }

    @Override
    public Role save(Role role) {
        return roleRepository.save(role);
    }

    @Override
    public Response delete(long id) {
        roleRepository.deleteById(id);
        return new Response("ok");
    }
}
