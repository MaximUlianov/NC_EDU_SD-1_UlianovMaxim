package com.netcracker.edu.backend.service;

import com.netcracker.edu.backend.entity.LogIn;
import com.netcracker.edu.backend.entity.Response;

import java.util.List;

public interface LogInService {
    List<LogIn> findAll();
    LogIn findByEmail(String email);
    LogIn save(LogIn user);
    Response delete(long id);
}
