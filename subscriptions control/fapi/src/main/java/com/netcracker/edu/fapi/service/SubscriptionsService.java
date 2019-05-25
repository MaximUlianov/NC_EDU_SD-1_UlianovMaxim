package com.netcracker.edu.fapi.service;

import com.netcracker.edu.fapi.models.Response;
import com.netcracker.edu.fapi.models.Subscription;

import java.util.List;

public interface SubscriptionsService {


    List<Subscription> getUserSubscriptions(String email);

    Response subscribe(Subscription subscription, String email);
    Response deleteUserSubscription(String email, long id);
    Response setSale(Subscription subscription);
}
