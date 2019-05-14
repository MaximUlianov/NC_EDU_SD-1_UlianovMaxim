package com.netcracker.edu.fapi.service.impl;

import com.netcracker.edu.fapi.models.Category;
import com.netcracker.edu.fapi.models.Response;
import com.netcracker.edu.fapi.models.Subscription;
import com.netcracker.edu.fapi.models.Wallet;
import com.netcracker.edu.fapi.service.SubscriptionsService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Service
public class SubscriptionsServiceImpl implements SubscriptionsService {

    @Value("${backend.server.url}")
    private String backendServerUrl;

    @Override
    public List<Subscription> getAllSubscriptions(int page, int size) {
        RestTemplate restTemplate = new RestTemplate();
        Subscription [] subscriptionsResponse = restTemplate.getForObject(backendServerUrl + "/api/subscriptions/" + page + "/" + size, Subscription[].class);
        return subscriptionsResponse == null ? Collections.emptyList() : Arrays.asList(subscriptionsResponse);
    }

    @Override
    public List<Subscription> getSubscrByCategoryId(long id) {
        RestTemplate restTemplate = new RestTemplate();
        Subscription [] subscriptionsResponse = restTemplate.getForObject(backendServerUrl + "/api/subscriptions/category?id=" + id, Subscription[].class);
        return subscriptionsResponse == null ? Collections.emptyList() : Arrays.asList(subscriptionsResponse);
    }

    @Override
    public List<Subscription> getSubscrByCompanyId(long id) {
        RestTemplate restTemplate = new RestTemplate();
        Subscription [] subscriptionsResponse = restTemplate.getForObject(backendServerUrl + "/api/subscriptions/company?id=" + id, Subscription[].class);
        return subscriptionsResponse == null ? Collections.emptyList() : Arrays.asList(subscriptionsResponse);
    }

    @Override
    public Response subscribe(long id, long wallet, String email) {
        RestTemplate restTemplate = new RestTemplate();
        Subscription subscription = new Subscription();
        subscription.setId(id);
        subscription.setEmail(email);
        subscription.setWallet(new Wallet(wallet));
        return restTemplate.postForEntity(backendServerUrl + "/api/subscriptions/user", subscription, Response.class).getBody();
    }

    @Override
    public Response addSubscription(Subscription subscription) {
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.postForEntity(backendServerUrl + "/api/subscriptions", subscription, Response.class).getBody();
    }

    @Override
    public Response addCategory(Category category) {
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.postForEntity(backendServerUrl + "/api/subscriptions/category", category, Response.class).getBody();
    }

    @Override
    public List<Subscription> getUserSubscriptions(String email) {
        RestTemplate restTemplate = new RestTemplate();
        Subscription [] subscriptionsResponse = restTemplate.getForObject(backendServerUrl + "/api/subscriptions/user?email=" + email, Subscription[].class);
        return subscriptionsResponse == null ? Collections.emptyList() : Arrays.asList(subscriptionsResponse);
    }

    @Override
    public Response deleteUserSubscription(String email, long id) {
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.delete(backendServerUrl + "/api/subscriptions/user?email=" + email + "&id=" + id);
        return new Response("ok");
    }

    @Override
    public Response deleteSubscription(long id) {
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.delete(backendServerUrl + "/api/subscriptions?id=" + id);
        return new Response("ok");
    }

    @Override
    public int getTotalPages(int size) {
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.getForObject(backendServerUrl + "/api/subscriptions/totalPages?perPage=" + size, Integer.class);
    }

    @Override
    public List<Subscription> getSubscrByPartOfName(String name) {
        RestTemplate restTemplate = new RestTemplate();
        Subscription [] subscriptionsResponse = restTemplate.getForObject(backendServerUrl + "/api/subscriptions?name=" + name, Subscription[].class);
        return subscriptionsResponse == null ? Collections.emptyList() : Arrays.asList(subscriptionsResponse);
    }
}
