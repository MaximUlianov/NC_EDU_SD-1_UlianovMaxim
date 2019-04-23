package com.netcracker.edu.fapi.service;

import com.netcracker.edu.fapi.models.Subscription;

import java.util.List;

public interface SubscriptionsService {

    List<Subscription> getAllSubscriptions();
    String subscribe(long id, String email);
    List<Subscription> getUserSubscriptions(String email);
    String deleteUserSubscription(String email, long id);
}
