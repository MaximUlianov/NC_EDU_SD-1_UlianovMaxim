package com.netcracker.edu.backend.service.impl;

import com.netcracker.edu.backend.entity.LogIn;
import com.netcracker.edu.backend.entity.Response;
import com.netcracker.edu.backend.repository.LogInRepository;
import com.netcracker.edu.backend.service.LogInService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LogInServiceImpl implements LogInService {


    private LogInRepository logInRepository;

    @Autowired
    public LogInServiceImpl(LogInRepository logInRepository) {
        this.logInRepository = logInRepository;
    }

    @Override
    public List<LogIn> findAll() {
        return (List<LogIn>) logInRepository.findAll();
    }

    @Override
    public LogIn findByEmail(String email) {
        return logInRepository.findByEmail(email);
    }

    @Override
    public LogIn save(LogIn logIn) {
        return logInRepository.save(logIn);
    }

    @Override
    public Response delete(long id) {
        logInRepository.deleteById(id);
        return new Response("ok");
    }
}
