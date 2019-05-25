package com.netcracker.edu.backend.controller;


import com.netcracker.edu.backend.DTO.SubscriptionDTO;
import com.netcracker.edu.backend.service.SubscriptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/subscriptions")
public class SubscriptionController {

    @Autowired
    private SubscriptionService subscriptionService;

    @GetMapping(value = "/user")
    @ResponseBody
    public ResponseEntity getUserSubscr(@RequestParam(value = "email") String email){
        return ResponseEntity.ok(subscriptionService.getUserSubscriptions(email));
    }

    @PostMapping(value = "/user")
    @ResponseBody
    public ResponseEntity subscribe(@RequestBody SubscriptionDTO sub){
        return ResponseEntity.ok(subscriptionService.subscribe(sub));
    }

    @DeleteMapping(value = "/user")
    @ResponseBody
    public ResponseEntity deleteUserSubscription(@RequestParam(value = "email") String email,
                                                         @RequestParam(value = "id") long id){
        return ResponseEntity.ok(subscriptionService.deleteUserSubscription(email, id));
    }

    @PutMapping(value = "/sale")
    @ResponseBody ResponseEntity setSale(@RequestBody SubscriptionDTO subscriptionDTO){
        return ResponseEntity.ok(subscriptionService.setSale(subscriptionDTO.getId(), subscriptionDTO.getSale()));
    }
}
