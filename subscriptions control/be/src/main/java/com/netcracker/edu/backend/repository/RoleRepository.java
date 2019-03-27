package com.netcracker.edu.backend.repository;

import com.netcracker.edu.backend.entity.Role;
import org.springframework.data.repository.CrudRepository;

public interface RoleRepository extends CrudRepository<Role, Long> {
    Role findByRole(String roleName);
}
