package com.netcracker.edu.fapi.service;

import com.netcracker.edu.fapi.models.Category;
import com.netcracker.edu.fapi.models.Subscription;

import java.util.List;

public interface SubscriptionsService {

    List<Subscription> getAllSubscriptions(int page, int size);
    String subscribe(long id,long wallet, String email);
    List<Subscription> getUserSubscriptions(String email);
    List<Subscription> getSubscrByCategoryId(long id);
    List<Subscription> getSubscrByCompanyId(long id);
    List<Subscription> getSubscrByPartOfName(String name);
    String deleteUserSubscription(String email, long id);
    String deleteSubscription(long id);
    String addSubscription(Subscription subscription);
    String addCategory(Category category);
    int getTotalPages(int size);
}
