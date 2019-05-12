package com.netcracker.edu.fapi.controller;

import com.netcracker.edu.fapi.models.Category;
import com.netcracker.edu.fapi.models.Subscription;
import com.netcracker.edu.fapi.security.TokenProvider;
import com.netcracker.edu.fapi.service.SubscriptionsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
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


    @GetMapping(value = "/{page}/{size}")
    public List<Subscription> getAllSubscr(@PathVariable(name = "page") int page,
                                           @PathVariable(name = "size") int size){
        return subscriptionsService.getAllSubscriptions(page, size);
    }

    @GetMapping(value = "/category")
    public List<Subscription> getSubscrByCategory(@RequestParam(name = "id") long id){
        return subscriptionsService.getSubscrByCategoryId(id);
    }

    @GetMapping(value = "/company")
    public List<Subscription> getSubscrByCompany(@RequestParam(name = "id") long id){
        return subscriptionsService.getSubscrByCompanyId(id);
    }

    @GetMapping(value = "/user")
    public List<Subscription> getUserSubscr(@RequestHeader("Authorization") String token){
        String email = tokenUtil.getUsernameFromToken(token);
        return subscriptionsService.getUserSubscriptions(email);
    }

    @GetMapping
    public List<Subscription> getSubscrByPartOfName(@RequestParam(value = "name") String name){
        return subscriptionsService.getSubscrByPartOfName(name);
    }

    @PostMapping(value = "/user")
    public String subscribe(@RequestHeader("Authorization") String token, @RequestBody long[] id){
        String email = tokenUtil.getUsernameFromToken(token);
        return subscriptionsService.subscribe(id[0],id[1], email);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public String addSubscription(@RequestBody Subscription subscription){
        return subscriptionsService.addSubscription(subscription);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping(value = "/category")
    public String addCategory(@RequestBody Category category){
        return subscriptionsService.addCategory(category);
    }


    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping
    public String deleteSubscription(@RequestParam(value = "id") long id){
        return subscriptionsService.deleteSubscription(id);
    }

    @DeleteMapping(value = "/user")
    public String deleteUserSubscription(@RequestParam(value = "id") long id, @RequestHeader("Authorization") String token){
        String email = tokenUtil.getUsernameFromToken(token);
        return subscriptionsService.deleteUserSubscription(email, id);
    }

    @GetMapping(value = "/totalPages")
    public Integer getTotalPages(@RequestParam(value = "perPage") int size){
        return subscriptionsService.getTotalPages(size);
    }


}
