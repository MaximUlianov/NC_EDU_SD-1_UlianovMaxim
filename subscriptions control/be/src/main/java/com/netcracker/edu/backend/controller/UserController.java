package com.netcracker.edu.backend.controller;

import com.netcracker.edu.backend.DTO.LogInUserDTO;
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

    @GetMapping
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    @PostMapping
    @ResponseBody
    public ResponseEntity<UserDTO> addUser(@RequestBody UserDTO user) {
        return ResponseEntity.ok(userService.save(user).get());
    }

    @RequestMapping(value = "/login/{email}", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<LogInUserDTO> getUserByLogin(@PathVariable String email){
        return ResponseEntity.ok(userService.getUserByEmail(email));
    }
    @GetMapping(value = "/{email}")
    @ResponseBody
    public ResponseEntity<UserDTO> getUserInfoByEmail(@PathVariable String email){
        return ResponseEntity.ok(userService.getUserInfoByEmail(email));
    }

    @GetMapping(value = "/username/{email}")
    @ResponseBody
    public ResponseEntity<UserDTO> getUsername(@PathVariable String email){
        return ResponseEntity.ok(userService.getUsername(email));
    }

}
