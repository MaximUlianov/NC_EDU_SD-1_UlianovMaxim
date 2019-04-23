package com.netcracker.edu.fapi.service.impl;

import com.netcracker.edu.fapi.models.Subscription;
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
    public List<Subscription> getAllSubscriptions() {
        RestTemplate restTemplate = new RestTemplate();
        Subscription [] subscriptionsResponse = restTemplate.getForObject(backendServerUrl + "/api/subscriptions", Subscription[].class);
        return subscriptionsResponse == null ? Collections.emptyList() : Arrays.asList(subscriptionsResponse);
    }

    @Override
    public String subscribe(long id, String email) {
        RestTemplate restTemplate = new RestTemplate();
        Subscription subscription = new Subscription();
        subscription.setId(id);
        subscription.setEmail(email);
        return restTemplate.postForEntity(backendServerUrl + "/api/subscriptions", subscription, String.class).getBody();
    }

    @Override
    public List<Subscription> getUserSubscriptions(String email) {
        RestTemplate restTemplate = new RestTemplate();
        Subscription [] subscriptionsResponse = restTemplate.getForObject(backendServerUrl + "/api/subscriptions/user/" + email, Subscription[].class);
        return subscriptionsResponse == null ? Collections.emptyList() : Arrays.asList(subscriptionsResponse);
    }

    @Override
    public String deleteUserSubscription(String email, long id) {
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.delete(backendServerUrl + "/api/subscriptions/user/" + email + "/" + id);
        return "Deleted";
    }
}
