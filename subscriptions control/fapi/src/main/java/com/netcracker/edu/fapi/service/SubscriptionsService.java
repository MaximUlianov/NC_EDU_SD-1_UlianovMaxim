package com.netcracker.edu.fapi.service;

import com.netcracker.edu.fapi.models.Category;
import com.netcracker.edu.fapi.models.Response;
import com.netcracker.edu.fapi.models.Subscription;

import java.util.List;

public interface SubscriptionsService {

    List<Subscription> getAllSubscriptions(int page, int size);
    List<Subscription> getUserSubscriptions(String email);
    List<Subscription> getSubscrByCategoryId(long id);
    List<Subscription> getSubscrByCompanyId(long id);
    List<Subscription> getSubscrByPartOfName(String name);

    Response subscribe(long id, long wallet, String email);
    Response deleteUserSubscription(String email, long id);
    Response deleteSubscription(long id);
    Response addSubscription(Subscription subscription);
    Response addCategory(Category category);

    int getTotalPages(int size);
}
