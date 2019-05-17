package com.netcracker.edu.fapi.controller;

import com.netcracker.edu.fapi.models.Response;
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

    private SubscriptionsService subscriptionsService;

    private TokenProvider tokenUtil;

    @Autowired
    public SubscriptionsController(SubscriptionsService subscriptionsService, TokenProvider tokenUtil) {
        this.subscriptionsService = subscriptionsService;
        this.tokenUtil = tokenUtil;
    }

    @GetMapping(value = "/user")
    public List<Subscription> getUserSubscr(@RequestHeader("Authorization") String token){
        String email = tokenUtil.getUsernameFromToken(token);
        return subscriptionsService.getUserSubscriptions(email);
    }


    @PostMapping(value = "/user")
    public Response subscribe(@RequestHeader("Authorization") String token, @RequestBody Subscription subscription){
        String email = tokenUtil.getUsernameFromToken(token);
        return subscriptionsService.subscribe(subscription, email);
    }


    @DeleteMapping(value = "/user")
    public Response deleteUserSubscription(@RequestParam(value = "id") long id, @RequestHeader("Authorization") String token){
        String email = tokenUtil.getUsernameFromToken(token);
        return subscriptionsService.deleteUserSubscription(email, id);
    }
}
