package com.netcracker.edu.fapi.controller;

import com.netcracker.edu.fapi.models.Subscription;
import com.netcracker.edu.fapi.security.TokenProvider;
import com.netcracker.edu.fapi.service.SubscriptionsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/subscriptions")
public class SubscriptionsController {

    @Autowired
    private SubscriptionsService subscriptionsService;

    @Autowired
    private TokenProvider tokenUtil;


    @GetMapping
    public List<Subscription> getAllSubscr(){
        return subscriptionsService.getAllSubscriptions();
    }

    @PostMapping
    public String subscribe(@RequestHeader("Authorization") String token, @RequestBody long id){
        String email = tokenUtil.getUsernameFromToken(token);
        return subscriptionsService.subscribe(id, email);
    }

    @GetMapping(value = "/user")
    public List<Subscription> getUserSubscr(@RequestHeader("Authorization") String token){
        String email = tokenUtil.getUsernameFromToken(token);
        return subscriptionsService.getUserSubscriptions(email);
    }

    @DeleteMapping(value = "/user/{id}")
    public String deleteUserSubscription(@PathVariable(value = "id") long id, @RequestHeader("Authorization") String token){
        String email = tokenUtil.getUsernameFromToken(token);
        return subscriptionsService.deleteUserSubscription(email, id);
    }
}
