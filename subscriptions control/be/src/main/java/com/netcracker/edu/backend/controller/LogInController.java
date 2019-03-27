package com.netcracker.edu.backend.controller;

import com.netcracker.edu.backend.entity.LogIn;
import com.netcracker.edu.backend.service.LogInService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/log_in")
public class LogInController {

    @Autowired
    private LogInService logInService;

    @RequestMapping(value = "/email/{email}", method = RequestMethod.GET)
    public ResponseEntity<LogIn> getLogInParametersByEmail(@PathVariable(name = "email") String email) {
        LogIn logIn = logInService.findByEmail(email);
        return ResponseEntity.ok(logIn);
    }

    @RequestMapping()
    public List<LogIn> getAllLogInParameters() {
        return logInService.findAll();
    }

    /*@RequestMapping(method = RequestMethod.POST)
    public User saveUser(@RequestBody User user) {
        return userService.save(user);
    }*/
}