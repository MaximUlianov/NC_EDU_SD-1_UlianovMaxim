package com.netcracker.edu.fapi.controller;

import com.netcracker.edu.fapi.models.User;
import com.netcracker.edu.fapi.models.UserInfo;
import com.netcracker.edu.fapi.security.TokenProvider;
import com.netcracker.edu.fapi.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private TokenProvider tokenUtil;


    @GetMapping(value = "/info")
    public User getUserClaims(@RequestHeader("Authorization") String token){
        String email = tokenUtil.getUsernameFromToken(token);
        return userService.getUserInfoByEmail(email);
    }
    @GetMapping(value = "/username")
    public User getUsername(@RequestHeader("Authorization") String token){
        String email = tokenUtil.getUsernameFromToken(token);
        return userService.getUsername(email);
    }

    @RequestMapping(value="/signUp", method = RequestMethod.POST, produces = "application/json")
    public User saveUser(@RequestBody User user){
        return userService.save(user);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping(value = "/admin")
    public List<UserInfo> getAllUsers(){
        return userService.getAllUsers();
    }

}
