package com.netcracker.edu.backend.controller;


import com.netcracker.edu.backend.DTO.SubscriptionDTO;
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

    @GetMapping
    @ResponseBody
    public ResponseEntity<List<Subscription>> getAllSubscriptions(){
        return ResponseEntity.ok(subscriptionService.getAllSubscriptions());
    }

    @PostMapping
    @ResponseBody
    public ResponseEntity<String> subscribe(@RequestBody SubscriptionDTO sub){
        return ResponseEntity.ok(subscriptionService.subscribe(sub));
    }
}
