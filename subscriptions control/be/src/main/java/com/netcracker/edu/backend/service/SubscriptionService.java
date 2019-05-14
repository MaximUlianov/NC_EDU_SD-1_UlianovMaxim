package com.netcracker.edu.backend.service;

import com.netcracker.edu.backend.DTO.SubscriptionDTO;
import com.netcracker.edu.backend.entity.Category;
import com.netcracker.edu.backend.entity.Response;
import com.netcracker.edu.backend.entity.Subscription;

import java.util.List;

public interface SubscriptionService {

    List<Subscription> getAllSubscriptions(int page, int size);
    List<Subscription> getUserSubscriptions(String email);
    List<Subscription> getSubscrByCategory(long id);
    List<Subscription> getSubscrByCompany(long id);
    List<Subscription> getSubscrByPartOfName(String name);

    Response subscribe(SubscriptionDTO subscriptionDTO);
    Response deleteUserSubscription(String email, long id);
    Response deleteSubscription(long id);
    Response addSubscription(SubscriptionDTO subscription);
    Response addCategory(Category category);

    int getTotalPages(int perPage);
}
