package com.netcracker.edu.backend.service;

import com.netcracker.edu.backend.DTO.SubscriptionDTO;
import com.netcracker.edu.backend.entity.Response;
import com.netcracker.edu.backend.entity.Subscription;

import java.util.List;

public interface SubscriptionService {

    List<Subscription> getUserSubscriptions(String email);

    Response subscribe(SubscriptionDTO subscriptionDTO);
    Response deleteUserSubscription(String email, long id);
    Response setSale(long id, int sale);

}
