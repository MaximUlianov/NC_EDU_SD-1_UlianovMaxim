package com.netcracker.edu.backend.service;

import com.netcracker.edu.backend.DTO.SubscriptionDTO;
import com.netcracker.edu.backend.entity.Category;
import com.netcracker.edu.backend.entity.Subscription;

import java.util.List;

public interface SubscriptionService {
    List<Subscription> getAllSubscriptions(int page, int size);
    String subscribe(SubscriptionDTO subscriptionDTO);
    List<Subscription> getUserSubscriptions(String email);
    List<Subscription> getSubscrByCategory(long id);
    List<Subscription> getSubscrByCompany(long id);
    List<Subscription> getSubscrByPartOfName(String name);
    String deleteUserSubscription(String email, long id);
    String deleteSubscription(long id);
    String addSubscription(SubscriptionDTO subscription);
    String addCategory(Category category);
    int getTotalPages(int perPage);
}
