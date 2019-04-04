package com.netcracker.edu.backend.controller;

import com.netcracker.edu.backend.DTO.UserDTO;
import com.netcracker.edu.backend.entity.User;
import com.netcracker.edu.backend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/username/{username}")
    public ResponseEntity<User> getUserByEmail(@PathVariable(name = "username") String username) {
        User user = userService.findByUsername(username);
        return ResponseEntity.ok(user);
    }

    @GetMapping
    public List<User> getAllUsers() {
        return userService.findAll();
    }

    @RequestMapping(method = RequestMethod.POST, value = "/add")
    @ResponseBody
    public UserDTO addUser(@RequestBody UserDTO user)
    {
        return userService.save(user);
    }

    /*@RequestMapping(method = RequestMethod.POST)
    public User saveUser(@RequestBody User user) {
        return userService.save(user);
    }*/
}
