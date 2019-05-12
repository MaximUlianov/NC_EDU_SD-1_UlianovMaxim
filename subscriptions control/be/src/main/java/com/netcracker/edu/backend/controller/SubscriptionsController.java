package com.netcracker.edu.backend.controller;


import com.netcracker.edu.backend.DTO.SubscriptionDTO;
import com.netcracker.edu.backend.entity.Category;
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

    @GetMapping(value = "/{page}/{elements}")
    @ResponseBody
    public ResponseEntity<List<Subscription>> getAllSubscriptions(@PathVariable(name = "page") int page,
                                                                  @PathVariable(name = "elements") int elements){
        return ResponseEntity.ok(subscriptionService.getAllSubscriptions(page, elements));
    }


    @GetMapping(value = "/totalPages")
    @ResponseBody
    public ResponseEntity<Integer> getTotalPages(@RequestParam(name = "perPage") int perPage){
        return ResponseEntity.ok(subscriptionService.getTotalPages(perPage));
    }

    @GetMapping(value = "/category")
    @ResponseBody
    public  ResponseEntity<List<Subscription>> getSubscrByCategory(@RequestParam(name = "id") long id){
        return ResponseEntity.ok(subscriptionService.getSubscrByCategory(id));
    }

    @GetMapping(value = "/company")
    @ResponseBody
    public  ResponseEntity<List<Subscription>> getSubscrByCompany(@RequestParam(name = "id") long id){
        return ResponseEntity.ok(subscriptionService.getSubscrByCompany(id));
    }

    @GetMapping(value = "/user")
    @ResponseBody
    public ResponseEntity<List<Subscription>> getUserSubscr(@RequestParam(value = "email") String email){
        return ResponseEntity.ok(subscriptionService.getUserSubscriptions(email));
    }

    @GetMapping
    @ResponseBody
    public ResponseEntity<List<Subscription>> getSubscrByPartOfName(@RequestParam(value = "name") String name){
        return ResponseEntity.ok(subscriptionService.getSubscrByPartOfName(name));
    }

    @PostMapping(value = "/user")
    @ResponseBody
    public ResponseEntity<String> subscribe(@RequestBody SubscriptionDTO sub){
        return ResponseEntity.ok(subscriptionService.subscribe(sub));
    }

    @PostMapping
    @ResponseBody
    public ResponseEntity<String> addSubscription(@RequestBody SubscriptionDTO subscription){
        return ResponseEntity.ok(subscriptionService.addSubscription(subscription));
    }

    @PostMapping(value = "/category")
    @ResponseBody
    public ResponseEntity<String> addCategory(@RequestBody Category category){
        return ResponseEntity.ok(subscriptionService.addCategory(category));
    }

    @DeleteMapping(value = "/user")
    @ResponseBody
    public ResponseEntity<String> deleteUserSubscription(@RequestParam(value = "email") String email,
                                                         @RequestParam(value = "id") long id){
        return ResponseEntity.ok(subscriptionService.deleteUserSubscription(email, id));
    }

    @DeleteMapping
    @ResponseBody
    public ResponseEntity<String> deleteSubscription(@RequestParam(value = "id") long id){
        return ResponseEntity.ok(subscriptionService.deleteSubscription(id));
    }
}
