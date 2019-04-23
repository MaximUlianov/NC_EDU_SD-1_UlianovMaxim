package com.netcracker.edu.backend.service;

import com.netcracker.edu.backend.DTO.SubscriptionDTO;
import com.netcracker.edu.backend.entity.Subscription;

import java.util.List;

public interface SubscriptionService {
    List<Subscription> getAllSubscriptions();
    String subscribe(SubscriptionDTO subscriptionDTO);
    List<Subscription> getUserSubscriptions(String email);
    String deleteUserSubscription(String email, long id);
}
