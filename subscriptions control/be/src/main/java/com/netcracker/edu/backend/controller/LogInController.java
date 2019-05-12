package com.netcracker.edu.backend.controller;

import com.netcracker.edu.backend.entity.LogIn;
import com.netcracker.edu.backend.service.LogInService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/log_in")
public class LogInController {

    @Autowired
    private LogInService logInService;

    @RequestMapping(value = "/email", method = RequestMethod.GET)
    public ResponseEntity<LogIn> getLogInParametersByEmail(@RequestParam(name = "email") String email) {
        LogIn logIn = logInService.findByEmail(email);
        return ResponseEntity.ok(logIn);
    }

    @DeleteMapping()
    @ResponseBody
    public ResponseEntity<String> deleteUser(@RequestParam(name = "id") long id){
        this.logInService.delete(id);
        return ResponseEntity.ok("ok");

    }
}