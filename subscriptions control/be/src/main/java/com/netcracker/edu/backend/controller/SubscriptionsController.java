package com.netcracker.edu.backend.controller;


import com.netcracker.edu.backend.DTO.SubscriptionDTO;
import com.netcracker.edu.backend.entity.Response;
import com.netcracker.edu.backend.entity.Subscription;
import com.netcracker.edu.backend.service.SubscriptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/subscriptions")
public class SubscriptionsController {

    @Autowired
    private SubscriptionService subscriptionService;

    @GetMapping(value = "/user")
    @ResponseBody
    public ResponseEntity<List<Subscription>> getUserSubscr(@RequestParam(value = "email") String email){
        return ResponseEntity.ok(subscriptionService.getUserSubscriptions(email));
    }

    @PostMapping(value = "/user")
    @ResponseBody
    public ResponseEntity<Response> subscribe(@RequestBody SubscriptionDTO sub){
        return ResponseEntity.ok(subscriptionService.subscribe(sub));
    }

    @DeleteMapping(value = "/user")
    @ResponseBody
    public ResponseEntity<Response> deleteUserSubscription(@RequestParam(value = "email") String email,
                                                         @RequestParam(value = "id") long id){
        return ResponseEntity.ok(subscriptionService.deleteUserSubscription(email, id));
    }
}
