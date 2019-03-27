package com.netcracker.edu.backend.repository;

import com.netcracker.edu.backend.entity.LogIn;
import org.springframework.data.repository.CrudRepository;

public interface LogInRepository extends CrudRepository<LogIn, Long> {
    LogIn findByEmail(String email);
}
