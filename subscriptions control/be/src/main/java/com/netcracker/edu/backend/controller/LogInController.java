package com.netcracker.edu.backend.controller;

import com.netcracker.edu.backend.service.LogInService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/log_in")
public class LogInController {

    private LogInService logInService;

    @Autowired
    public LogInController(LogInService logInService) {
        this.logInService = logInService;
    }

    @RequestMapping(value = "/email", method = RequestMethod.GET)
    public ResponseEntity getLogInParametersByEmail(@RequestParam(name = "email") String email) {
        return ResponseEntity.ok(logInService.findByEmail(email));
    }

    @DeleteMapping()
    @ResponseBody
    public ResponseEntity deleteUser(@RequestParam(name = "id") long id){
        return ResponseEntity.ok(logInService.delete(id));
    }
}