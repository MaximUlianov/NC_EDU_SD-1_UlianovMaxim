package com.netcracker.edu.fapi.controller;

import com.netcracker.edu.fapi.models.*;
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

    @GetMapping(value = "/email")
    public User getUserInfo(@RequestHeader("Authorization") String token){
        String email = tokenUtil.getUsernameFromToken(token);
        return userService.getUserInfoByEmail(email);
    }


    @RequestMapping(value="/signUp", method = RequestMethod.POST, produces = "application/json")
    public User saveUser(@RequestBody User user){
        return userService.save(user);
    }


    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping(value = "/admin/totalPages")
    public Integer getTotalPages(@RequestParam(value = "perPage") int perPage){
        return userService.getTotalPages(perPage);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping(value = "/admin/{page}/{size}")
    public List<UserInfo> getUsers(@PathVariable(value = "page") int page,
                                   @PathVariable(value = "size") int size){
        return userService.getUsers(page,size);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping(value = "/subscriptions")
    public List<Subscription> getUserSubscrByAdmin(@RequestParam(value = "id") long id) {
        return userService.getUserSubscrByAdmin(id);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping(value = "/search")
    public List<UserInfo> searchUser(@RequestParam(value = "type") String type,
                           @RequestParam(value = "value") String value){
        return userService.searchUser(type, value);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping(value = "/subscriptions/block")
    public Response blockSubscription(@RequestBody long [] id){
        return userService.blockSubscription(id);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping(value = "/subscriptions/unblock")
    public Response unblockSubscription(@RequestBody long [] id){
        return userService.unblockSubscription(id);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping(value = "/audit")
    public List<Audit> getUserHistory(@RequestParam(value = "id") long id){
        return userService.getUserHistory(id);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping(value = "/wallets")
    public List<Wallet> getUserWalletsByAdmin(@RequestParam(value = "id") long id) {
        return userService.getUserWalletsByAdmin(id);
    }
}
